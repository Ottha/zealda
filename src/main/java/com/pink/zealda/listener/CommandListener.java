package com.pink.zealda.listener;

import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import com.pink.zealda.service.LegendService;
import com.pink.zealda.service.QuestOfLegendService;
import com.pink.zealda.service.QuestService;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandListener extends AbstractSlackMessagePostedListener{

    private final Logger log = LoggerFactory.getLogger(CommandListener.class);

    @Autowired
    LegendService legendService;

    @Override
    public void onEventInternal(SlackMessagePosted event, SlackSession session, Legend legend) {
        log.debug("Message Posted: '{}'", event.getMessageContent().toUpperCase());

        String normalizedMessage = event.getMessageContent().trim().toUpperCase();

        if (normalizedMessage.contains("SCORE") || normalizedMessage.contains("STANDING") || normalizedMessage.contains("LEADER")) {
            legendService.showScoreBoard(legend);
        }




    }

}
