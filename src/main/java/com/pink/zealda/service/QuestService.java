package com.pink.zealda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import com.pink.zealda.repository.QuestOfLegendRepository;
import com.pink.zealda.repository.QuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    QuestRepository questRepository;

    @Autowired
    QuestOfLegendRepository questOfLegendRepository;

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
        questOfLegend.setLegendId(legend);
        questOfLegend.setQuest(quest);
        return questOfLegendRepository.save(questOfLegend);
    }

}
