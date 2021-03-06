package com.pink.zealda.listener;

import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
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
public class QuestListener extends AbstractSlackMessagePostedListener{

    private final Logger log = LoggerFactory.getLogger(QuestListener.class);

    @Autowired
    QuestService questService;

    @Autowired
    QuestOfLegendService questOfLegendService;

    @Override
    public void onEventInternal(SlackMessagePosted event, SlackSession session, Legend legend) {
        log.debug("Message Posted: '{}'", event.getMessageContent().toUpperCase());

        String normalizedMessage = event.getMessageContent().trim().toUpperCase();
        List<Quest> allQuests = questService.getAllQuests();
        String userName = event.getSender().getUserName();
        if (normalizedMessage.contains("QUEST") && normalizedMessage.contains("ALL")) {
            session.sendMessage(event.getChannel(), " Hello " + userName + ". Quests to challenge thee legends are:" + allQuests.stream().map(quest -> quest.getName()).collect(Collectors.joining(", ", " :crossed_swords: ", " :crossed_swords: ")), null);
        }

        if (normalizedMessage.contains("QUEST") && normalizedMessage.contains("GIVE") && normalizedMessage.contains("ME")) {
            Optional<Quest> quest = allQuests.stream().filter(quest1 -> normalizedMessage.contains(quest1.getName().toUpperCase())).findFirst();
            if (quest.isPresent()) {
                QuestOfLegend questOfLegend = questService.assignQuestToLegend(quest.get(), legend);
                log.debug("new quest created:" + questOfLegend);
                questService.sendNewQuestMessage(userName, quest.get());
            } else {
                Quest nextQuest = questOfLegendService.getNextQuestForLegend(legend);
                questService.assignQuestToLegend(nextQuest, legend);
                questService.sendNewQuestMessage(userName, nextQuest);
            }
        }

        if (normalizedMessage.contains("ASSIGN") && normalizedMessage.contains("ALL")) {
            questOfLegendService.assignRandomQuestToLegends();
        }

        if (normalizedMessage.contains("FINISH") || normalizedMessage.contains("DONE") || normalizedMessage.contains("DID")) {
            questOfLegendService.solvePendingQuest(legend);
        }




    }

}
