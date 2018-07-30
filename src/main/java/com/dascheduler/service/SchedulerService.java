package com.dascheduler.service;

import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private int i=0;

    public int schedulerEmail(){
        return i++;
    }
}
