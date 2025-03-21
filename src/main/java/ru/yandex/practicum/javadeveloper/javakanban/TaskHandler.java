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
        switch (exchange.getRequestMethod()) {
            case "GET":

                break;
            case "POST":

                break;
            case "DELETE":

                break;
            default:
                sendText(exchange, "Method not allowed", 405);
                break;
        }
    }
}