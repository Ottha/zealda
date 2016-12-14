package com.pink.zealda.service;

import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.repository.LegendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegendService {

    @Autowired
    LegendRepository legendRepository;

    public Legend findByName(String name) {
        return legendRepository.findByName(name);
    }


}
