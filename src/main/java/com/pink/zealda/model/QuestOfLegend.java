package com.pink.zealda.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by akraf on 12/14/16.
 */
@CompoundIndex(name = "questOfLegend", def = "{'legend': 1, 'quest': 1}")
public class QuestOfLegend {

    @Id
    private String id;

    @DBRef
    private Legend legend;
    @DBRef
    private Quest quest;

    private boolean succeeded;

    private long startTimeInMillis;

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public long getStartTimeInMillis() {
        return startTimeInMillis;
    }

    public void setStartTimeInMillis(long startTimeInMillis) {
        this.startTimeInMillis = startTimeInMillis;
    }
}
