package ru.yandex.practicum.javadeveloper.javakanban;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTaskManagerTest {

    private FileBackedTaskManager fileBackedTaskManager;
    private final File file = new File("test_tasks.csv");

    @BeforeEach
    void setUp() {
        fileBackedTaskManager = Managers.getFileBackedTaskManager(file);
    }

    @AfterEach
    void tearDown() {
        file.delete();
    }

    @Test
    void testCreateAndLoadTask() throws ManagerSaveException {
        Task task = new Task("Test Task", "Test Description");
        fileBackedTaskManager.createTask(task);

        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(file);
        Task loadedTask = loadedManager.getTask(task.getId());

        assertNotNull(loadedTask);
        assertEquals(task.getTitle(), loadedTask.getTitle());
        assertEquals(task.getDescription(), loadedTask.getDescription());
    }
    }