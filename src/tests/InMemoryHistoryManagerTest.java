package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.history.InMemoryHistoryManager;
import service.tasks.Epic;
import service.tasks.Subtask;
import service.tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.history.InMemoryHistoryManager;
import service.tasks.Epic;
import service.tasks.Subtask;
import service.tasks.Task;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    private InMemoryHistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void testAddTaskWithMinId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(1);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
    }

    @Test
    public void testAddTaskWithMaxId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(Integer.MAX_VALUE);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
    }

    @Test
    public void testAddTaskWithZeroId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(0);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
    }

    @Test
    public void testAddTaskWithNegativeId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(-1);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1,history.size());
        assertEquals(task, history.get(0));
    }



    @Test
    public void testRemoveTaskWithMinId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(1);
        historyManager.add(task);

        historyManager.remove(1);

        List<Task> history = historyManager.getHistory();
        assertEquals(0, history.size());
    }

    @Test
    public void testRemoveTaskWithMaxId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(Integer.MAX_VALUE);
        historyManager.add(task);

        historyManager.remove(Integer.MAX_VALUE);

        List<Task> history = historyManager.getHistory();
        assertEquals(0, history.size());
    }

    @Test
    public void testRemoveTaskWithZeroId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(0);
        historyManager.add(task);

        historyManager.remove(0);

        List<Task> history = historyManager.getHistory();
        assertEquals(0, history.size());
    }

    @Test
    public void testRemoveTaskWithNegativeId() {
        Task task = new Task("Task 1", "Description 1");
        task.setId(-1);
        historyManager.add(task);

        historyManager.remove(-1);

        List<Task> history = historyManager.getHistory();
        assertEquals(0, history.size());
    }

}