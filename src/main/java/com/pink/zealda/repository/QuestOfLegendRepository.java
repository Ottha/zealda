package com.pink.zealda.repository;

import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestOfLegendRepository extends MongoRepository<QuestOfLegend, String> {

}
