package com.pink.zealda.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jobdetails.quest.assignment")
public class QuestAssignmentProperties {

    public String defaultMessage;
    public String time;

}
