package YLENGbot;



import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class BotStarter {
    public static void main(String[] args) throws TelegramApiException {
        BotRegisterer bot = new BotRegisterer();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        TxtParser.GatherTasks();
    }
}
