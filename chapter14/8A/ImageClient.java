import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageClient {

    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final Scanner scanner = new Scanner(System.in);
    private volatile boolean running = true;
    private String clientName;

    public static void main(String[] args) {
        new ImageClient().start();
    }

    public void start() {
        try {
            connect();

            Thread receiver = new Thread(this::receiveMessages);
            receiver.start();

            while (running) {
                System.out.println();
                System.out.println("1. Показать список изображений");
                System.out.println("2. Отправить изображение");
                System.out.println("3. Выход");
                System.out.print("Выберите пункт: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> showImages();
                    case "2" -> sendImage();
                    case "3" -> {
                        sendExit();
                        running = false;
                        close();
                    }
                    default -> System.out.println("Неверный выбор");
                }
            }

            receiver.join();
        } catch (Exception e) {
            System.out.println("Ошибка клиента: " + e.getMessage());
        }
    }

    private void connect() throws IOException {
        socket = new Socket(HOST, PORT);
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        System.out.print("Введите имя клиента: ");
        clientName = scanner.nextLine();

        out.writeUTF(clientName);
        out.flush();

        String type = in.readUTF();
        String message = in.readUTF();

        if ("ERROR".equals(type)) {
            throw new IOException(message);
        }

        System.out.println(message);
    }

    private void receiveMessages() {
        try {
            while (running) {
                String type = in.readUTF();

                switch (type) {
                    case "INFO" -> {
                        String message = in.readUTF();
                        System.out.println("\n[СЕРВЕР] " + message);
                    }
                    case "ERROR" -> {
                        String message = in.readUTF();
                        System.out.println("\n[ОШИБКА] " + message);
                    }
                    case "IMAGE" -> {
                        String from = in.readUTF();
                        String fileName = in.readUTF();
                        long size = in.readLong();

                        if (size > Integer.MAX_VALUE) {
                            System.out.println("\n[ОШИБКА] Слишком большой файл");
                            continue;
                        }

                        byte[] data = new byte[(int) size];
                        in.readFully(data);

                        saveImage(from, fileName, data);

                        System.out.println("\n[ФАЙЛ] Получено изображение от " + from + ": " + fileName);
                    }
                    default -> System.out.println("\n[ОШИБКА] Неизвестный тип сообщения: " + type);
                }
            }
        } catch (IOException e) {
            if (running) {
                System.out.println("\nСоединение с сервером потеряно");
            }
        }
    }

    private void showImages() throws IOException {
        List<Path> images = getImageList();

        if (images.isEmpty()) {
            System.out.println("В папке images нет изображений");
            return;
        }

        System.out.println("Список изображений:");
        for (int i = 0; i < images.size(); i++) {
            System.out.println((i + 1) + ". " + images.get(i).getFileName());
        }
    }

    private void sendImage() throws IOException {
        List<Path> images = getImageList();

        if (images.isEmpty()) {
            System.out.println("В папке images нет изображений");
            return;
        }

        System.out.println("Выберите изображение:");
        for (int i = 0; i < images.size(); i++) {
            System.out.println((i + 1) + ". " + images.get(i).getFileName());
        }

        System.out.print("Номер изображения: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= images.size()) {
            System.out.println("Неверный номер");
            return;
        }

        System.out.print("Введите имя получателя: ");
        String targetName = scanner.nextLine();

        Path file = images.get(index);
        byte[] data = Files.readAllBytes(file);

        out.writeUTF("SEND_IMAGE");
        out.writeUTF(targetName);
        out.writeUTF(file.getFileName().toString());
        out.writeLong(data.length);
        out.write(data);
        out.flush();

        System.out.println("Изображение отправлено на сервер");
    }

    private void sendExit() throws IOException {
        out.writeUTF("EXIT");
        out.flush();
    }

    private List<Path> getImageList() throws IOException {
        Path dir = Paths.get("images");

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        List<Path> result = new ArrayList<>();

        try (var stream = Files.list(dir)) {
            stream.filter(Files::isRegularFile)
                    .filter(this::isImageFile)
                    .forEach(result::add);
        }

        return result;
    }

    private boolean isImageFile(Path path) {
        String name = path.getFileName().toString().toLowerCase();
        return name.endsWith(".jpg")
                || name.endsWith(".jpeg")
                || name.endsWith(".png")
                || name.endsWith(".gif")
                || name.endsWith(".bmp");
    }

    private void saveImage(String from, String fileName, byte[] data) throws IOException {
        Path dir = Paths.get("received");

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        String safeName = from + "_" + System.currentTimeMillis() + "_" + fileName;
        Path target = dir.resolve(safeName);

        Files.write(target, data);
        System.out.println("Файл сохранён: " + target.toAbsolutePath());
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
