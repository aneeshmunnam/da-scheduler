package com.dascheduler.scheduler;

import com.dascheduler.model.SenderDetails;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class DaScheduler {

    private Class<? extends Job> jobClass;

    @Autowired
    private Scheduler scheduler;

    Logger logger = Logger.getLogger(DaScheduler.class.getName());

    public DaScheduler(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    /**
     *
     * @param senderDetails
     */
    public void addSchedulerJob(SenderDetails senderDetails) {
        try {
            scheduler.start();
            JobDetail jobDetail = JobBuilder
                                .newJob(jobClass)
                                .withIdentity("SenderDetails", "senderDetails_"+senderDetails.getId())
                                .build();
            jobDetail.getJobDataMap().put("senderDetailsId", senderDetails.getId().toString());
            //this would run at 8 every day
            Trigger trigger = TriggerBuilder.newTrigger()
                                .forJob("SenderDetails","senderDetails_"+senderDetails.getId())
                                .withIdentity("SenderDetailsTrigger","senderDetails_"+senderDetails.getId())
                                .withSchedule(CronScheduleBuilder.cronSchedule(convertDateToCron(senderDetails.getTime())))
                                .build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Email service is scheduled at "+senderDetails.getTime());
        } catch (SchedulerException se) {
            se.printStackTrace();
        }

    }

    /**
     * Convert date to CronExpression with seconds being 0
     * @param date
     * @return cron expression
     */
    private String convertDateToCron(Date date) {
        if (date == null)
            return "";
        Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);

        String hours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

        String mins = String.valueOf(calendar.get(Calendar.MINUTE));

        String days = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String months = new java.text.SimpleDateFormat("MM").format(calendar.getTime());

        String years = String.valueOf(calendar.get(Calendar.YEAR));

        return "0 " + mins+ " " + hours + " " + days + " " + months + " ? " + years;
    }
}
