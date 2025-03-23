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
        switch (exchange.getRequestMethod()) {
            case "GET":
                // Логика обработки GET-запроса

                sendText(exchange, "Приоритетные задачи", 200);
                break;
            case "PATCH":
                // Логика обработки PATCH-запроса
                sendText(exchange, "Приоритет задачи обновлён", 200);
                break;
            case "PUT":
                // Логика обработки PUT-запроса
                sendText(exchange, "Приоритет задачи обновлён", 200);
                break;
            default:
                sendText(exchange, "Метод не разрешён", 405);
                break;
        }
    }
}
