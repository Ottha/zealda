package com.pink.zealda.listener;

import com.pink.zealda.service.QuestService;
import com.pink.zealda.service.SlackService;
import com.ullink.slack.simpleslackapi.SlackPersona;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class QuestListener implements SlackMessagePostedListener {

    private final Logger log = LoggerFactory.getLogger(QuestListener.class);

    @Autowired
    SlackService slackService;

    @Autowired
    QuestService questService;

    private SlackPersona bot;

    @PostConstruct
    private void setBotName() {
        bot = slackService.getBot();
    }

    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        log.debug("Message Posted: '{}'", event.getMessageContent().toUpperCase());
        if (event.getSender().getId().equals(bot.getId())) {
            return;
        }
        String normalizedMessage = event.getMessageContent().trim().toUpperCase();
        if (normalizedMessage.contains("QUEST") && normalizedMessage.contains("ALL")) {
            session.sendMessage(event.getChannel(), " Hello " + event.getSender().getUserName() + ". Quests to challenge thee legends are:" + questService.getAllQuests().stream().map(quest -> quest.getName()).collect(Collectors.joining(", ", " :crossed_swords: ", " :crossed_swords: ")), null);
        }

    }

}
