package com.dascheduler.emailservice;

import com.dascheduler.model.SenderDetails;
import com.dascheduler.service.SchedulerService;
import com.dascheduler.service.SenderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private SenderDetailsService senderDetailsService;

    @Value("${spring.mail.username}")
    private String mailAddress;

    private JavaMailSender javaMailSender;

    public SchedulerServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void schedulerEmail(UUID id) throws MailException{
        Optional<SenderDetails> user = senderDetailsService.getSenderDetailsById(id);
        Logger logger = Logger.getLogger(SchedulerServiceImpl.class.getName());
        if (user.isPresent()) {
            logger.info("Sending email to " + user.get().getEmailAddress());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.get().getEmailAddress());
            mailMessage.setFrom(mailAddress);
            mailMessage.setSubject("Test Message");
            mailMessage.setText(user.get().getMessage());

            javaMailSender.send(mailMessage);
        }
        else {
            logger.info("The user with id:" +id+" is not present");
        }
    }
}
