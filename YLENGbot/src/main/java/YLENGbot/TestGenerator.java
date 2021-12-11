package YLENGbot;


import java.util.ArrayList;
import java.util.Random;

public class TestGenerator {

    private final ArrayList<Integer> completed = new ArrayList<>();
    private int currentTaskId = 0;

    public String GetRandomTask() {
        var rand = new Random();
        var taskId = 0;
        String rndTask;
        while (true) {
            taskId = rand.nextInt(TxtParser.getAllAnswers().size());
            if (!this.completed.contains(taskId)) {
                rndTask = TxtParser.getAllQuestions().get(taskId);// loop???
                this.completed.add(taskId);
                this.currentTaskId = taskId;
                break;
            }
        }
        return rndTask;
    }

    public int getCurrentTaskId() {
        return this.currentTaskId;
    }
}
