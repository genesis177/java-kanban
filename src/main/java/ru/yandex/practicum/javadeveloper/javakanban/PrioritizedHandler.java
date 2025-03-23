package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PrioritizedHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;

    public PrioritizedHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            // Логика обработки GET-запроса
        } else if ("PATCH".equals(exchange.getRequestMethod())) {
            // Логика обработки PATCH-запроса
        } else if ("PUT".equals(exchange.getRequestMethod())) {
            // Логика обработки PUT-запроса
        } else {
            sendText(exchange, "Method not allowed", 405);
        }
    }
}
