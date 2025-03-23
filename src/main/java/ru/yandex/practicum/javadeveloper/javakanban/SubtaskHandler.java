package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class SubtaskHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;

    public SubtaskHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                // Логика обработки GET-запроса
                sendText(exchange, "Список подзадач", 200);
                break;
            case "POST":
                // Логика обработки POST-запроса
                sendText(exchange, "Подзадача создана", 201);
                break;
            case "DELETE":
                // Логика обработки DELETE-запроса
                sendText(exchange, "Подзадача удалена", 200);
                break;
            default:
                sendText(exchange, "Метод не разрешён", 405);
                break;
        }
    }
}
