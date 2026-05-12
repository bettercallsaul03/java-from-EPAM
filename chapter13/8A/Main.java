/*
Погода. В БД хранится информация о погоде в различных регионах. Для погоды необходимо хранить: • регион; • дату; 
• температуру; • осадки. Для регионов необходимо хранить: • название; • площадь; • тип жителей. Для типов жителей необходимо хранить: 
• название; • язык общения. • Вывести сведения о погоде в заданном регионе.
• Вывести даты, когда в заданном регионе шел снег и температура была ниже заданной отрицательной.
• Вывести информацию о погоде за прошедшую неделю в регионах, жители которых общаются на заданном языке.
• Вывести среднюю температуру за прошедшую неделю в регионах с площадью больше заданной.
*/
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
