package com.pink.zealda.repository;

import com.pink.zealda.model.Legend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LegendRepository extends MongoRepository<Legend, String> {

    Legend findByName(String legendName);

}
