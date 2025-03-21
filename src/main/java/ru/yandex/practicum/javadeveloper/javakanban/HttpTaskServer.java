package ru.yandex.practicum.javadeveloper.javakanban;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer {
    private final TaskManager taskManager;
    private HttpServer server;

    public HttpTaskServer(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void start() {
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/tasks", new ru.yandex.practicum.javadeveloper.javakanban.http.handler.TaskHandler(taskManager));
            server.createContext("/subtasks", new ru.yandex.practicum.javadeveloper.javakanban.http.handler.SubtaskHandler(taskManager));
            server.createContext("/epics", new EpicHandler(taskManager));
            server.createContext("/history", new HistoryHandler(taskManager));
            server.createContext("/prioritized", new PrioritizedHandler(taskManager));
            server.setExecutor(null);
            server.start();
            System.out.println("Server started on port 8080");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        server.stop(0);
    }
}

