package com.java.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * 通过implements Job 这个接口，来实现一个任务，execute
 * Trigger：任务调度策略，SimpleTrigger(适用于在某个特定时间来执行),CronTrigger(
 * 基于日历的调度安排,CronExpression:seconds minute hours dayOfMonth month dayOfWeek year
 * seconds:[-],[*],[/] (-:取具体的值，/:/前面表示执行时间，/后面的表示间隔的时间，1/1:一秒执行一次，间隔一秒执行) 0-59;
 * minute:[-],[*],[/] 0-59;
 * hours:[-],[*],[/] 0-23;
 * dayOfMonth:[-],[*],[/],[?],[L W C](?:没有具体值，dayOfMonth与dayOfWeek不能同时为*或?,L:last 5L表示最后一个星期四触发,W:有效的工作日,C:SUN SAT),
 * month:[-],[*],[/],
 * dayOfWeek:[-],[*],[/],[?].[L C #],
 * year
 * )
 * Listener：JobListener,TriggerListener,ScheduledListener
 */
public class QuartzDemo implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("execution:" + jobExecutionContext.getJobDetail().getJobDataMap().get("name"));
        throw new RuntimeException("异常");
    }
    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail("firstJob","myJobGroup",QuartzDemo.class);
            jobDetail.getJobDataMap().put("name","zhou");
            jobDetail.addJobListener("myListener");
            /*Trigger trigger = TriggerUtils.makeWeeklyTrigger(2,21,25);
            trigger.setName("myTrigger");
            trigger.setGroup("myTriggerGroup");
            trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));*/
            CronTrigger trigger = new CronTrigger();
            trigger.setName("myTrigger");
            trigger.setGroup("myTriggerGroup");
            trigger.setCronExpression("1/1 * * * * ?");
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.addJobListener(new ListenerDemo());
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
