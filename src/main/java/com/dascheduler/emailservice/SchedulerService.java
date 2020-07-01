package com.dascheduler.emailservice;

import com.dascheduler.model.SenderDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Value("${spring.mail.username}")
    private String mailAddress;

    private JavaMailSender javaMailSender;

    public SchedulerService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void schedulerEmail(SenderDetails user) throws MailException{

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailAddress);
        mailMessage.setSubject("Message");
        mailMessage.setText("Hello! hi");

        javaMailSender.send(mailMessage);
    }
}
