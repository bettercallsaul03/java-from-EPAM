package ru.lab.app;

import ru.lab.http.HttpClientService;

public class Main {

    public static void main(String[] args) {

        HttpClientService service =
                new HttpClientService();

        User user = new User(
                "Дмитрий",
                67
        );

        String json = service.toJson(user);

        System.out.println("JSON:");

        System.out.println(json);

        User parsed =
                service.fromJson(json, User.class);

        System.out.println("\nОбъект:");

        System.out.println(parsed);
    }
}
