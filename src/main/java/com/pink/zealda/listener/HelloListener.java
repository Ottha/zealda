package com.pink.zealda.listener;

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

@Component
public class HelloListener extends AbstractSlackMessagePostedListener {

    private final Logger log = LoggerFactory.getLogger(HelloListener.class);


    @Override
    public void onEventInternal(SlackMessagePosted event, SlackSession session) {
        log.debug("Message Posted: '{}'", event.getMessageContent().toUpperCase());
        if (event.getMessageContent().trim().toUpperCase().contains("HELLO <@" + bot.getId() + ">")) {
            session.sendMessage(event.getChannel(), " Hello " + event.getSender().getUserName() + ". Number 5 is alive. :robot_face:", null);
        }

    }

}
