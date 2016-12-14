package com.pink.zealda.repository;

import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestOfLegendRepository extends MongoRepository<QuestOfLegend, String> {

    List<QuestOfLegend> findByLegend(Legend legend);
}
