package ru.yandex.practicum.javadeveloper.javakanban;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TaskManagerTest {

    private TaskManager taskManager;

    @Test
    void testCreateTask() {
        Task task = new Task("New Task", "Description for new task");
        task.createTask(task);

        Task retrievedTask = task.getTask(task.getId());
        assertNotNull(retrievedTask);
        assertEquals("New Task", retrievedTask.getTitle());
        assertEquals("Description for new task", retrievedTask.getDescription());
    }

    @Test
    void testEpicStatus() {
        Epic epic = new Epic("Epic Title", "Epic Description");
        taskManager.createEpic(epic);

        Subtask subtask1 = new Subtask("Subtask 1", "Subtask 1 Description", epic.getId());
        Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2 Description", epic.getId());

        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.IN_PROGRESS);

        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);


        Epic retrievedEpic = taskManager.getEpic(epic.getId());
        assertEquals(Status.IN_PROGRESS, retrievedEpic.getStatus());
    }

    @Test
    void testOverlappingTasks() {
        // Проверка пересечения задач
    }
}
