/*
  8 вариант, задание:Вывести количество вхождений заданного слова в тексте соответственно 
из файла в виде [слово1-2, слово2-3, слово3-0]. 
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] targetWords = {"слово1", "слово2", "слово3"};
        int[] counts = new int[targetWords.length];

        String text = Files.readString(Path.of("input.txt")).toLowerCase();
        String[] words = text.split("\\W+");

        for (String w : words) {
            for (int i = 0; i < targetWords.length; i++) {
                if (w.equals(targetWords[i])) {
                    counts[i]++;
                }
            }
        }

        System.out.print("[");
        for (int i = 0; i < targetWords.length; i++) {
            System.out.print(targetWords[i] + "-" + counts[i]);
            if (i != targetWords.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
