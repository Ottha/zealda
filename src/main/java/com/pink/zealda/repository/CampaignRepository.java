package com.pink.zealda.repository;

import com.pink.zealda.model.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

    Campaign findByName(String campaignName);

}
