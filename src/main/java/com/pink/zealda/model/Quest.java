package com.pink.zealda.model;


import org.springframework.data.annotation.Id;

public class Quest {

    @Id
    public String id;

    private String name;
    private String description;
    private long expGain;
    private int levelRequired;
    private String category;
    private boolean repeatable;
    private long timeframeInMinutes;

    public Quest(String name) {
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

    public long getExpGain() {
        return expGain;
    }

    public void setExpGain(long expGain) {
        this.expGain = expGain;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public long getTimeframeInMinutes() {
        return timeframeInMinutes;
    }

    public void setTimeframeInMinutes(long timeframeInMinutes) {
        this.timeframeInMinutes = timeframeInMinutes;
    }

    @Override
    public String toString() {
        return "Quest{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", expGain=" + expGain +
            ", levelRequired=" + levelRequired +
            ", category='" + category + '\'' +
            ", repeatable=" + repeatable +
            ", timeframeInMinutes=" + timeframeInMinutes +
            '}';
    }
}
