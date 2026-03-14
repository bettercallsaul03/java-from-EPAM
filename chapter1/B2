import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите 5 чисел через пробел:");
        
        int min = scanner.nextInt();
        int max = min;
        
        for (int i = 0; i < 4; i++) {
            int num = scanner.nextInt(); 
            
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        System.out.println("Наименьшее: " + min);
        System.out.println("Наибольшее: " + max);
    }
}
