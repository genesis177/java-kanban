import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest implements EpicTest1 {
    private Epic epic;

    @BeforeEach
    @Override
    public void setUp() {
        epic = new Epic("Epic Title", "Epic Description");
    }

    @Test
    @Override
    public void testAddSubtask() {
        epic.addSubtask(1);
        List<Integer> subtaskIds = epic.getSubtaskIds();
        assertEquals(1, subtaskIds.size());
        assertEquals(1, subtaskIds.get(0).intValue());
    }

    @Test
    @Override
    public void testClearSubtasks() {
        epic.addSubtask(1);
        epic.addSubtask(2);
        epic.clearSubtasks();
        assertTrue(epic.getSubtaskIds().isEmpty());
    }

    @Test
    @Override
    public void testGetSubtaskIds() {
        epic.addSubtask(1);
        epic.addSubtask(2);
        List<Integer> subtaskIds = epic.getSubtaskIds();
        assertEquals(2, subtaskIds.size());
        assertTrue(subtaskIds.contains(1));
        assertTrue(subtaskIds.contains(2));
    }
}