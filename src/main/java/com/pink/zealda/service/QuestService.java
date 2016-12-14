package com.pink.zealda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.zealda.listener.HelloListener;
import com.pink.zealda.model.Quest;
import com.pink.zealda.repository.QuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    private final Logger log = LoggerFactory.getLogger(QuestService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    QuestRepository questRepository;

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public void initQuests() {
        questRepository.deleteAll();
        Quest selfieQuest = new Quest("The maiden and me");
        selfieQuest.setDescription("Thou shall go to one colleague of yours and acquire a painting of the two of thee!");

        try {
            log.info(objectMapper.writeValueAsString(selfieQuest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        questRepository.save(selfieQuest);
    }

    private void getDefaultQuests() {

    }

}
