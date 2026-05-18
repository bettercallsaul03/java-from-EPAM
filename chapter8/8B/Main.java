/*
Слова текста, начинающиеся с гласных букв, рассортировать в алфавитном 
порядке по первой согласной букве слова. 
*/



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String text = Files.readString(Path.of("input.txt")).toLowerCase();
        String[] words = text.split("\\W+");

        List<String> list = new ArrayList<>();

        String vowels = "aeiouyаеёиоуыэюя";

        for (String w : words) {
            if (w.length() > 0 && vowels.indexOf(w.charAt(0)) != -1) {
                list.add(w);
            }
        }

        list.sort((a, b) -> {
            char ca = getFirstConsonant(a, vowels);
            char cb = getFirstConsonant(b, vowels);
            return Character.compare(ca, cb);
        });

        for (String w : list) {
            System.out.println(w);
        }
    }

    static char getFirstConsonant(String word, String vowels) {
        for (char c : word.toCharArray()) {
            if (vowels.indexOf(c) == -1) {
                return c;
            }
        }
        return 'z';
    }
}
