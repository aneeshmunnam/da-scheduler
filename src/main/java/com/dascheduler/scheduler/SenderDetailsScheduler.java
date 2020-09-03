package com.dascheduler.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderDetailsScheduler extends DaScheduler {

    @Autowired
    public SenderDetailsScheduler() {
        super(SchedulerEmailJob.class);
    }
}
