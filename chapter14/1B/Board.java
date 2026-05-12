import java.util.ArrayDeque;
import java.util.Random;

public class Board {

    private static final int SIZE = 10;
    private static final char EMPTY = '.';
    private static final char SHIP = '#';
    private static final char HIT = 'X';
    private static final char MISS = '*';

    private final char[][] cells = new char[SIZE][SIZE];
    private final Random random = new Random();

    public Board() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = EMPTY;
            }
        }
        placeFleet();
    }

    private void placeFleet() {
        int[] ships = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

        for (int length : ships) {
            boolean placed = false;

            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(horizontal ? SIZE : SIZE - length + 1);
                int col = random.nextInt(horizontal ? SIZE - length + 1 : SIZE);

                if (canPlace(row, col, length, horizontal)) {
                    putShip(row, col, length, horizontal);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlace(int row, int col, int length, boolean horizontal) {
        int rowFrom = Math.max(0, row - 1);
        int rowTo = Math.min(SIZE - 1, row + (horizontal ? 1 : length));
        int colFrom = Math.max(0, col - 1);
        int colTo = Math.min(SIZE - 1, col + (horizontal ? length : 1));

        for (int r = rowFrom; r <= rowTo; r++) {
            for (int c = colFrom; c <= colTo; c++) {
                if (cells[r][c] != EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    private void putShip(int row, int col, int length, boolean horizontal) {
        for (int i = 0; i < length; i++) {
            if (horizontal) {
                cells[row][col + i] = SHIP;
            } else {
                cells[row + i][col] = SHIP;
            }
        }
    }

    public ShotResult shoot(int row, int col) {
        if (!isValid(row, col)) {
            return ShotResult.INVALID;
        }

        if (cells[row][col] == HIT || cells[row][col] == MISS) {
            return ShotResult.REPEAT;
        }

        if (cells[row][col] == SHIP) {
            cells[row][col] = HIT;

            if (isShipSunk(row, col)) {
                if (allShipsDestroyed()) {
                    return ShotResult.WIN;
                }
                return ShotResult.SUNK;
            }

            return ShotResult.HIT;
        }

        cells[row][col] = MISS;
        return ShotResult.MISS;
    }

    private boolean isShipSunk(int row, int col) {
        boolean[][] visited = new boolean[SIZE][SIZE];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.removeFirst();
            int r = current[0];
            int c = current[1];

            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    if (cells[nr][nc] == SHIP) {
                        return false;
                    }
                    if (cells[nr][nc] == HIT) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return true;
    }

    private boolean allShipsDestroyed() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (cells[r][c] == SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public String renderOwnBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (int c = 1; c <= SIZE; c++) {
            sb.append(String.format("%2d ", c));
        }
        sb.append('\n');

        for (int r = 0; r < SIZE; r++) {
            sb.append(String.format("%2d  ", r + 1));
            for (int c = 0; c < SIZE; c++) {
                sb.append(' ').append(cells[r][c]).append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public String renderEnemyBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (int c = 1; c <= SIZE; c++) {
            sb.append(String.format("%2d ", c));
        }
        sb.append('\n');

        for (int r = 0; r < SIZE; r++) {
            sb.append(String.format("%2d  ", r + 1));
            for (int c = 0; c < SIZE; c++) {
                char ch = cells[r][c];
                char view = (ch == HIT || ch == MISS) ? ch : '.';
                sb.append(' ').append(view).append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
