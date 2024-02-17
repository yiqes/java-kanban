package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.managers.InMemoryTaskManager;
import service.status.Status;
import service.tasks.Epic;
import service.tasks.Subtask;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerUpdateSubTaskTest {

    private InMemoryTaskManager taskManager;
    private Epic epic;
    private Subtask subtask1;
    private Subtask subtask2;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();

        epic = new Epic("Epic title", "Epic description", new ArrayList<>());
        taskManager.createEpicTask(epic);

        subtask1 = new Subtask("Subtask 1 title", "Subtask 1 description", "NEW", epic.getId());
        taskManager.createSubTask(subtask1);

        subtask2 = new Subtask("Subtask 2 title", "Subtask 2 description", "NEW", epic.getId());
        taskManager.createSubTask(subtask2);
    }

    @Test
    void shouldUpdateSubtaskStatusToInProgressWhenEpicHasInProgressSubtasks() {
        subtask1.setStatus(Status.IN_PROGRESS.toString());
        taskManager.updateSubTask(subtask1.getId(), subtask1);

        assertEquals(Status.IN_PROGRESS.toString(), epic.getStatus());
    }

    @Test
    void shouldUpdateSubtaskStatusToDoneWhenEpicHasNoInProgressSubtasks() {
        subtask1.setStatus(Status.DONE.toString());
        subtask2.setStatus(Status.DONE.toString());
        taskManager.updateSubTask(subtask1.getId(), subtask1);
        taskManager.updateSubTask(subtask2.getId(), subtask2);

        assertEquals(Status.DONE.toString(), epic.getStatus());
    }

    @Test
    void shouldUpdateSubtaskStatusToInProgressWhenEpicHasNoDoneSubtasks() {
        subtask1.setStatus(Status.IN_PROGRESS.toString());
        taskManager.updateSubTask(subtask1.getId(), subtask1);

        assertEquals(Status.IN_PROGRESS.toString(), epic.getStatus());
    }

    @Test
    void shouldUpdateSubtaskStatusToDoneWhenEpicHasMixedSubtaskStatuses() {
        subtask1.setStatus(Status.DONE.toString());
        taskManager.updateSubTask(subtask1.getId(), subtask1);

        assertEquals(Status.IN_PROGRESS.toString(), epic.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenEpicIsNull() {
        epic = null;

        assertThrows(IllegalArgumentException.class, () -> taskManager.updateSubTask(subtask1.getId(), subtask1));
    }

    @Test
    void shouldThrowExceptionWhenSubtaskNotFound() {
        assertThrows(IllegalArgumentException.class, () -> taskManager.updateSubTask(-1, subtask1));
    }
}

