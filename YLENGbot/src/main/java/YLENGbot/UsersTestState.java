package YLENGbot;

import java.util.HashMap;

public class UsersTestState {
    public static HashMap<Long, TestGenerator> chatIds = new HashMap<>();
    public static HashMap<Long, UsersTestState> users = new HashMap<>();
    boolean isStartLvlActive = false;
    boolean isFirstQuestion = true;
    int questionCounter = 0;
    double grade = 0;
}
