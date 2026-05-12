import java.net.ServerSocket;
import java.net.Socket;

public class BattleshipServer {

    private static final int PORT = 5050;

    public static void main(String[] args) {
        System.out.println("Сервер запущен на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Ожидание первого игрока...");
            PlayerConnection player1 = new PlayerConnection(serverSocket.accept());
            player1.sendInfo("Вы подключились к серверу. Ожидание второго игрока...");
            System.out.println("Подключился: " + player1.getName());

            System.out.println("Ожидание второго игрока...");
            PlayerConnection player2 = new PlayerConnection(serverSocket.accept());
            System.out.println("Подключился: " + player2.getName());

            if (player1.getName().equalsIgnoreCase(player2.getName())) {
                player1.sendError("Имя первого игрока и второго игрока совпадает. Перезапустите клиентов.");
                player2.sendError("Имя первого игрока и второго игрока совпадает. Перезапустите клиентов.");
                player1.close();
                player2.close();
                return;
            }

            new GameSession(player1, player2).run();
        } catch (Exception e) {
            System.out.println("Ошибка сервера: " + e.getMessage());
        }
    }
}
