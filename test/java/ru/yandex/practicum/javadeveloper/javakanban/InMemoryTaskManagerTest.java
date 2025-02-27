package ru.yandex.practicum.javadeveloper.javakanban;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.javadeveloper.javakanban.Managers;
import ru.yandex.practicum.javadeveloper.javakanban.TaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class InMemoryTaskManagerTest {

    private TaskManager taskManager;


    @BeforeEach
    void setUp() {


        taskManager = Managers.getDefault();

    }

    @Test
    void testAddAndGetTask() {

        Task task = new Task("Test Task", "Description");

        taskManager.createTask(task);

        Task retrievedTask = taskManager.getTask(task.getId());

        assertNotNull(retrievedTask);

        assertEquals(task, retrievedTask);

    }


    @Test
    void testHistoryManagement() {

        Task task1 = new Task("Task 1", "Description 1");

        Task task2 = new Task("Task 2", "Description 2");


        taskManager.createTask(task1);


        taskManager.createTask(task2);


        taskManager.getTask(task1.getId());


        taskManager.getTask(task2.getId());


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

        taskManager.getTask(task1.getId());
        taskManager.getTask(task2.getId());
        taskManager.getTask(task1.getId()); // Повторный просмотр task1

        List<Task> history = taskManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task2, history.get(0)); // task2 должен быть первым
        assertEquals(task1, history.get(1)); // task1 должен быть вторым
    }

}
