package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class EpicHandler extends BaseHttpHandler implements HttpHandler {
    private final TaskManager taskManager;
    private final Gson gson;

    public EpicHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.gson = new Gson();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                handleGet(exchange);
                break;
            case "POST":
                handlePost(exchange);
                break;
            case "PUT":
                handlePut(exchange);
                break;
            case "PATCH":
                handlePatch(exchange);
                break;
            case "DELETE":
                handleDelete(exchange);
                break;
            default:
                sendText(exchange, "Метод не разрешён", 405);
                break;
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        List<Epic> epics = taskManager.getAllEpics();
        String jsonResponse = gson.toJson(epics);
        sendText(exchange, jsonResponse, 200);
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        String requestBody = readRequestBody(exchange);
        Epic epic = gson.fromJson(requestBody, Epic.class);
        taskManager.createEpic(epic);
        sendText(exchange, "Эпик создан", 201);
    }

    private void handlePut(HttpExchange exchange) throws IOException {
        String requestBody = readRequestBody(exchange);
        Epic epic = gson.fromJson(requestBody, Epic.class);
        taskManager.updateEpic(epic);
        sendText(exchange, "Эпик обновлён", 200);
    }

    private void handlePatch(HttpExchange exchange) throws IOException {
        String requestBody = readRequestBody(exchange);
        Epic epic = gson.fromJson(requestBody, Epic.class);
        taskManager.updateEpic(epic); // Здесь может быть логика для частичного обновления
        sendText(exchange, "Эпик частично обновлён", 200);
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        int id = extractIdFromQuery(query);
        taskManager.deleteEpic(id);
        sendText(exchange, "Эпик удалён", 200);
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }

    private int extractIdFromQuery(String query) {

        if (query != null && query.startsWith("id=")) {
            return Integer.parseInt(query.substring(3));
        }
        return -1;
    }
}



