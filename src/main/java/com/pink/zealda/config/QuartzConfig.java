package com.pink.zealda.config;

import com.pink.zealda.properties.QuartzProperties;
import com.pink.zealda.schedule.StandupJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Configures Quartz beans
 * https://github.com/davidkiss/spring-boot-quartz-demo/blob/master/src/main/java/com/kaviddiss/bootquartz/SchedulerConfig.java
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    QuartzProperties quartzProperties;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(StandupJob.class);
        JobDataMap map = new JobDataMap();
        map.put("message", quartzProperties.greeting);
        factoryBean.setJobDataMap(map);
        return factoryBean;
    }

    @Bean(name = "standupJobTrigger")
    public CronTriggerFactoryBean standupJobTrigger(JobDetail jobDetail) {
        return trigger(jobDetail);
    }

    @Bean
    public SchedulerFactoryBean standupSchedulerFactoryBean(@Qualifier("standupJobTrigger") Trigger standupJobTrigger) {
        return schedulerFactoryBean(standupJobTrigger);
    }


    @Bean(name = "questTrigger")
    public CronTriggerFactoryBean questTrigger(JobDetail jobDetail) {
        return trigger(jobDetail);
    }

    @Bean
    public SchedulerFactoryBean questSchedulerFactoryBean(@Qualifier("questTrigger") Trigger questTrigger) {
        return schedulerFactoryBean(questTrigger);
    }




    private CronTriggerFactoryBean trigger(JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(createCronExpression(jobDetail));
        return factoryBean;
    }

    private String createCronExpression(JobDetail jobDetail) {
        return quartzProperties.time;
    }


    private SchedulerFactoryBean schedulerFactoryBean(Trigger trigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(trigger);
        // Job factory using Spring DI
        QuartzJobFactory jobFactory = new QuartzJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        factoryBean.setJobFactory(jobFactory);
        return factoryBean;
    }




}
