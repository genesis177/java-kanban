package ru.yandex.practicum.javadeveloper.javakanban;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public class TaskHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;

    public TaskHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        if ("GET".equals(requestMethod)) {
            // Обработка GET-запроса
        } else if ("POST".equals(requestMethod)) {
            // Обработка POST-запроса
        } else if ("DELETE".equals(requestMethod)) {
            // Обработка DELETE-запроса
        } else {
            sendText(exchange, "Method not allowed", 405);
        }
    }
}