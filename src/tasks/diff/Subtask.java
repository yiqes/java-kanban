package tasks.diff;

public class Subtask extends Task {
    private int epicId;
    public Subtask() {

    }
    public Subtask(String title, String description, String status, int epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

}
