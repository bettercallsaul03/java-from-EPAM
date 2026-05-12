package ru.lab.weather;

public class Main {

    public static void main(String[] args) {

        DatabaseManager manager =
                new DatabaseManager();

        manager.createTables();

        manager.insertTestData();

        DatabaseQueries queries =
                new DatabaseQueries();

        System.out.println(
                "Погода в регионе:"
        );

        queries.showWeatherByRegion(
                "Северный"
        );

        System.out.println(
                "\nДаты со снегом и температурой ниже -10:"
        );

        queries.showSnowDates(
                "Северный",
                -10
        );

        System.out.println(
                "\nПогода в регионах с русским языком:"
        );

        queries.showWeatherByLanguage(
                "Русский"
        );

        System.out.println(
                "\nСредняя температура:"
        );

        queries.showAverageTemperature(
                100000
        );
    }
}
