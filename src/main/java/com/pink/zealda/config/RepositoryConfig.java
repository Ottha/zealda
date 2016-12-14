package com.pink.zealda.config;

import com.pink.zealda.model.Quest;
import com.pink.zealda.repository.QuestRepository;
import com.pink.zealda.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RepositoryConfig {

    @Autowired
    QuestService questService;

    @PostConstruct
    public void initRepos() {
        questService.initQuests();
    }

}
