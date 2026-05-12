package ru.lab.weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueries {

    private final DatabaseManager manager =
            new DatabaseManager();

    public void showWeatherByRegion(String regionName) {

        String sql = """
                SELECT
                    r.name,
                    w.weather_date,
                    w.temperature,
                    w.precipitation
                FROM weather w
                JOIN regions r
                    ON w.region_id = r.id
                WHERE r.name = ?
                """;

        try (
                Connection connection = manager.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, regionName);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getString("name")
                                + " | "
                                + resultSet.getString("weather_date")
                                + " | "
                                + resultSet.getDouble("temperature")
                                + " | "
                                + resultSet.getString("precipitation")
                );
            }

        } catch (SQLException e) {

            System.out.println("Ошибка запроса");

            e.printStackTrace();
        }
    }

    public void showSnowDates(
            String regionName,
            double temperature
    ) {

        String sql = """
                SELECT w.weather_date
                FROM weather w
                JOIN regions r
                    ON w.region_id = r.id
                WHERE r.name = ?
                  AND w.precipitation = 'снег'
                  AND w.temperature < ?
                """;

        try (
                Connection connection = manager.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, regionName);

            statement.setDouble(2, temperature);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getString("weather_date")
                );
            }

        } catch (SQLException e) {

            System.out.println("Ошибка запроса");

            e.printStackTrace();
        }
    }

    public void showWeatherByLanguage(
            String language
    ) {

        String sql = """
                SELECT
                    r.name,
                    w.weather_date,
                    w.temperature,
                    w.precipitation
                FROM weather w
                JOIN regions r
                    ON w.region_id = r.id
                JOIN resident_types rt
                    ON r.resident_type_id = rt.id
                WHERE rt.language = ?
                """;

        try (
                Connection connection = manager.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, language);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                System.out.println(
                        resultSet.getString("name")
                                + " | "
                                + resultSet.getString("weather_date")
                                + " | "
                                + resultSet.getDouble("temperature")
                                + " | "
                                + resultSet.getString("precipitation")
                );
            }

        } catch (SQLException e) {

            System.out.println("Ошибка запроса");

            e.printStackTrace();
        }
    }

    public void showAverageTemperature(
            double minArea
    ) {

        String sql = """
                SELECT AVG(w.temperature) AS avg_temp
                FROM weather w
                JOIN regions r
                    ON w.region_id = r.id
                WHERE r.area > ?
                """;

        try (
                Connection connection = manager.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDouble(1, minArea);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Средняя температура: "
                                + resultSet.getDouble("avg_temp")
                );
            }

        } catch (SQLException e) {

            System.out.println("Ошибка запроса");

            e.printStackTrace();
        }
    }
}
