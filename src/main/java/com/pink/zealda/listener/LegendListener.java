package com.pink.zealda.listener;

import com.pink.zealda.service.LegendService;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackGroupJoined;
import com.ullink.slack.simpleslackapi.listeners.SlackGroupJoinedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LegendListener implements SlackGroupJoinedListener {

    private final Logger log = LoggerFactory.getLogger(LegendListener.class);

    @Autowired
    LegendService legendService;

    @Value("slack.bot.channel")
    String channel;

    @Override
    public void onEvent(SlackGroupJoined event, SlackSession session) {
        if (!event.getSlackChannel().getName().equals(channel)) {
            return;
        }

        List<String> legends = legendService.findAll().stream().map(l -> l.getName()).collect(Collectors.toList());

        event.getSlackChannel().getMembers()
            .stream()
            .map(m -> m.getUserName())
            .filter(m -> !legends.contains(m)).forEach(legendService::createLegendByName);

        session.sendMessage(event.getSlackChannel(), "This is your chance to become a legend!", null);

    }
}
