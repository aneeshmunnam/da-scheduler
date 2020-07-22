package com.dascheduler.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DaScheduler {

    Logger logger = Logger.getLogger(DaScheduler.class.getName());

    public void quartzSchedulerBasicTest() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            logger.info("Spring Quartz Scheduler start/shutdown");
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }

    }
}
