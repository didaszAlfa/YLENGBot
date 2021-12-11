package YLENGbot;


import java.util.HashMap;

public class UsersTestState {
    private static final HashMap<Long, TestGenerator> chatIds = new HashMap<>();
    private static final HashMap<Long, UsersTestState> registeredUsers = new HashMap<>();

    private boolean isStartLvlStateEnabled = false;
    private int answeredQuestionCounter = 0;
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

    public boolean checkIsFirstTestLvlAnswer() {
        var answersAmount = 1;
        System.out.print("First  " + answersAmount + " : " + this.answeredQuestionCounter + "\n");
        return this.answeredQuestionCounter == answersAmount;
    }

    public boolean checkIsLastTestLvlAnswer() {
        var answersAmount = TxtParser.getAllAnswers().size() + 1;
        System.out.print("Last  " + answersAmount + " : " + this.answeredQuestionCounter + "\n");
        return this.answeredQuestionCounter == answersAmount;
    }

    public void incrementQuestionCounter() {
        this.answeredQuestionCounter++;
    }

    public void addScores() {
        this.score += 0.5;
    }

    public double getScores() {
        return this.score;
    }

    public static void registerUser(long chatId, TestGenerator question, UsersTestState user) {
        UsersTestState.chatIds.put(chatId, question);
        UsersTestState.registeredUsers.put(chatId, user);
    }

    public static HashMap<Long, TestGenerator> getChatIds() {
        return chatIds;
    }

    public static HashMap<Long, UsersTestState> getRegisteredUsers() {
        return registeredUsers;
    }
}
