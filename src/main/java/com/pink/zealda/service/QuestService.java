package com.pink.zealda.service;

import com.pink.zealda.model.Quest;
import com.pink.zealda.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    @Autowired
    QuestRepository questRepository;

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public void initQuests() {
        questRepository.deleteAll();
        Quest selfieQuest = new Quest("The maiden and me");
        selfieQuest.setDescription("Thou shall go to one colleague of yours and acquire a painting of the two of thee!");
        questRepository.save(selfieQuest);
    }

}
