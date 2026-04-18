import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class NumberService {

    public static Result readNumbers(String fileName) throws IOException, AppException {

        double sum = 0;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.trim().split("\\s+");

                if (parts.length != 2) {
                    throw new AppException("Некорректная строка: " + line);
                }

                Locale locale = Locale.forLanguageTag(parts[0].replace('_', '-'));
                double value = parse(parts[1], locale);

                sum += value;
                count++;
            }
        }

        if (count == 0) {
            throw new AppException("Файл пустой");
        }

        return new Result(sum, sum / count);
    }

    private static double parse(String text, Locale locale) throws AppException {

        NumberFormat format = NumberFormat.getNumberInstance(locale);
        ParsePosition pos = new ParsePosition(0);
        Number number = format.parse(text, pos);

        if (number == null || pos.getIndex() != text.length()) {
            throw new AppException("Ошибка числа: " + text);
        }

        double val = number.doubleValue();

        if (!Double.isFinite(val)) {
            throw new AppException("Недопустимое значение: " + text);
        }

        return val;
    }
}
