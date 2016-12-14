package com.pink.zealda.service;

import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import com.pink.zealda.repository.LegendRepository;
import com.pink.zealda.repository.QuestOfLegendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestOfLegendService {

    @Autowired
    QuestOfLegendRepository questOfLegendRepository;

    @Autowired
    LegendService legendService;

    @Autowired
    QuestService questService;

    public List<QuestOfLegend> assignQuestToRandomLegend() {
        Set<String> legendsWithQuest = questOfLegendRepository.findAll().stream().filter(questOfLegend -> !questOfLegend.isSucceeded())
            .map(questOfLegend -> questOfLegend.getLegendId().getId())
            .collect(Collectors.toSet());

        List<Legend> legends = legendService.findAll().stream().filter(legend -> !legendsWithQuest.contains(legend.getId())).collect(Collectors.toList());
        return legends.stream().map(legend -> questService.assignQuestToLegend(getNextQuestForLegend(legend), legend)).collect(Collectors.toList());
    }

    private Quest getNextQuestForLegend(Legend legend) {
        return questService.getAllQuests().get(0);
    }

}
