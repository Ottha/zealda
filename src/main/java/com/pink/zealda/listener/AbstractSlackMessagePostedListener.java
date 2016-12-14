package com.pink.zealda.listener;

import com.pink.zealda.model.Legend;
import com.pink.zealda.service.LegendService;
import com.pink.zealda.service.SlackService;
import com.ullink.slack.simpleslackapi.SlackPersona;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by akraf on 12/14/16.
 */
public abstract class AbstractSlackMessagePostedListener implements SlackMessagePostedListener {

    private final Logger log = LoggerFactory.getLogger(QuestListener.class);

    @Autowired
    SlackService slackService;

    @Autowired
    LegendService legendService;

    protected SlackPersona bot;

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

        String legendName = event.getSender().getUserName();
        Legend legend = legendService.findByName(legendName);
        if (legend == null) {
            legendService.createLegendByName(legendName);
        }

        onEventInternal(event, session, legend);
    }

    abstract void onEventInternal(SlackMessagePosted event, SlackSession session, Legend legend);

}
