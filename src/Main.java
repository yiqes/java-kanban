import service.history.InMemoryHistoryManager;
import service.tasks.Epic;
import service.tasks.Subtask;
import service.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        // Create tasks, epic with 3 subtasks, and another epic without subtasks
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        Epic epic1 = new Epic("Epic 1", "Description 1", new ArrayList<>());
        Subtask subtask11 = new Subtask("Subtask 1.1", "Description 1.1", "NEW", 1);
        Subtask subtask12 = new Subtask("Subtask 1.2", "Description 1.2", "NEW", 1);
        Subtask subtask13 = new Subtask("Subtask 1.3", "Description 1.3", "NEW", 1);
        Epic epic2 = new Epic("Epic 2", "Description 2", new ArrayList<>());

        // Add tasks, epics, and subtasks several times in different orders
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(epic1);
        historyManager.add(subtask11);
        historyManager.add(subtask12);
        historyManager.add(subtask13);
        historyManager.add(epic2);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(epic1);

        // Print history after each addition
        System.out.println("History after adding tasks, epics, and subtasks:");
        printHistory(historyManager);

        // Remove a task that exists in the history
        historyManager.remove(task1.getId());

        // Print history after removing a task
        System.out.println("\nHistory after removing task:");
        printHistory(historyManager);

        // Remove an epic with 3 subtasks
        historyManager.remove(epic1.getId());

        // Print history after removing an epic with subtasks
        System.out.println("\nHistory after removing an epic with subtasks:");
        printHistory(historyManager);
    }

    private static void printHistory(InMemoryHistoryManager historyManager) {
        List<Task> history = historyManager.getHistory();
        for (Task task : history) {
            System.out.println(task);
        }
    }
}