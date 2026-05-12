package ru.lab.http;

public record HttpResponseData(
        int statusCode,
        String body
) {
}
