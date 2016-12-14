package com.pink.zealda.config;

import com.pink.zealda.service.CampaignService;
import com.pink.zealda.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RepositoryConfig {

    @Autowired
    QuestService questService;

    @Autowired
    CampaignService campaignService;

    @PostConstruct
    public void initRepos() {
        questService.initQuests();
        campaignService.initCampaigns();
    }

}
