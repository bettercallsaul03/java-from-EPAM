import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerConnection {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final String name;

    public PlayerConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        String receivedName = in.readUTF();
        if (receivedName == null || receivedName.isBlank()) {
            throw new IOException("Пустое имя клиента");
        }
        this.name = receivedName.trim();
    }

    public String getName() {
        return name;
    }

    public int readInt() throws IOException {
        return in.readInt();
    }

    public synchronized void send(String type, String message) throws IOException {
        out.writeUTF(type);
        out.writeUTF(message == null ? "" : message);
        out.flush();
    }

    public void sendInfo(String message) throws IOException {
        send("INFO", message);
    }

    public void sendError(String message) throws IOException {
        send("ERROR", message);
    }

    public void sendTurn(String message) throws IOException {
        send("TURN", message);
    }

    public void sendGameOver(String message) throws IOException {
        send("GAME_OVER", message);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }
}
