package service.managers;

import service.status.Status;
import service.tasks.Epic;
import service.tasks.Subtask;
import service.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private int counter;
    private HashMap<Integer, Task> taskList;
    private HashMap<Integer, Subtask> subTaskList;
    private HashMap<Integer, Epic> epicTaskList;
    @Override
    public List<Task> history() {
        List<Task> history = new ArrayList<>();
        if (!taskList.isEmpty()) {
            history.addAll(taskList.values());
        }
        if (!subTaskList.isEmpty()) {
            history.addAll(subTaskList.values());
        }
        if (!epicTaskList.isEmpty()) {
            history.addAll(epicTaskList.values());
        }
        return history;
    }

    public InMemoryTaskManager() {
        this.counter = 0;
        this.subTaskList = new HashMap<>();
        this.taskList = new HashMap<>();
        this.epicTaskList = new HashMap<>();
    }

    // ... остальные методы из TaskManager.java

    // ... остальные методы из TaskManager.java
    @Override
    public int getCounter() {
        return counter;
    }
    @Override
    public void setCounter() {
        this.counter++;
    }
    @Override
    public HashMap<Integer, Task> getTaskList() {
        return taskList;
    }
    @Override
    public HashMap<Integer, Subtask> getSubTaskList() {
        return subTaskList;
    }
    @Override
    public HashMap<Integer, Epic> getEpicTaskList() {
        return epicTaskList;
    }
    @Override
    public void clearTaskList() {
        this.taskList.clear();
    }
    @Override
    public void clearSubtaskList() {
        this.subTaskList.clear();
    }
    @Override
    public void clearEpicList() {
        this.epicTaskList.clear();
    }
    @Override
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
    @Override
    public void createTask(Task task) {
        task.setId(getCounter());
        setCounter();
        taskList.put(task.getId(), task);
    }
    @Override
    public void createSubTask(Subtask subtask) {
        subtask.setId(getCounter());
        setCounter();
        // получаем Id epic-а
        int epicId = subtask.getEpicId();
        // получаем объект epic-а
        Epic epic = epicTaskList.get(epicId);
        // добавляем Id subtask-а в поле объекта epic
        epic.addSubtaskId(subtask.getId());
        // пихаем новый epic в hashmap
        epicTaskList.put(epicId, epic);
        subTaskList.put(subtask.getId(), subtask);
    }
    @Override
    public void createEpicTask(Epic task) {
        task.setId(getCounter());
        setCounter();
        epicTaskList.put(task.getId(), task);
    }
    @Override
    public void updateTask(int oldTaskId, Task newTask) {
        taskList.put(oldTaskId, newTask);
    }
    /*public void updateSubTask(int id, Subtask subtask) {
    Subtask existingSubtask = subtasks.get(id);
    if (existingSubtask.getStatus() != Status.DONE) {
        existingSubtask.setName(subtask.getName());
        existingSubtask.setDescription(subtask.getDescription());
        existingSubtask.setStatus(subtask.getStatus());
    }
}if (existingSubtask.getStatus().equals(Status.DONE)) {
            taskManager.updateSubtask(subtask);
        }
*/  @Override
    public void updateSubTask(int oldTaskId, Subtask newTask) {
        Subtask existingSubtask = subTaskList.get(oldTaskId);
        if (existingSubtask != null && existingSubtask.getStatus().equals(Status.DONE)) {
            subTaskList.replace(oldTaskId, existingSubtask, newTask);
        }

        // Update the epic status
        String status = newTask.getStatus();
        Epic epic = epicTaskList.get(newTask.getEpicId());
        if (epic != null) {
            switch (status) {
                case "IN_PROGRESS":
                    boolean hasInProgressSubtask = false;
                    for (Integer id : epic.getSubtasksId()) {
                        if (subTaskList.containsKey(id)) {
                            if (subTaskList.get(id).getStatus().equals("IN_PROGRESS")) {
                                hasInProgressSubtask = true;
                                break;
                            }
                        }
                    }

                    if (hasInProgressSubtask) {
                        epic.setStatus("IN_PROGRESS");
                    } else {
                        epic.setStatus("DONE");
                    }
                    break;
                case "DONE":
                    boolean allSubtasksDone = true;
                    for (Integer id : epic.getSubtasksId()) {
                        if (subTaskList.containsKey(id)) {
                            if (!subTaskList.get(id).getStatus().equals("DONE")) {
                                allSubtasksDone = false;
                                break;
                            }
                        }
                    }
                    epic.setStatus(allSubtasksDone ? "DONE" : "IN_PROGRESS");
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + status);
            }
        }
    }


    @Override
    public void updateEpicTask(int oldTaskId, Epic newTask) {
        epicTaskList.put(oldTaskId, newTask);
    }
    @Override
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
    @Override
    public ArrayList<Subtask> getSubtasks(Epic epic) {
        ArrayList<Subtask> ans = new ArrayList<>();
        for (Integer id : epic.getSubtasksId()) {
            if (subTaskList.containsKey(id)) {
                ans.add(subTaskList.get(id));
            }
        }
        return ans;
    }
    public Task getTask(int id) {
        return taskList.get(id);
    }

    public Epic getEpic(int id) {
        return epicTaskList.get(id);
    }

    public Subtask getSubtask(int id) {
        return subTaskList.get(id);
    }

}



