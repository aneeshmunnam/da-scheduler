package com.dascheduler.scheduler;

import com.dascheduler.model.SenderDetails;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class DaScheduler {

    Logger logger = Logger.getLogger(DaScheduler.class.getName());

    /**
     *
     * @param senderDetails
     */
    @SuppressWarnings("unchecked")
    public void quartzSchedulerBasicTest(SenderDetails senderDetails) {
        try {
            SchedulerFactory stf = new StdSchedulerFactory();
            Scheduler scheduler = stf.getScheduler();
            scheduler.start();
            JobDetail jobDetail = JobBuilder
                                .newJob(SchedulerEmailJob.class)
                                .withIdentity("SenderDetails", "senderDetails_"+senderDetails.getId())
                                .build();
            jobDetail.getJobDataMap().put("senderDetailsId", senderDetails.getId().toString());
            //this would run at 8 every day
            Trigger trigger = TriggerBuilder.newTrigger()
                                .forJob("SenderDetails","senderDetails_"+senderDetails.getId())
                                .withIdentity("SenderDetailsTrigger","senderDetails_"+senderDetails.getId())
                                .withSchedule(CronScheduleBuilder.cronSchedule(convertDateToCron(senderDetails.getTime())))
                                .build();
            Date ft = scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Email service is scheduled at "+ft);
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
