package com.pink.zealda.schedule;

import com.pink.zealda.service.SlackService;
import com.ullink.slack.simpleslackapi.SlackChannel;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StandupJob extends QuartzJobBean {

    @Autowired
    SlackService slackService;

    @Autowired
    SlackChannel defaultChannel;

    private String message;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        slackService.sendMessage(message, defaultChannel);
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
