package com.pink.zealda.service;

import com.ullink.slack.simpleslackapi.SlackSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageFormattingService {


    private static final String LINEBREAK = "\n";

    @Autowired
    SlackSession slackSession;

    public void sendFormattedMessage(String legendName, List<String> message) {
        slackSession.sendMessageToUser(slackSession.findUserByUserName(legendName), message.stream().collect(Collectors.joining(LINEBREAK)) , null);
    }

    public String withLinebreak() {
        return LINEBREAK;
    }

    public String getBeautifiedXP(long xp) {
        return " :sports_medal: *" + xp + " XP* :sports_medal: ";
    }

    public String getBeautifiedQuestName(String questName) {
        return " :crossed_swords: *" + questName + "* :crossed_swords: ";
    }
}
