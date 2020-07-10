package com.dascheduler.emailservice;

import com.dascheduler.model.SenderDetails;
import com.dascheduler.scheduler.DaScheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SchedulerService {

    @Value("${spring.mail.username}")
    private String mailAddress;

    private JavaMailSender javaMailSender;

    public SchedulerService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void schedulerEmail(SenderDetails user) throws MailException{
        Logger logger = Logger.getLogger(SchedulerService.class.getName());
        logger.info("Sending email to "+user.getEmailAddress());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmailAddress());
        mailMessage.setFrom(mailAddress);
        mailMessage.setSubject("Test Message");
        mailMessage.setText(user.getMessage());

        javaMailSender.send(mailMessage);
    }
}
