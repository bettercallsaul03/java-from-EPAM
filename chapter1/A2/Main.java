import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите слова через пробел и нажмите Enter: ");
        String input = scanner.nextLine();


        if (input.isEmpty()) {
            System.out.println("Вы ничего не ввели");
        } else {
            
            String[] words = input.split(" ");

            System.out.println("Слова в обратном порядке: ");


            for (int i = words.length - 1; i >= 0; i--) {
                System.out.print(words[i] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
