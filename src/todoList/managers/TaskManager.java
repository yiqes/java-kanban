package todoList.managers;

import todoList.tasks.Epic;
import todoList.tasks.Subtask;
import todoList.tasks.Task;

import java.util.HashMap;
import java.util.List;

public interface TaskManager {
    int getCounter();

    void setCounter();

    HashMap<Integer, Task> getTaskList();

    HashMap<Integer, Subtask> getSubTaskList();

    HashMap<Integer, Epic> getEpicTaskList();

    void clearTaskList();

    void clearSubtaskList();

    void clearEpicList();

    Task getValueFromId(int id);

    void createTask(Task task);

    void createSubTask(Subtask subtask);

    void createEpicTask(Epic task);

    void updateTask(int oldTaskId, Task newTask);

    void updateSubTask(int oldTaskId, Subtask newTask);

    void updateEpicTask(int oldTaskId, Epic newTask);

    void deleteById(int id);

    List<Subtask> getSubtasks(Epic epic);

    List<Task> history();

}
