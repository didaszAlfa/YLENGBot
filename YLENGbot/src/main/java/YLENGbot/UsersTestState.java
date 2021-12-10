package YLENGbot;


import java.util.HashMap;

public class UsersTestState {
    public static HashMap<Long, TestGenerator> chatIds = new HashMap<>();
    public static HashMap<Long, UsersTestState> users = new HashMap<>();

    private boolean isStartLvlStateEnabled = false;
    private boolean isFirstTestLvlAnswer = true;
    private int questionCounter = 0;
    private double score = 0;

    public void enableStartLvlState() {
        this.isStartLvlStateEnabled = true;
    }

    public void disableStartLvlState() {
        this.isStartLvlStateEnabled = false;
    }

    public boolean checkIsStartLvlStateEnabled() {
        return this.isStartLvlStateEnabled;
    }
    public void registerFirstTestLvlAnswer() {
        this.isFirstTestLvlAnswer = false;
    }

    public boolean checkIsFirstTestLvlAnswer() {
        return this.isFirstTestLvlAnswer;
    }

    public void incrementQuestionCounter() {
        this.questionCounter++;
    }

    public int getQuestionCounter() {
        return this.questionCounter;
    }

    public void addScores() {
        this.score += 0.5;
    }

    public double getScores() {
        return this.score;
    }

    public static void registerUser(long chatId, TestGenerator question, UsersTestState user) {
        UsersTestState.chatIds.put(chatId, question);
        UsersTestState.users.put(chatId, user);
    }
}
