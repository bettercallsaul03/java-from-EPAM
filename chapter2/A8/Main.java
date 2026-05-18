import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел: ");
        int n = scanner.nextInt();

        String[] arr = new String[n];
        System.out.println("Введите " + n + " чисел через пробел: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.next();
        }

        int count = 0;
        String firstAnswer = "";
        String secondAnswer = "";

        for (int i = 0; i < n; i++) {
            String s = arr[i];
            String reverse = "";

            for (int j = s.length() - 1; j >= 0; j--) {
                reverse = reverse + s.charAt(j);
            }

            if (s.equals(reverse)) {
                count++;

                if (count == 1) {
                    firstAnswer = s; 
                }

                if (count == 2) {
                    secondAnswer = s; 
                    break; 
                }
            }
        }
        
        if (count == 0) {
            System.out.println("Палиндромов нет");
        } else if (count == 1) {
            System.out.println("Один палиндром найден: " + firstAnswer);
        } else {
            System.out.println("Первое число-палиндром: " + firstAnswer);
            System.out.println("Второе число-палиндром: " + secondAnswer);
        }
    }
}
