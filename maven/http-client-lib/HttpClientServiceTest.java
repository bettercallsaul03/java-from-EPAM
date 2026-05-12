package ru.lab.http;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;

public class HttpClientServiceTest {

    private static HttpServer server;

    private final HttpClientService service =
            new HttpClientService();

    @BeforeAll
    static void startServer() throws IOException {

        server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        server.createContext("/get", exchange -> {

            String response = "GET_OK";

            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();

            os.write(response.getBytes());

            os.close();
        });

        server.createContext("/post", exchange -> {

            String response = "POST_OK";

            exchange.sendResponseHeaders(201, response.length());

            OutputStream os = exchange.getResponseBody();

            os.write(response.getBytes());

            os.close();
        });

        server.start();
    }

    @AfterAll
    static void stopServer() {

        server.stop(0);
    }

    @Test
    void testGetRequest() {

        HttpResponseData response =
                service.sendGet("http://localhost:8080/get");

        assertEquals(200, response.statusCode());

        assertEquals("GET_OK", response.body());
    }

    @Test
    void testPostRequest() {

        HttpResponseData response =
                service.sendPost(
                        "http://localhost:8080/post",
                        "{\"name\":\"Ivan\"}"
                );

        assertEquals(201, response.statusCode());

        assertEquals("POST_OK", response.body());
    }

    @Test
    void testJsonSerialization() {

        User user = new User("Ivan", 20);

        String json = service.toJson(user);

        assertTrue(json.contains("Ivan"));
    }

    @Test
    void testJsonParsing() {

        String json = """
                {
                    "name":"Alex",
                    "age":25
                }
                """;

        User user = service.fromJson(json, User.class);

        assertEquals("Alex", user.name());
        assertEquals(25, user.age());
    }

    @Test
    void testInvalidJson() {

        assertThrows(
                HttpException.class,
                () -> service.fromJson("bad json", User.class)
        );
    }

    record User(String name, int age) {
    }
}
