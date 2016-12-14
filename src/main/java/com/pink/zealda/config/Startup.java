package com.pink.zealda.config;

import com.pink.zealda.listener.HelloListener;
import com.pink.zealda.listener.LegendLeftListener;
import com.pink.zealda.listener.LegendListener;
import com.pink.zealda.listener.QuestListener;
import com.pink.zealda.properties.SlackProperties;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Startup.class);

    @Value("${spring.config.location:NONE_PASSED}")
    public String properties;

    @Autowired
    SlackSession slackSession;
    @Autowired
    HelloListener helloListener;
    @Autowired
    SlackProperties slackProperties;

    @Autowired
    QuestListener questListener;
    @Autowired
    LegendLeftListener legendLeftListener;
    @Autowired
    LegendListener legendListener;

    @Override
    public void run(String... args) throws Exception {
        log.info("Properties provided: " + properties);
        log.info("Slack service started with apikey: ..." + slackProperties.key.substring(slackProperties.key.length() - 4));
        slackSession.addMessagePostedListener(helloListener);
        slackSession.addMessagePostedListener(questListener);
        slackSession.addGroupJoinedListener(legendListener);
        slackSession.addChannelLeftListener(legendLeftListener);
    }
}
