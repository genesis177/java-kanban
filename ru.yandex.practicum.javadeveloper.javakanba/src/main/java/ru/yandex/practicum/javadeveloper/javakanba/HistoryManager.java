package main.java.ru.yandex.practicum.javadeveloper.javakanba;

import java.util.List;

public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
}
