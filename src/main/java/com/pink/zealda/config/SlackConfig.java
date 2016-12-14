package com.pink.zealda.config;

import com.pink.zealda.properties.SlackProperties;
import com.pink.zealda.listener.HelloListener;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SlackConfig {

    private final Logger log = LoggerFactory.getLogger(SlackConfig.class);

    @Autowired
    SlackProperties slackProperties;

    private SlackSession slackSession;

    @Bean
    SlackSession slackSession() throws IOException {
        if (null == slackSession) {
            slackSession = SlackSessionFactory.createWebSocketSlackSession(slackProperties.key);
            slackSession.connect();
            log.debug("New session created: {}", slackSession);
        }
        return slackSession;
    }

    @Bean
    SlackChannel slackChannel() throws IOException {
        return slackSession.findChannelByName(slackProperties.channel);
    }

    @Bean
    HelloListener helloListener() {
        return new HelloListener();
    }

}
