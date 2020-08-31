package com.dascheduler.scheduler;

import com.dascheduler.emailservice.SchedulerService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchedulerEmailJob implements Job {

    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getDescription();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        UUID senderDetailId = UUID.fromString(dataMap.getString("senderDetailsId"));
        schedulerService.schedulerEmail(senderDetailId);
    }
}
