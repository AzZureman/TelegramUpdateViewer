package io.azzu.telegramUpdateViewer;

import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import io.azzu.webSocket.SocketHandler;

@Component
public class TelegramUpdateViewer extends TelegramLongPollingBot {

    private BotConfig config;
    private SocketHandler socketHandler;
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    public TelegramUpdateViewer(BotConfig config, SocketHandler socketHandler){
        super(config.getDefaultBotOptions());
        this.config = config;
        this.socketHandler = socketHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        socketHandler.dispatchMessage(gson.toJson(update, Update.class));
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

}
