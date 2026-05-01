/*
Выполнить задания из варианта B гл. 4, сохраняя объекты приложения в одном или нескольких файлах с применением механизма сериализации.
Объекты могут содержать поля, помеченные как static, а также transient.
Для изменения информации и извлечения информации в файле создать специальный класс- коннектор с необходимыми для выполнения этих задач методами.
задание из главы 4: Транспорт.
Определить иерархию подвижного состава железнодорожного транспорта. Создать пассажирский поезд. Подсчитать общую численность пассажиров и багажа.
Провести сортировку вагонов поезда на основе уровня комфортности. Найти в поезде вагоны, соответствующие заданному диапазону параметров числа пассажиров.
*/


import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Train train = new Train("Пассажирский поезд №12");

        GeneralWagon w1 = new GeneralWagon(1, 60, 40);
        PlatzkartWagon w2 = new PlatzkartWagon(2, 54, 54);
        CoupeWagon w3 = new CoupeWagon(3, 36, 36);
        SVWagon w4 = new SVWagon(4, 18, 18);
        LuxWagon w5 = new LuxWagon(5, 8, 8);

        w1.setServiceNote("Служебная пометка перед сохранением");

        train.addWagon(w1);
        train.addWagon(w2);
        train.addWagon(w3);
        train.addWagon(w4);
        train.addWagon(w5);

        TrainFileConnector connector = new TrainFileConnector();
        String fileName = "train.ser";

        try {
            connector.saveTrain(fileName, train);

            Train loadedTrain = connector.loadTrain(fileName);

            System.out.println("Исходные данные поезда:");
            System.out.println(loadedTrain);

            System.out.println("Общая численность пассажиров: " + loadedTrain.getTotalPassengers());
            System.out.println("Общая численность багажа: " + loadedTrain.getTotalBaggage());

            System.out.println("\nПроверка transient-поля после загрузки:");
            System.out.println("Примечание у первого вагона: " + loadedTrain.getWagons().get(0).getServiceNote());

            loadedTrain.sortWagonsByComfort();
            System.out.println("\nВагоны после сортировки по уровню комфортности:");
            for (Wagon wagon : loadedTrain.getWagons()) {
                System.out.println(wagon);
            }

            int minPassengers = 20;
            int maxPassengers = 55;
            List<Wagon> found = loadedTrain.findWagonsByPassengerRange(minPassengers, maxPassengers);

            System.out.println("\nВагоны с числом пассажиров от " + minPassengers + " до " + maxPassengers + ":");
            for (Wagon wagon : found) {
                System.out.println(wagon);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}
