package service.tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subtasksId;

    public Epic(String title, String description, ArrayList<Integer> subtasksId) {
        super(title, description);
        this.subtasksId = subtasksId;
        this.setStatus("NEW");
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void addSubtaskId(int subtaskId) {
        this.subtasksId.add(subtaskId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", subtasksId=" + subtasksId +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}