package com.pink.zealda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.zealda.model.Campaign;
import com.pink.zealda.model.Quest;
import com.pink.zealda.repository.CampaignRepository;
import com.pink.zealda.repository.QuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    private final Logger log = LoggerFactory.getLogger(CampaignService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CampaignRepository campaignRepository;

    public List<Campaign> getAllQuests() {
        return campaignRepository.findAll();
    }

    public void initCampaigns() {
        campaignRepository.deleteAll();
        Campaign xmasCampaign = new Campaign("Santa is coming around");
        xmasCampaign.setDescription("Ho, ho, ho!");

        try {
            log.info(objectMapper.writeValueAsString(xmasCampaign));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        campaignRepository.save(xmasCampaign);
    }

    private void getDefaultQuests() {

    }

}
