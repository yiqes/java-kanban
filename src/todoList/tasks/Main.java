package todoList.tasks;

import todoList.service.TaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
     public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          String title = "";
          String description = "";
          String status = "";
          getValues(title, description, status, scanner);
          Task task = new Task(title, description, status);
          TaskManager taskManager = new TaskManager();
          printTask(taskManager, task);

          ArrayList<Integer> subtatskId = new ArrayList<>();
          getValues(title, description, status, scanner);
          Epic epic = new Epic(title, description, subtatskId);
          getValues(title, description, status, scanner);
          Subtask subtask = new Subtask(title, description, status, epic.getId());
          subtatskId.add(subtask.getId());

          printEpic(taskManager, epic);
          printSubtask(taskManager, subtask);
     }
     public static void getValues(String title, String description, String status, Scanner scanner) {
          System.out.println("Заголовок:");
          title = scanner.nextLine();
          System.out.println("Описание:");
          description = scanner.nextLine();
          System.out.println("Статус:");
          status = scanner.nextLine();
     }
     public static void printTask(TaskManager taskManager, Task task) {
          taskManager.createTask(task);
          HashMap<Integer, Task> taskList = taskManager.getTaskList();
          System.out.println(taskList.toString());
     }
     public static void printEpic(TaskManager taskManager, Epic epic) {
          taskManager.createEpicTask(epic);
          HashMap<Integer, Epic> epicTaskList = taskManager.getEpicTaskList();
          System.out.println(epicTaskList.toString());

     }
     public static void printSubtask(TaskManager taskManager, Subtask subtask) {
          taskManager.createSubTask(subtask);
          HashMap<Integer, Subtask> subTaskList = taskManager.getSubTaskList();
          System.out.println(subTaskList.toString());

     }

}
