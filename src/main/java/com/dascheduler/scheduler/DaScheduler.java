package com.dascheduler.scheduler;

import com.dascheduler.model.SenderDetails;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class DaScheduler {

    Logger logger = Logger.getLogger(DaScheduler.class.getName());

    public void quartzSchedulerBasicTest(SenderDetails senderDetails) {
        try {
            SchedulerFactory stf = new StdSchedulerFactory();
            Scheduler scheduler = stf.getScheduler();
            JobDetail jobDetail = JobBuilder
                                .newJob(SchedulerEmailJob.class)
                                .withIdentity("SenderDetails", "senderDetails_"+senderDetails.getId()).build();
            jobDetail.getJobDataMap().put("senderDetailsId", senderDetails.getId());
            //this would run at 8 every day
            Trigger trigger = TriggerBuilder.newTrigger()
                                .forJob("SenderDetails","senderDetails_"+senderDetails.getId())
                                .withIdentity("SenderDetails","senderDetails_"+senderDetails.getId())
                                .withSchedule(CronScheduleBuilder.cronSchedule("0 8 * * * ?"))
                                .build();
            Date ft = scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Email service is scheduled at "+ft);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }

    }
}
