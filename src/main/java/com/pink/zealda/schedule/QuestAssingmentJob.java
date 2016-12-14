package com.pink.zealda.schedule;

import com.pink.zealda.listener.QuestListener;
import com.pink.zealda.model.QuestOfLegend;
import com.pink.zealda.service.QuestOfLegendService;
import com.pink.zealda.service.SlackService;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestAssingmentJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(QuestListener.class);

    @Autowired
    SlackService slackService;

    @Autowired
    SlackSession slackSession;

    @Autowired
    QuestOfLegendService questOfLegendService;

    @Value("${slack.bot.channel}")
    String channel;

    private String message;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("executed job to assign quests to legends");
        List<QuestOfLegend> questOfLegends = questOfLegendService.assignRandomQuestToLegends();
        questOfLegends.forEach((qol) -> questOfLegendService.sendNewQuestOfLegendMessage(qol));
    }

    /**
     * Used by Quartz to load message from properties
     *
     * @param message stand-up message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
