import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String text = """
                Неуверенность

                Когда тебя нет рядом,
                Мне грустно и тревожно.
                Я думаю о многом,
                Но все понять так сложно.

                Когда ты улыбаешься,
                В душе светлее станет.
                И сердце вновь надеется,
                И верить не устанет.
                """;

       
        text = text.toLowerCase();

        text = text.replaceAll("[^а-яa-zё\\s]", "");

       
        Map<Character, Integer> letterCount = new HashMap<>();

        for (char ch : text.toCharArray()) {

            if (ch != ' ') {

                if (letterCount.containsKey(ch)) {
                    letterCount.put(ch, letterCount.get(ch) + 1);
                } else {
                    letterCount.put(ch, 1);
                }

            }
        }

      
        Map<String, Integer> wordCount = new HashMap<>();

        String[] words = text.split("\\s+");

        for (String word : words) {

            if (!word.isEmpty()) {

                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1);
                }

            }
        }

       
        System.out.println("Стихотворение:\n");
        System.out.println(text);

        
        System.out.println("\nЧастота повторяемости букв:");

        for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        
        System.out.println("\nЧастота повторяемости слов:");

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
