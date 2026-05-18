/* С использованием множества выполнить попарное суммирование произ- 
вольного конечного ряда чисел по следующим правилам: на первом этапе 
суммируются попарно рядом стоящие числа, на втором этапе суммируются 
результаты первого этапа и т.д. до тех пор, пока не останется одно число. 
*/
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Set<Integer> numbers = new LinkedHashSet<>();

        System.out.println("Введите числа множества:");
        System.out.println("Для завершения введите 0");

        while (true) {

            int number = scanner.nextInt();

            if (number == 0) {
                break;
            }

            numbers.add(number);
        }

        System.out.println("\nИсходное множество:");
        System.out.println(numbers);

        while (numbers.size() > 1) {

            Integer[] array = numbers.toArray(new Integer[0]);

            Set<Integer> temp = new LinkedHashSet<>();

            System.out.println("\nЭтап суммирования:");

            for (int i = 0; i < array.length; i += 2) {

                if (i + 1 < array.length) {

                    int sum = array[i] + array[i + 1];

                    System.out.println(array[i] + " + " + array[i + 1] + " = " + sum);

                    temp.add(sum);

                } else {

                    System.out.println("Осталось число без пары: " + array[i]);

                    temp.add(array[i]);
                }
            }

            numbers = temp;
        }

        System.out.println("\nИтоговая сумма: " + numbers.iterator().next());

        scanner.close();
    }
}
