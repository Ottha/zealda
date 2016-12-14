package com.pink.zealda.listener;

import com.pink.zealda.model.Legend;
import com.pink.zealda.service.LegendService;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackChannelLeft;
import com.ullink.slack.simpleslackapi.events.SlackGroupJoined;
import com.ullink.slack.simpleslackapi.listeners.SlackChannelJoinedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackChannelLeftListener;
import com.ullink.slack.simpleslackapi.listeners.SlackGroupJoinedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LegendLeftListener implements SlackChannelLeftListener {

    private final Logger log = LoggerFactory.getLogger(LegendLeftListener.class);

    @Autowired
    LegendService legendService;

    @Value("${slack.bot.channel}")
    String channel;

    @Override
    public void onEvent(SlackChannelLeft event, SlackSession session) {
        if (!event.getSlackChannel().getName().equals(channel)) {
            return;
        }

        List<String> membersOfChannel = event.getSlackChannel().getMembers()
            .stream()
            .map(m -> m.getUserName()).collect(Collectors.toList());

        List<Legend> legends = legendService.findAll().stream().filter(legend -> !membersOfChannel.contains(legend.getName())).collect(Collectors.toList());

        legendService.deleteLegends(legends);

    }
}
