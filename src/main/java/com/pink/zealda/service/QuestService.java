package com.pink.zealda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.zealda.model.Quest;
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

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public void initQuests() {
        questRepository.deleteAll();
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
            questRepository.save(objectMapper.readValue(file, Quest.class));
        } catch (IOException e) {
            LOG.error("Could not read file because of: ",e);
        }
    }

}
