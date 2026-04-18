/*
Задания к главе 9 
Вариант A 
В символьном файле находится информация об N числах с плавающей запятой 
с указанием локали каждого числа отдельно. Прочитать информацию из файла. 
Проверить на корректность, то есть являются ли числа числами. Преобразовать 
к числовым значениям и вычислить сумму и среднее значение прочитанных чисел. 
Создать собственный класс исключения. Предусмотреть обработку исклю- 
чений, возникающих при нехватке памяти, отсутствии самого файла по задан- 
ному адресу, отсутствии или некорректности требуемой записи в файле, недо- 
пустимом значении числа (выходящим за пределы максимально допустимых 
значений) и т.д.
*/
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String fileName = "input.txt";

        try {
            Result result = NumberService.readNumbers(fileName);
            System.out.println("Sum = " + result.sum);
            System.out.println("Average = " + result.avg);
        } catch (AppException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        } catch (OutOfMemoryError e) {
            System.out.println("Не хватило памяти");
        }
    }
}
