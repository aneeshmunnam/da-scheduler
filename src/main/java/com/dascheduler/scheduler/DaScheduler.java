package com.dascheduler.scheduler;

import com.dascheduler.model.SenderDetails;
import com.dascheduler.emailservice.SchedulerService;
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

//    SenderDetails user = new SenderDetails("aneesh", "munnam", "xxxxxxxxxxxxxx@gmail.com");
    SenderDetails user = null;

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
