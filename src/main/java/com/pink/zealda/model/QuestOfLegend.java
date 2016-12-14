package com.pink.zealda.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by akraf on 12/14/16.
 */
public class QuestOfLegend {

    @DBRef
    private Legend legendId;
    @DBRef
    private Quest quest;

    private boolean succeeded;

    private long startTimeInMillis;

    public Legend getLegendId() {
        return legendId;
    }

    public void setLegendId(Legend legendId) {
        this.legendId = legendId;
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
