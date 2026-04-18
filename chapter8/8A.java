/*
Удалить из текста его часть, заключенную между двумя символами, кото- 
рые вводятся (например, между скобками «(» и «)» или между звездочками 
«*» и т.п.)
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        char start = '(';
        char end = ')';

        String text = Files.readString(Path.of("input.txt"));

        StringBuilder result = new StringBuilder();
        boolean inside = false;

        for (char c : text.toCharArray()) {
            if (c == start) {
                inside = true;
                continue;
            }
            if (c == end) {
                inside = false;
                continue;
            }
            if (!inside) {
                result.append(c);
            }
        }

        System.out.println(result.toString());
    }
}
