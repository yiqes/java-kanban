package todoList.service;
import todoList.tasks.Subtask;
import todoList.tasks.Task;
import todoList.tasks.Epic;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int counter;
    HashMap<Integer, Task> taskList;
    HashMap<Integer, Subtask> subTaskList;

    HashMap<Integer, Epic> epicTaskList;

    public TaskManager() {
        this.counter = 0;
        this.subTaskList = new HashMap<>();
        this.taskList = new HashMap<>();
        this.epicTaskList = new HashMap<>();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter++;
    }

    public HashMap<Integer, Task> getTaskList() {
        return taskList;
    }

    public HashMap<Integer, Subtask> getSubTaskList() {
        return subTaskList;
    }

    public HashMap<Integer, Epic> getEpicTaskList() {
        return epicTaskList;
    }
    public void clearTaskList() {
        this.taskList.clear();
    }
    public void clearSubtaskList() {
        this.subTaskList.clear();
    }
    public void clearEpicList() {
        this.epicTaskList.clear();
    }
    public Task getValueFromId(int id) {
        if (taskList.containsKey(id)) {
            return taskList.get(id);
        }
        if (subTaskList.containsKey(id)) {
            return subTaskList.get(id);
        }
        if (epicTaskList.containsKey(id)) {
            return epicTaskList.get(id);
        } else {
            return null;
        }
    }
    public void createTask(Task task) {
        task.setId(getCounter());
        setCounter();
        taskList.put(task.getId(), task);
    }
    public void createSubTask(Subtask subtask) {
        subtask.setId(getCounter());
        setCounter();
        // получаем Id epic-а
        int epicId = subtask.getEpicId();
        // получаем объект epic-а
        Epic epic = epicTaskList.get(epicId);
        // добавляем Id subtask-а в поле объекта epic
        epic.updatesubtasksId(subtask.getId());
        // пихаем новый epic в hashmap
        epicTaskList.put(epicId, epic);
        subTaskList.put(subtask.getId(), subtask);
    }
    public void createEpicTask(Epic task) {
        task.setId(getCounter());
        setCounter();
        epicTaskList.put(task.getId(), task);
    }
    public void updateTask(int oldTaskId, Task newTask) {
        taskList.put(oldTaskId, newTask);
    }
    public void updateSubTask(int oldTaskId, Subtask newTask) {
        subTaskList.put(oldTaskId, newTask);
        //если old.status != new.status, то ищем epic
        //и меняем его статус
        String status = newTask.getStatus();
        Epic epic = epicTaskList.get(newTask.getEpicId());
        switch (status) {
            case "IN_PROGRESS":
                epic.setStatus("IN_PROGRESS");
            case "DONE":
                boolean f = true;
                for (Integer id : epic.getSubtasksId()) {
                    if (subTaskList.containsKey(id)) {
                        if (!subTaskList.get(id).getStatus().equals("DONE")) {
                            f = false;
                        }
                    }
                }
                if (f) {
                    epic.setStatus("DONE");
                } else {
                    epic.setStatus("IN_PROGRESS");
                }
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
    }
    public void updateEpicTask(int oldTaskId, Epic newTask) {
        epicTaskList.put(oldTaskId, newTask);
    }
    public void deleteById(int id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
        }
        if (subTaskList.containsKey(id)) {
            subTaskList.remove(id);
        }
        if (epicTaskList.containsKey(id)) {
            epicTaskList.remove(id);
        }
    }
    public ArrayList<Subtask> getSubtasks(Epic epic) {
        ArrayList<Subtask> ans = new ArrayList<>();
        for (Integer id : epic.getSubtasksId()) {
            if (subTaskList.containsKey(id)) {
                ans.add(subTaskList.get(id));
            }
        }
        return ans;
    }
}
