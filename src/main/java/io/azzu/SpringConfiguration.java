package io.azzu;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.azzu.telegramUpdateViewer.BotConfig;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(BotConfig.class)
class SpringConfiguration {}