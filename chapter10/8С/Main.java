/*
Прочитать текст Java-программы и удалить из него все лишние пробелы и табуляции, оставив только необходимые для разделения операторов.
*/




import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст Java-программы:");
        System.out.println("Для завершения ввода введите пустую строку.");

        StringBuilder code = new StringBuilder();

        while (true) {

            String line = scanner.nextLine();

            if (line.isEmpty()) {
                break;
            }

            code.append(line).append("\n");
        }

        String text = code.toString();

        
        text = text.replaceAll("[\\t ]+", " ");

       
        text = text.replaceAll("(?m)^\\s+", "");

        
        text = text.replaceAll("\\s*([;{}(),])\\s*", "$1");

        
        text = text.replaceAll("if\\(", "if (");
        text = text.replaceAll("for\\(", "for (");
        text = text.replaceAll("while\\(", "while (");
        text = text.replaceAll("switch\\(", "switch (");

        System.out.println("\nТекст программы после удаления лишних пробелов:\n");
        System.out.println(text);

        scanner.close();
    }
}
