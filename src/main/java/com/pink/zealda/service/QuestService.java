package com.pink.zealda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import com.pink.zealda.repository.QuestOfLegendRepository;
import com.pink.zealda.repository.QuestRepository;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    SlackSession slackSession;

    @Autowired
    QuestRepository questRepository;

    @Autowired
    QuestOfLegendRepository questOfLegendRepository;

    @Autowired
    MessageFormattingService messageFormattingService;

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public void initQuests() {
        storeQuestFilesInDB();
    }

    private void storeQuestFilesInDB() {
        try {
            Arrays.stream(new ClassPathResource("quests/").getFile().listFiles()).forEach(file -> saveQuests(file));
        } catch (IOException e) {
            LOG.error("failed to read quests");
        }

    }

    private void saveQuests(File file)  {
        try {
            Quest quest = objectMapper.readValue(file, Quest.class);
            if (questRepository.findByName(quest.getName()) == null) {
                questRepository.save(quest);
            }
        } catch (IOException e) {
            LOG.error("Could not read file because of: ",e);
        }
    }

    public void save(Quest quest) {
        questRepository.save(quest);
    }
    public QuestOfLegend assignQuestToLegend(Quest quest, Legend legend) {
        QuestOfLegend questOfLegend = new QuestOfLegend();
        questOfLegend.setLegend(legend);

        questOfLegend.setQuest(quest);
        questOfLegend.setStartTimeInMillis(System.currentTimeMillis());
        questOfLegend.setSucceeded(false);
        return questOfLegendRepository.save(questOfLegend);
    }

    public void sendNewQuestMessage(String legendName, Quest quest) {
        List<String> message = new ArrayList<>();
        message.add("Thou shall now try the quest: ");
        message.add(messageFormattingService.getBeautifiedQuestName(quest.getName()) + messageFormattingService.withLinebreak());
        message.add("This is what thou shall do: ");
        message.add(quest.getDescription() + messageFormattingService.withLinebreak());
        message.add("And thou will gain " + messageFormattingService.getBeautifiedXP(quest.getExpGain()));
        messageFormattingService.sendFormattedMessage(legendName, message);
    }

    public void sendSolvedQuestMessage(Legend legend, Quest quest) {
        List<String> message = new ArrayList<>();
        message.add("Thou succeeded in completing the quest: ");
        message.add(messageFormattingService.getBeautifiedQuestName(quest.getName()) + messageFormattingService.withLinebreak());
        message.add("And thou will gain " +
            messageFormattingService.getBeautifiedXP(quest.getExpGain()) +
            " and thou are now at " +
            messageFormattingService.getBeautifiedXP(legend.getCurrentXp()));

        messageFormattingService.sendFormattedMessage(legend.getName(), message);
    }
}
