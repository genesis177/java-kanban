package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class EpicHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;

    public EpicHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        if ("GET".equals(requestMethod)) {
            // Логика обработки GET-запроса
        } else if ("POST".equals(requestMethod)) {
            // Логика обработки POST-запроса
        } else if ("PUT".equals(requestMethod)) {
            // Логика обработки PUT-запроса
        } else if ("PATCH".equals(requestMethod)) {
            // Логика обработки PATCH-запроса
        } else if ("DELETE".equals(requestMethod)) {
            // Логика обработки DELETE-запроса
        } else {
            sendText(exchange, "Method not allowed", 405);
        }
    }
}

