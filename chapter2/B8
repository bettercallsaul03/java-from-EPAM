import java.util.Scanner;

public class Converter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите число: ");
        String number = input.nextLine().toUpperCase();

        System.out.print("Введите исходную систему счисления (например, 16): ");
        int sourceBase = input.nextInt();

        System.out.print("Введите целевую систему счисления (например, 2): ");
        int targetBase = input.nextInt();

        try {
            long decimalValue = Long.parseLong(number, sourceBase);
            String result = Long.toString(decimalValue, targetBase).toUpperCase();

            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: Проверьте правильность ввода данных");
        }
    }
}
