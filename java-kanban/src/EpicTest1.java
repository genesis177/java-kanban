import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public interface EpicTest1 {
    @BeforeEach
    void setUp();

    @Test
    void testAddSubtask();

    @Test
    void testClearSubtasks();

    @Test
    void testGetSubtaskIds();
}
