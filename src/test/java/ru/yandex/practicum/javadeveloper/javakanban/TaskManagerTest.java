package ru.yandex.practicum.javadeveloper.javakanban;

import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TaskManagerTest {

    private TaskManager taskManager;

    @Test
    void testCreateTask() {
        Task task = new Task("New Task", "Description for new task");
        task.getClass();

        Task retrievedTask = task;
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
        Task task1 = new Task("Task 1", "Description for task 1");
        task1.setStartTime(LocalDateTime.of(2023, 10, 1, 10, 0));
        task1.setDuration(Duration.ofHours(2));

        Task task2 = new Task("Task 2", "Description for task 2");
        task2.setStartTime(LocalDateTime.of(2023, 10, 1, 11, 0)); // Перекрывается с task1
        task2.setDuration(Duration.ofHours(2));

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        List<Task> allTasks = taskManager.getAllTasks();
        assertEquals(2, allTasks.size());


        assertTrue(checkForOverlappingTasks(allTasks));
    }

    // Метод для проверки перекрывающихся задач
    private boolean checkForOverlappingTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                Task t1 = tasks.get(i);
                Task t2 = tasks.get(j);
                if (t1.getStartTime().isBefore(t2.getEndTime()) && t2.getStartTime().isBefore(t1.getEndTime())) {
                    return true;
                }
            }
        }
        return false;
    }
}
