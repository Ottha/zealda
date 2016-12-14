package com.pink.zealda.service;

import com.pink.zealda.model.Legend;
import com.pink.zealda.repository.LegendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class LegendService {

    @Autowired
    LegendRepository legendRepository;

    @Autowired
    MessageFormattingService messageFormattingService;

    public Legend findByName(String name) {
        return legendRepository.findByName(name);
    }

    public Legend createLegendByName(String legendName) {
        return legendRepository.save(new Legend(legendName));
    }

    public List<Legend> findAll() {
        return legendRepository.findAll();
    }

    public void deleteLegends(List<Legend> legends) {
        legendRepository.delete(legends);
    }

    public Legend save(Legend legend) {
        return legendRepository.save(legend);
    }

    public void showScoreBoard(Legend legend) {
        List<Legend> legendsSorted = legendRepository.findAll().stream().sorted(legendXPComparator).collect(Collectors.toList());
        sendScoreBoard(legend, legendsSorted);
    }

    private void sendScoreBoard(Legend legend ,List<Legend> legendList) {
        List<String> message = new ArrayList<>();
        message.add(":trophy: Thee current list of legends is: ");
        legendList.stream().map(legend1 -> formattedScoreBoardEntry(legend1, legendList)).forEach(message::add);

        messageFormattingService.sendFormattedMessage(legend.getName(), message);
    }

    private String formattedScoreBoardEntry(Legend legend, List<Legend> legendList) {
        return "*#" + Math.addExact(legendList.indexOf(legend), 1) + "* - " + legend.getName() + "\t" + messageFormattingService.getBeautifiedXP(legend.getCurrentXp());
    }

    private Comparator<Legend> legendXPComparator = Comparator.comparingLong(Legend::getCurrentXp).reversed();
}
