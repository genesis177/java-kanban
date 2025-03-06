package EpicTest1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private Epic epic;

    @BeforeEach
    void setUp() {
        epic = new Epic("Epic Title", "Epic Description");
    }

    @Test
    void testAddSubtask() {
        epic.addSubtask(1);
        List<Integer> subtaskIds = epic.getSubtaskIds();
        assertEquals(1, subtaskIds.size());
        assertEquals(1, subtaskIds.get(0).intValue());
    }

    @Test
    void testClearSubtasks() {
        epic.addSubtask(1);
        epic.addSubtask(2);
        epic.clearSubtasks();
        Assertions.assertTrue(epic.getSubtaskIds().isEmpty());
    }

    @Test
    void testGetSubtaskIds() {
        epic.addSubtask(1);
        epic.addSubtask(2);
        List<Integer> subtaskIds = epic.getSubtaskIds();
        assertEquals(2, subtaskIds.size());
        assertTrue(subtaskIds.contains(1));
        assertTrue(subtaskIds.contains(2));
    }
}