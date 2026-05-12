/* 

Создать на основе сокетов клиент/серверное приложение

Клиент выбирает изображение из списка и пересылает его другому клиенту через сервер.

*/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImageServer {

    private static final int PORT = 5050;
    private final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new ImageServer().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }

    private class ClientHandler implements Runnable {

        private final Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String clientName;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

                clientName = in.readUTF();

                if (clientName == null || clientName.isBlank() || clients.containsKey(clientName)) {
                    sendError("Имя клиента пустое или уже занято");
                    close();
                    return;
                }

                clients.put(clientName, this);
                sendInfo("Вы подключены как: " + clientName);

                System.out.println("Клиент подключился: " + clientName);

                while (true) {
                    String command = in.readUTF();

                    if ("SEND_IMAGE".equals(command)) {
                        handleSendImage();
                    } else if ("EXIT".equals(command)) {
                        break;
                    } else {
                        sendError("Неизвестная команда: " + command);
                    }
                }

            } catch (EOFException e) {
                // клиент отключился
            } catch (IOException e) {
                System.out.println("Ошибка клиента " + clientName + ": " + e.getMessage());
            } finally {
                clients.remove(clientName);
                close();
                System.out.println("Клиент отключился: " + clientName);
            }
        }

        private void handleSendImage() throws IOException {
            String targetName = in.readUTF();
            String fileName = in.readUTF();
            long size = in.readLong();

            if (size > Integer.MAX_VALUE) {
                sendError("Файл слишком большой");
                return;
            }

            byte[] data = new byte[(int) size];
            in.readFully(data);

            ClientHandler target = clients.get(targetName);

            if (target == null) {
                sendError("Клиент " + targetName + " не найден");
                return;
            }

            target.sendImage(clientName, fileName, data);
            sendInfo("Изображение отправлено клиенту " + targetName);
        }

        private synchronized void sendImage(String from, String fileName, byte[] data) throws IOException {
            out.writeUTF("IMAGE");
            out.writeUTF(from);
            out.writeUTF(fileName);
            out.writeLong(data.length);
            out.write(data);
            out.flush();
        }

        private synchronized void sendInfo(String message) throws IOException {
            out.writeUTF("INFO");
            out.writeUTF(message);
            out.flush();
        }

        private synchronized void sendError(String message) throws IOException {
            out.writeUTF("ERROR");
            out.writeUTF(message);
            out.flush();
        }

        private void close() {
            try {
                if (in != null) in.close();
            } catch (IOException ignored) {
            }
            try {
                if (out != null) out.close();
            } catch (IOException ignored) {
            }
            try {
                if (socket != null) socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
