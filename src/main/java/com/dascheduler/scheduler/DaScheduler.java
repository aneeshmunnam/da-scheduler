package com.dascheduler.scheduler;

import com.dascheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DaScheduler {

    Logger logger = Logger.getLogger(DaScheduler.class.getName());

    @Autowired
    private SchedulerService schedulerService;

    @Scheduled(cron = "0 * * * * ?")
    public void run(){
        int email = schedulerService.schedulerEmail();
        logger.info("Email sent to "+email);
    }
}
