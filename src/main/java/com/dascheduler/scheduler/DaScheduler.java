package com.dascheduler.scheduler;

import com.dascheduler.model.User;
import com.dascheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DaScheduler {

    Logger logger = Logger.getLogger(DaScheduler.class.getName());

    @Autowired
    private SchedulerService schedulerService;

    User user = new User("aneesh", "munnam", "xxxxxxxxxxxxxx@gmail.com");

    @Scheduled(cron = "0 * * * * ?")
    public void run(){
        try {
            schedulerService.schedulerEmail(user);
            logger.info("Email sent from " + user.getEmailAddress());
        }catch (MailException e){
            logger.info("Error sending message"+e);
        }
    }
}
