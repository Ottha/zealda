package com.pink.zealda.repository;

import com.pink.zealda.model.Quest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestRepository extends MongoRepository<Quest, String> {

    Quest findByName(String questName);
//    Quest findBy
}
