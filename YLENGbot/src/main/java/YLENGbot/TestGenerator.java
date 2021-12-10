package YLENGbot;


import java.util.ArrayList;
import java.util.Random;

public class TestGenerator {

    ArrayList<Integer> completed = new ArrayList<>();
    int qInd = 0;
    public String GetRandomTask() {
        Random rand = new Random();
        int nextTaskId;
        String rndTask;
        while (true) {
            nextTaskId = rand.nextInt(TxtParser.allQuestions.size());
            if (!this.completed.contains(nextTaskId)) {
                rndTask = TxtParser.allQuestions.get(nextTaskId);// loop???
                this.completed.add(nextTaskId);
                this.qInd = nextTaskId;
                break;
            }
        }
        return rndTask;
    }
}
