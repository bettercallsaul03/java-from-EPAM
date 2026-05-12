package ru.lab.weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL =
            "jdbc:sqlite:weather.db";

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(URL);
    }

    public void createTables() {

        String createResidentTypes = """
                CREATE TABLE IF NOT EXISTS resident_types (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    language TEXT NOT NULL
                );
                """;

        String createRegions = """
                CREATE TABLE IF NOT EXISTS regions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    area REAL NOT NULL,
                    resident_type_id INTEGER NOT NULL,
                    FOREIGN KEY (resident_type_id)
                    REFERENCES resident_types(id)
                );
                """;

        String createWeather = """
                CREATE TABLE IF NOT EXISTS weather (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    region_id INTEGER NOT NULL,
                    weather_date TEXT NOT NULL,
                    temperature REAL NOT NULL,
                    precipitation TEXT NOT NULL,
                    FOREIGN KEY (region_id)
                    REFERENCES regions(id)
                );
                """;

        try (
                Connection connection = connect();
                Statement statement = connection.createStatement()
        ) {

            statement.execute(createResidentTypes);

            statement.execute(createRegions);

            statement.execute(createWeather);

        } catch (SQLException e) {

            System.out.println("Ошибка создания таблиц");

            e.printStackTrace();
        }
    }

    public void insertTestData() {

        try (
                Connection connection = connect();
                Statement statement = connection.createStatement()
        ) {

            statement.executeUpdate("""
                    INSERT INTO resident_types(name, language)
                    VALUES
                    ('Славяне', 'Русский'),
                    ('Германцы', 'Немецкий'),
                    ('Романцы', 'Французский');
                    """);

            statement.executeUpdate("""
                    INSERT INTO regions(name, area, resident_type_id)
                    VALUES
                    ('Северный', 150000, 1),
                    ('Западный', 80000, 2),
                    ('Южный', 200000, 3);
                    """);

            statement.executeUpdate("""
                    INSERT INTO weather(region_id, weather_date, temperature, precipitation)
                    VALUES
                    (1, '2026-05-10', -15, 'снег'),
                    (1, '2026-05-11', -5, 'снег'),
                    (1, '2026-05-12', 2, 'дождь'),

                    (2, '2026-05-10', 5, 'дождь'),
                    (2, '2026-05-11', 8, 'ясно'),

                    (3, '2026-05-10', 20, 'ясно'),
                    (3, '2026-05-11', 18, 'дождь');
                    """);

        } catch (SQLException e) {

            System.out.println("Ошибка заполнения БД");

            e.printStackTrace();
        }
    }
}
