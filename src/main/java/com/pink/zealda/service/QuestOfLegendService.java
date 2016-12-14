package com.pink.zealda.service;

import com.pink.zealda.model.Legend;
import com.pink.zealda.model.Quest;
import com.pink.zealda.model.QuestOfLegend;
import com.pink.zealda.repository.QuestOfLegendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    public List<QuestOfLegend> assignRandomQuestToLegends() {
        Set<String> legendsWithQuest = questOfLegendRepository.findAll().stream().filter(questOfLegend -> !questOfLegend.isSucceeded())
            .map(questOfLegend -> questOfLegend.getLegend().getId())
            .collect(Collectors.toSet());

        List<Legend> legends = legendService.findAll().stream().filter(legend -> !legendsWithQuest.contains(legend.getId())).collect(Collectors.toList());
        return legends.stream().map(legend -> questService.assignQuestToLegend(getNextQuestForLegend(legend), legend)).collect(Collectors.toList());
    }

    public Quest getNextQuestForLegend(Legend legend) {

        List<Quest> allQuests = questService.getAllQuests();
        int i = new Random().nextInt(allQuests.size());
        return allQuests.get(i);
    }

    public void sendNewQuestOfLegendMessage(QuestOfLegend questOfLegend) {
        questService.sendNewQuestMessage(questOfLegend.getLegend().getName(), questOfLegend.getQuest());
    }

    public void solvePendingQuest(Legend legend) {
        Optional<QuestOfLegend> questOfLegend = questOfLegendRepository.findByLegend(legend).stream().filter(questOfLegendPredicate -> !questOfLegendPredicate.isSucceeded()).findFirst();
        if (questOfLegend.isPresent()) {
            QuestOfLegend qol = questOfLegend.get();
            qol.setSucceeded(true);
            questOfLegendRepository.save(qol);
            legend.setCurrentXp(legend.getCurrentXp() + qol.getQuest().getExpGain());
            legendService.save(legend);
            sendQuestOfLegendSolvedMessage(qol);
        }
    }

    private void sendQuestOfLegendSolvedMessage(QuestOfLegend questOfLegend) {
        questService.sendSolvedQuestMessage(questOfLegend.getLegend(), questOfLegend.getQuest());
    }


}
