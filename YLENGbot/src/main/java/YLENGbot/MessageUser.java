package YLENGbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageUser {
    public static void AnswerToUser(Long chatId, String text, BotRegisterer bot) {
                try {
                    bot.execute(SendMessage.builder()
                            .chatId(String.valueOf(chatId))
                            .text(text)
                            .build());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
    }

    public static void AnswerToUser(Long chatId, double grade, BotRegisterer bot) {
        try {
            bot.execute(SendMessage.builder()
                    .chatId(String.valueOf(chatId))
                    .text("Тест закончен, ваш балл: " + grade)
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
