package com.pink.zealda.listener;

import com.pink.zealda.model.Legend;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloListener extends AbstractSlackMessagePostedListener {

    private final Logger log = LoggerFactory.getLogger(HelloListener.class);


    @Override
    public void onEventInternal(SlackMessagePosted event, SlackSession session, Legend legend) {
        log.debug("Message Posted: '{}'", event.getMessageContent().toUpperCase());
        if (event.getMessageContent().trim().toUpperCase().contains("HELLO <@" + bot.getId() + ">")) {
            session.sendMessage(event.getChannel(), " Hello " + event.getSender().getUserName() + ". Number 5 is alive. :robot_face:", null);
        }

    }

}
