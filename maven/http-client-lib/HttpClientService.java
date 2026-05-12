package ru.lab.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientService {

    private final HttpClient client;
    private final ObjectMapper mapper;

    public HttpClientService() {

        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public HttpResponseData sendGet(String url) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return new HttpResponseData(
                    response.statusCode(),
                    response.body()
            );

        } catch (IOException | InterruptedException e) {

            throw new HttpException("Ошибка GET запроса", e);
        }
    }

    public HttpResponseData sendPost(String url, String json) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return new HttpResponseData(
                    response.statusCode(),
                    response.body()
            );

        } catch (IOException | InterruptedException e) {

            throw new HttpException("Ошибка POST запроса", e);
        }
    }

    public String toJson(Object object) {

        try {

            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {

            throw new HttpException("Ошибка преобразования в JSON", e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {

        try {

            return mapper.readValue(json, clazz);

        } catch (JsonProcessingException e) {

            throw new HttpException("Ошибка чтения JSON", e);
        }
    }
}
