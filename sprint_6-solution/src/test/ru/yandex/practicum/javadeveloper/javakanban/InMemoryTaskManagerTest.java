package ru.yandex.practicum.javadeveloper.javakanban;


import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.Test;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class InMemoryTaskManagerTest {


    private TaskManager taskManager;


    @BeforeEach
    void setUp() {


        taskManager = Managers.getDefault();


    }


    @Test
    public void testAddAndGetTask() {


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


}