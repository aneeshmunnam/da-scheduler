package com.dascheduler.controller;

import com.dascheduler.model.SenderDetails;
import com.dascheduler.service.SenderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/senderDetails")
public class SenderDetailController {

    @Autowired
    private SenderDetailsService senderDetailsService;

    @GetMapping
    private Object getAllSenderDetails() {
        return senderDetailsService.getSenderDetails();
    }

    @PostMapping
    private Object createSenderDetails(@RequestBody @Valid SenderDetails senderDetails) {
        return senderDetailsService.createSenderDetail(senderDetails);
    }

    @PutMapping
    private Object updateSenderDetails(@RequestBody @Valid SenderDetails updateDetails) {
        return senderDetailsService.updateSenderDetail(updateDetails);
    }

    @DeleteMapping("/{senderId}")
    private void deleteSenderDetails(@PathVariable("senderId")UUID senderId) {
        senderDetailsService.deleteSenderDetail(senderId);
    }

    @GetMapping("/{id}")
    private Object getSenderDetailsById(@PathVariable("id") UUID id){
        return senderDetailsService.getSenderDetailsById(id);
    }

    @GetMapping("email/{emailAddress}")
    private Object getSenderDetailsByEmailAddress(@PathVariable("emailAddress") String emailAddress) {
        return senderDetailsService.getSenderDetailsByEmailAddress(emailAddress);
    }

}
