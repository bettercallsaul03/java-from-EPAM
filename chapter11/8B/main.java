import java.io.*;
import java.util.*;

class ObjectItem {

    private String name;
    private int code;

    public ObjectItem(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Шифр: " + code + ", объект: " + name;
    }
}

public class Main {

    public static void main(String[] args) {

        List<ObjectItem> z = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            String line;
            int code = 1;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    z.add(new ObjectItem(line.trim(), code));
                    code++;
                }
            }

            reader.close();

            // Сортировка по шифру
            z.sort(Comparator.comparingInt(ObjectItem::getCode));

            System.out.println("Список Z:");

            for (ObjectItem item : z) {
                System.out.println(item);
            }

            // Удаление дубликатов
            List<ObjectItem> compressed = new ArrayList<>();
            Set<String> uniqueNames = new HashSet<>();

            for (ObjectItem item : z) {

                if (!uniqueNames.contains(item.getName())) {

                    uniqueNames.add(item.getName());
                    compressed.add(item);
                }
            }

            System.out.println("\nСжатый список Z:");

            for (ObjectItem item : compressed) {
                System.out.println(item);
            }

        } catch (IOException e) {

            System.out.println("Ошибка при чтении файла.");
        }
    }
}
