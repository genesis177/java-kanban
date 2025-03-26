package ru.yandex.practicum.javadeveloper.javakanban;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class InMemoryTaskManagerTest {

    private TaskManager taskManager;
    private Duration duration;


    @BeforeEach
    void setUp() {


        taskManager = Managers.getDefault();

    }

    @Test
    void testAddAndGetTask() {

        Task task = new Task("Test Task", "Description");

        taskManager.createTask(task);

        Task retrievedTask = TaskManager.getTask(task.getId());

        assertNotNull(retrievedTask);

        assertEquals(task, retrievedTask);

    }


    @Test
    void testHistoryManagement() {

        Task task1 = new Task("Task 1", "Description 1");

        Task task2 = new Task("Task 2", "Description 2");


        taskManager.createTask(task1);


        taskManager.createTask(task2);


        TaskManager.getTask(task1.getId());


        TaskManager.getTask(task2.getId());


        List<Task> history = taskManager.getHistory();


        assertEquals(2, history.size());


        assertEquals(task1, history.get(0));


        assertEquals(task2, history.get(1));

    }
    @Test
    void testHistoryManagementWithDuplicates() {
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        TaskManager.getTask(task1.getId());
        TaskManager.getTask(task2.getId());
        TaskManager.getTask(task1.getId()); // Повторный просмотр task1

        List<Task> history = taskManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task2, history.get(0)); // task2 должен быть первым
        assertEquals(task1, history.get(1)); // task1 должен быть вторым
    }
    @Test
    void testCreateTaskWithDurationAndStartTime() {
        Task task = new Task("Test Task", "Description");
        task.setDuration(Duration.ofMinutes(30));
        task.setStartTime(LocalDateTime.now());

        taskManager.createTask(task);

        Task retrievedTask = TaskManager.getTask(task.getId());
        assertEquals(task.getDuration(duration), retrievedTask.getDuration(duration));
        assertEquals(task.getStartTime(), retrievedTask.getStartTime());
    }

}
