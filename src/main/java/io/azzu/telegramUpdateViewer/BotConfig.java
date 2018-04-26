package io.azzu.telegramUpdateViewer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@ConfigurationProperties("bot-settings")
public class BotConfig {

    private String name;
    private String token;
    private String proxy_host;
    private int proxy_port;
    private String proxy_user;
    private String proxy_password;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setProxy_host(String proxy_host) { this.proxy_host = proxy_host; }

    public String getProxy_host() { return proxy_host; }

    public void setProxy_port(int proxy_port) { this.proxy_port = proxy_port; }

    public int getProxy_port() { return proxy_port; }

    public DefaultBotOptions getDefaultBotOptions(){
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        if (proxy_host != null) {
            HttpHost httpHost = new HttpHost(proxy_host, proxy_port);
            RequestConfig.Builder requestConfigBuilder = RequestConfig.custom().setProxy(httpHost);

            if (proxy_user != null) {
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(proxy_host, proxy_port),
                        new UsernamePasswordCredentials(proxy_user, proxy_password)
                );
                requestConfigBuilder.setAuthenticationEnabled(true);
                botOptions.setCredentialsProvider(credsProvider);
            } else {
                requestConfigBuilder.setAuthenticationEnabled(false);
            }

            botOptions.setRequestConfig(requestConfigBuilder.build());
            botOptions.setHttpProxy(httpHost);
        }
        return botOptions;
    }

}
