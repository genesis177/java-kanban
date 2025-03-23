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

        switch (requestMethod) {
            case "GET":
                // Обработка GET-запроса
                sendText(exchange, "Список задач", 200);
                break;
            case "POST":
                // Обработка POST-запроса
                sendText(exchange, "Задача создана", 201);
                break;
            case "DELETE":
                // Обработка DELETE-запроса
                sendText(exchange, "Задача удалена", 200);
                break;
            default:
                sendText(exchange, "Метод не разрешён", 405);
                break;
        }
    }
}