package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HistoryHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;

    public HistoryHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                // Логика обработки GET-запроса

                sendText(exchange, "История задач", 200);
                break;
            default:
                sendText(exchange, "Метод не разрешён", 405);
                break;
        }
    }
}
