package io.azzu.telegramUpdateViewer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bot-settings")
public class BotConfig {

    private String botName;
    private String token;

    public void setBotname(String botname) {
        this.botName = botname;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBotname() {
        return botName;
    }

    public String getToken() {
        return token;
    }

}
