package YLENGbot;


import java.util.HashMap;

public class UsersTestState {
    private static final HashMap<Long, TestGenerator> chatIds = new HashMap<>();
    private static final HashMap<Long, UsersTestState> registeredUsers = new HashMap<>();

    private boolean isStartLvlStateEnabled = false;
    private int answeredQuestionCounter = 0;
    private double score = 0;
    private String engLvl = "";

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
        return this.answeredQuestionCounter == answersAmount;
    }

    public boolean checkIsLastTestLvlAnswer() {
        var answersAmount = TxtParser.getAllAnswers().size() + 1;
        return this.answeredQuestionCounter == answersAmount;
    }

    public void incrementQuestionCounter() {
        this.answeredQuestionCounter++;
    }

    public void addScores() {
        this.score += 2.5;
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

    private void setEngLvl() {
        if (this.score <= 5) this.engLvl = "A0";
        else if (this.score <= 12) this.engLvl = "A1";
        else if (this.score <= 20) this.engLvl = "A2";
        else if (this.score <= 27) this.engLvl = "B1";
        else engLvl = "B2";
    }

    public String getEngLvl() {
        setEngLvl();
        return this.engLvl;
    }
}
