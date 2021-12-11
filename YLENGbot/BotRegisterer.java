package YLENGbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotRegisterer extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return BotInfo.botName;
    }

    @Override
    public String getBotToken() {
        return BotInfo.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotRegisterer bot1 = new BotRegisterer();
        BotMsgHandler.handleMessage(update, bot1);
    }
}
