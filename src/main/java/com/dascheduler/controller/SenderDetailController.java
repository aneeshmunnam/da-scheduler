package com.dascheduler.controller;

import com.dascheduler.emailservice.SchedulerService;
import com.dascheduler.model.SenderDetails;
import com.dascheduler.scheduler.DaScheduler;
import com.dascheduler.service.SenderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.UUID;

@Controller
@RequestMapping(path = "/")
public class SenderDetailController {

    @Autowired
    private SenderDetailsService senderDetailsService;

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private DaScheduler daScheduler;

    @RequestMapping(method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("senderDetails", senderDetailsService.getSenderDetails());
        return "index";
    }

    @RequestMapping(value = "senderDetails", method = RequestMethod.GET)
    public String addSender(Model model) {
        model.addAttribute("senderDetails", new SenderDetails());
        return "addSender";
    }

    @GetMapping("all")
    private Object getAllSenderDetails() {
        return senderDetailsService.getSenderDetails();
    }

    @RequestMapping(value = "senderDetails", method = RequestMethod.POST)
    public String createSenderDetails(@Valid SenderDetails senderDetails, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addSender";
        }
        System.out.println(senderDetails.getTime());
        SenderDetails savedDetails = senderDetailsService.createSenderDetail(senderDetails);
        if (savedDetails == null)
            return null;
        //test for Simple Quartz Scheduler
        daScheduler.quartzSchedulerBasicTest(savedDetails);
        model.addAttribute("senderDetails", senderDetailsService.getSenderDetails());
        return "index";
    }

    @PutMapping
    private Object updateSenderDetails(@RequestBody @Valid SenderDetails updateDetails) {
        return senderDetailsService.updateSenderDetail(updateDetails);
    }

    @DeleteMapping("senderDetails/{senderId}")
    private void deleteSenderDetails(@PathVariable("senderId")UUID senderId) {
        senderDetailsService.deleteSenderDetail(senderId);
    }

    @GetMapping("senderDetails/{id}")
    private Object getSenderDetailsById(@PathVariable("id") UUID id){
        return senderDetailsService.getSenderDetailsById(id);
    }

    @GetMapping("email/{emailAddress}")
    private Object getSenderDetailsByEmailAddress(@PathVariable("emailAddress") String emailAddress) {
        return senderDetailsService.getSenderDetailsByEmailAddress(emailAddress);
    }

}
