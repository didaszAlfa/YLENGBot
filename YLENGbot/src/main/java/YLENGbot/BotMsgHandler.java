package YLENGbot;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.DefaultAbsSender.*;

import java.util.Objects;

public class BotMsgHandler {
    public static void handleMessage(Update update, BotRegisterer bot) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            var chatId = message.getChatId();
            var messageText = message.getText();
            var user = new UsersTestState(); // каждый раз обновляется, надо закинуть в статичную структуру
            var question = new TestGenerator();
            if (!UsersTestState.chatIds.containsKey(chatId)) {
                UsersTestState.chatIds.put(chatId, question);
                UsersTestState.users.put(chatId, user);
            }
            else {
                question = UsersTestState.chatIds.get(chatId);
                user = UsersTestState.users.get(chatId);
            }
            if (message.hasText()) {
                if (Objects.equals(messageText, "/start") ||
                        Objects.equals(messageText, "/help")) {
                        MessageUser.AnswerToUser(chatId, "Привет. Я бот для изучения английского языка. С помощью команды /help ты можешь узнать, как происходит обучение. Для того, чтобы узнать ваш уровень, рекомендую пройти тест. Если собираешься проходить, то пиши /startlvl, а иначе введи уровень сам - /enterlvl", bot);

                }
                else if (Objects.equals(messageText, "/startlvl") || user.isStartLvlActive) {
                    user.questionCounter++;
                    if (user.isFirstQuestion) {
                        user.isStartLvlActive = true;
                        user.isFirstQuestion = false;
                        var rndTask = question.GetRandomTask();
                            MessageUser.AnswerToUser(chatId, "Начнём: \n" + rndTask + "\n", bot);

                    }
                    else {
                        if (user.questionCounter == 3) {
                            if (Objects.equals(messageText, TxtParser.answers.get(question.qInd))) { // Верно
                                user.grade += 0.5;
                            }
                            user.isStartLvlActive = false;
                            MessageUser.AnswerToUser(chatId, user.grade, bot);
                        }
                        else {
                            if (Objects.equals(messageText, TxtParser.answers.get(question.qInd))) { // Верно
                                user.grade += 0.5;
                            }
                            var rndTask = question.GetRandomTask();
                            MessageUser.AnswerToUser(chatId, "Следующий вопрос \n" + rndTask, bot);

                        }

                    }
                }
                else {
                    MessageUser.AnswerToUser(chatId, "You sent: " + messageText + '\n', bot);
                }
            }
        }
    }
}
