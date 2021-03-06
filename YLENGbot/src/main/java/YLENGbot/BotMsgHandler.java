package YLENGbot;


import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Objects;

public class BotMsgHandler {
    public static void handleMessage(Update update, BotRegisterer bot) {
        var message = update.getMessage();
        var chatId = message.getChatId();
        var messageText = message.getText();
        var user = new UsersTestState();
        var question = new TestGenerator();
        if (!UsersTestState.getChatIds().containsKey(chatId)) {
            UsersTestState.registerUser(chatId, question, user);
        }
        else {
            question = UsersTestState.getChatIds().get(chatId);
            user = UsersTestState.getRegisteredUsers().get(chatId);
        }
        var greetingsText = "Привет. Я бот для изучения английского языка. С помощью команды /help ты можешь узнать, как происходит обучение. Для того, чтобы узнать ваш уровень, рекомендую пройти тест. Если собираешься проходить, то пиши /startlvl, а иначе введи уровень сам - /enterlvl";
        switch (messageText) {
            case ("/start"), ("/help") -> MessageUser.AnswerToUser(chatId, greetingsText, bot);
            case ("/startlvl") -> handleStartLvlMessage(user, question, chatId, messageText, bot);
        }
        if (user.checkIsStartLvlStateEnabled() && !messageText.equals("/startlvl")) {
            handleStartLvlMessage(user, question, chatId, messageText, bot);
        }
    }

    private static void handleStartLvlMessage(UsersTestState user, TestGenerator question,
                                              long chatId, String messageText, BotRegisterer bot) {
        user.incrementQuestionCounter();
        if (Objects.equals(messageText, TxtParser.getAllAnswers().get(question.getCurrentTaskId()))) {
            user.addScores();
        }
        if (user.checkIsLastTestLvlAnswer()) {
            user.disableStartLvlState();
            MessageUser.AnswerToUser(chatId, "Ваш уровень: " + user.getEngLvl(), bot);
        }
        else {
            var rndTask = question.GetRandomTask();
            if (user.checkIsFirstTestLvlAnswer()) {
                user.enableStartLvlState();
                MessageUser.AnswerToUser(chatId, "Начнём: \n" + rndTask + "\n", bot);
            }
            else {
                MessageUser.AnswerToUser(chatId, "Следующий вопрос \n" + rndTask, bot);
            }
        }
    }
}
