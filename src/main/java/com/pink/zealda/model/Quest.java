package com.pink.zealda.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Quest {

    @Id
    public String id;

    @Indexed(unique = true)
    private String name;
    private String description;
    private long expGain;
    private int levelRequired;
    private String category;
    private boolean repeatable;
    private long timeframeInMinutes;

    public Quest() {
    }

    public Quest(String name) {
        this.name = name;
    }

    private Quest(Builder builder) {
        this.id = com.google.common.base.Preconditions.checkNotNull(builder.id);
        this.name = com.google.common.base.Preconditions.checkNotNull(builder.name);
        this.description = com.google.common.base.Preconditions.checkNotNull(builder.description);
        this.expGain = com.google.common.base.Preconditions.checkNotNull(builder.expGain);
        this.levelRequired = com.google.common.base.Preconditions.checkNotNull(builder.levelRequired);
        this.category = builder.category;
        this.repeatable = com.google.common.base.Preconditions.checkNotNull(builder.repeatable);
        this.timeframeInMinutes = com.google.common.base.Preconditions.checkNotNull(builder.timeframeInMinutes);
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

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private long expGain;
        private int levelRequired;
        private String category;
        private boolean repeatable;
        private long timeframeInMinutes;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder expGain(long expGain) {
            this.expGain = expGain;
            return this;
        }

        public Builder levelRequired(int levelRequired) {
            this.levelRequired = levelRequired;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder repeatable(boolean repeatable) {
            this.repeatable = repeatable;
            return this;
        }

        public Builder timeframeInMinutes(long timeframeInMinutes) {
            this.timeframeInMinutes = timeframeInMinutes;
            return this;
        }

        public Builder fromPrototype(Quest prototype) {
            id = prototype.id;
            name = prototype.name;
            description = prototype.description;
            expGain = prototype.expGain;
            levelRequired = prototype.levelRequired;
            category = prototype.category;
            repeatable = prototype.repeatable;
            timeframeInMinutes = prototype.timeframeInMinutes;
            return this;
        }

        public Quest build() {
            return new Quest(this);
        }
    }
}
