package ru.yandex.practicum.javadeveloper.javakanban;

import java.util.ArrayList;
import java.util.List;

// Класс Epic представляет собой задачу, которая может содержать подзадачи.
public class Epic extends Task {
    // Список идентификаторов подзадач, связанных с этой задачей.
    private List<Integer> subtaskIds;

    // Конструктор класса Epic, который принимает заголовок и описание задачи.
    public Epic(String title, String description) {
        // Вызов конструктора родительского класса Task для инициализации заголовка и описания.
        super(title, description);

        // Инициализация списка идентификаторов подзадач как нового ArrayList.
        this.subtaskIds = new ArrayList<>();
    }

    // Метод для добавления идентификатора подзадачи в список подзадач.
    public void addSubtask(int subtaskId) {
        // Добавляет идентификатор подзадачи в список.
        subtaskIds.add(subtaskId);
    }

    // Метод для получения списка идентификаторов подзадач.
    public List<Integer> getSubtaskIds() {
        // Возвращает список идентификаторов подзадач.
        return subtaskIds;
    }

    // Метод для очистки списка подзадач, удаляя все идентификаторы.
    public void clearSubtasks() {
        // Очищает список идентификаторов подзадач.
        subtaskIds.clear();
    }
}