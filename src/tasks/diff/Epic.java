package tasks.diff;
import java.util.ArrayList;
public class Epic extends Task {


    private ArrayList<Integer> subtasksId;
    private Subtask subtask;
    private String statusEpic;

    public Epic(String title, String description, ArrayList<Integer> subtasksId) {
        super(title, description);
        this.subtask = new Subtask();
        this.subtasksId = new ArrayList<>();
        this.statusEpic = "NEW";
    }
    public int getID() {
        return subtask.getEpicId();
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void updatesubtasksId(int newSubtaskId) {
        this.subtasksId.add(newSubtaskId);
    }
    @Override
    public String toString() {
        return "Epic{" +
                "title=" + getTitle() + '\n' +
                "description=" + getDescription() + "\n" +
                "status=" + statusEpic + '\n';
    }
}
