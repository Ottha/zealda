package com.pink.zealda.model;


import org.springframework.data.annotation.Id;

import java.time.Duration;

public class Campaign {

    @Id
    private String id;

    private String name;
    private String description;
    private String reward;
    private long timeframe;
    private Duration duration;

    public Campaign(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public long getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(long timeframe) {
        this.timeframe = timeframe;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
