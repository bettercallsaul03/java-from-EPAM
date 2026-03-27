import java.util.Scanner;

public class MatrixDet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите размерность квадратной матрицы (2 или 3):");
        int n = sc.nextInt();

        double[][] matrix = new double[n][n];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }

        double det = 0;

        if (n == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][2];
        } else if (n == 3) {
            det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        } else {
            System.out.println("Извините, этот простой код поддерживает только матрицы 2x2 и 3x3.");
            return;
        }

        System.out.println("Определитель матрицы равен: " + det);
    }
}
