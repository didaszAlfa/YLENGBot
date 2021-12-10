package YLENGbot;


import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Objects;

public class BotMsgHandler {
    private static String greetingsText = "Привет. Я бот для изучения английского языка. С помощью команды /help ты можешь узнать, как происходит обучение. Для того, чтобы узнать ваш уровень, рекомендую пройти тест. Если собираешься проходить, то пиши /startlvl, а иначе введи уровень сам - /enterlvl";
    public static void handleMessage(Update update, BotRegisterer bot) {
        var message = update.getMessage();
        var chatId = message.getChatId();
        var messageText = message.getText();
        var user = new UsersTestState();
        var question = new TestGenerator();
        if (!UsersTestState.chatIds.containsKey(chatId)) {
            UsersTestState.registerUser(chatId, question, user);
        }
        else {
            question = UsersTestState.chatIds.get(chatId);
            user = UsersTestState.users.get(chatId);
        }
        switch (messageText) {
            case ("/start"), ("/help") -> MessageUser.AnswerToUser(chatId, greetingsText, bot);
            case ("/startlvl") -> handleStartLvlMessage(user, question, chatId, messageText, bot);
        }
        if (user.checkIsStartLvlStateEnabled() && !messageText.equals("/startlvl")) handleStartLvlMessage(user, question, chatId, messageText, bot);
    }

    private static void handleStartLvlMessage(UsersTestState user, TestGenerator question,
                                              long chatId, String messageText, BotRegisterer bot) {
        user.incrementQuestionCounter();
        if (user.checkIsFirstTestLvlAnswer()) {
            user.enableStartLvlState();
            user.registerFirstTestLvlAnswer();
            var rndTask = question.GetRandomTask();
            MessageUser.AnswerToUser(chatId, "Начнём: \n" + rndTask + "\n", bot);
        }
        else {
            if (user.getQuestionCounter() == TxtParser.answers.size() + 1) {
                if (Objects.equals(messageText, TxtParser.answers.get(question.qInd))) { // Верно
                    user.addScores();
                }
                user.disableStartLvlState();
                MessageUser.AnswerToUser(chatId, user.getScores(), bot);
            }
            else {
                if (Objects.equals(messageText, TxtParser.answers.get(question.qInd))) { // Верно
                    user.addScores();
                }
                var rndTask = question.GetRandomTask();
                MessageUser.AnswerToUser(chatId, "Следующий вопрос \n" + rndTask, bot);
            }
        }
    }
}
