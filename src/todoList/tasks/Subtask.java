package todoList.tasks;

public class Subtask extends Task {

    private int epicId;

    public Subtask(String title, String description, String status, int epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}