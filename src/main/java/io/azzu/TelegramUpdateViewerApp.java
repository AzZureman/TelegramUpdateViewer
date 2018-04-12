package io.azzu;

import io.azzu.telegramUpdateViewer.TelegramUpdateViewer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelegramUpdateViewerApp {

    @Bean
    TelegramBotsApi telegramBotsApi(TelegramUpdateViewer updateViewer){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(updateViewer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramUpdateViewerApp.class, args);
    }
}
