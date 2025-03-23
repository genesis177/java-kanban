package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class EpicHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;

    public EpicHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    //Могу ли я оставить реализацию case? Мне так удобнее и понятнее

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        switch (requestMethod) {
            case "GET":
                // Логика обработки GET-запроса

                sendText(exchange, "Список эпиков", 200);
                break;
            case "POST":
                // Логика обработки POST-запроса

                sendText(exchange, "Эпик создан", 201);
                break;
            case "PUT":
                // Логика обработки PUT-запроса

                sendText(exchange, "Эпик обновлён", 200);
                break;
            case "PATCH":
                // Логика обработки PATCH-запроса

                sendText(exchange, "Эпик частично обновлён", 200);
                break;
            case "DELETE":
                // Логика обработки DELETE-запроса

                sendText(exchange, "Эпик удалён", 200);
                break;
            default:
                sendText(exchange, "Метод не разрешён", 405);
                break;
        }
    }
}



