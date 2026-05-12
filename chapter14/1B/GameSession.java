public class GameSession implements Runnable {

    private final PlayerConnection player1;
    private final PlayerConnection player2;
    private final Board board1 = new Board();
    private final Board board2 = new Board();

    public GameSession(PlayerConnection player1, PlayerConnection player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void run() {
        try {
            player1.sendInfo("Игра началась. Ваш соперник: " + player2.getName());
            player2.sendInfo("Игра началась. Ваш соперник: " + player1.getName());

            int current = 0;

            while (true) {
                PlayerConnection active = current == 0 ? player1 : player2;
                PlayerConnection enemy = current == 0 ? player2 : player1;
                Board enemyBoard = current == 0 ? board2 : board1;
                Board activeBoard = current == 0 ? board1 : board2;

                active.sendInfo("Ваше поле:");
                active.sendInfo(activeBoard.renderOwnBoard());
                active.sendInfo("Поле противника:");
                active.sendInfo(enemyBoard.renderEnemyBoard());
                active.sendTurn("Ваш ход. Введите координаты в формате: строка столбец (от 1 до 10)");

                int row = active.readInt();
                int col = active.readInt();

                ShotResult result = enemyBoard.shoot(row, col);

                if (result == ShotResult.INVALID || result == ShotResult.REPEAT) {
                    active.sendError(result.getMessage());
                    continue;
                }

                String coord = "(" + (row + 1) + ", " + (col + 1) + ")";

                switch (result) {
                    case MISS -> {
                        active.sendInfo("Выстрел " + coord + ": мимо.");
                        enemy.sendInfo(active.getName() + " выстрелил " + coord + ": мимо.");
                        current = 1 - current;
                    }
                    case HIT -> {
                        active.sendInfo("Выстрел " + coord + ": попадание! Ходите ещё раз.");
                        enemy.sendInfo(active.getName() + " попал по " + coord + ".");
                    }
                    case SUNK -> {
                        active.sendInfo("Выстрел " + coord + ": корабль потоплен! Ходите ещё раз.");
                        enemy.sendInfo(active.getName() + " потопил корабль по " + coord + ".");
                    }
                    case WIN -> {
                        active.sendInfo("Выстрел " + coord + ": последний корабль уничтожен!");
                        enemy.sendInfo("Ваш последний корабль уничтожен.");
                        active.sendGameOver("Вы победили!");
                        enemy.sendGameOver("Вы проиграли!");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            try {
                player1.sendError("Игра прервана: " + e.getMessage());
            } catch (Exception ignored) {
            }
            try {
                player2.sendError("Игра прервана: " + e.getMessage());
            } catch (Exception ignored) {
            }
        } finally {
            player1.close();
            player2.close();
        }
    }
}
