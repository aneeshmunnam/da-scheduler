package com.dascheduler.scheduler;

import com.dascheduler.service.SchedulerService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class SchedulerEmailJob implements Job {

    private static final Logger logger = Logger.getLogger(SchedulerEmailJob.class.getName());

    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getDescription();
        logger.info("Executing "+jobName);
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        UUID senderDetailId = UUID.fromString(dataMap.getString("senderDetailsId"));
        schedulerService.schedulerEmail(senderDetailId);
    }
}
