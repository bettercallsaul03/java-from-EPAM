import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Train myTrain = new Train();

        myTrain.addVagon(new PassengerVagon(1, 5, 18, 12));
        myTrain.addVagon(new PassengerVagon(2, 3, 36, 25));
        myTrain.addVagon(new PassengerVagon(3, 1, 54, 40));
        myTrain.addVagon(new PassengerVagon(4, 2, 48, 30));

        System.out.println("Сформирован новый состав поезда.");
        System.out.println("Всего людей в поезде: " + myTrain.calculateTotalPassengers());
        System.out.println("Всего единиц багажа: " + myTrain.calculateTotalBaggage());

        System.out.println("\nВыполняю сортировку вагонов по комфорту ");
        myTrain.sortVagons();
        for (Vagon v : myTrain.vagons) {
            System.out.println(v);
        }

        System.out.println("\nВведите диапазон пассажиров для поиска вагонов.");
        System.out.print("От: ");
        int min = input.nextInt();
        System.out.print("До: ");
        int max = input.nextInt();

        List<Vagon> result = myTrain.findVagonsByCapacity(min, max);
        if (result.isEmpty()) {
            System.out.println("Подходящих вагонов не найдено.");
        } else {
            System.out.println("Найденные вагоны:");
            for (Vagon v : result) {
                System.out.println(v);
            }
        }
    }
}
