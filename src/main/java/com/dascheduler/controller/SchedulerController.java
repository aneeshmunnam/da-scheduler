package com.dascheduler.controller;

import com.dascheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping
    public int schedulerTest(){
        return schedulerService.schedulerEmail();
    }
}
