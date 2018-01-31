package com.java.job;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 设计理念：每一个被调度的任务都会由线程池中的一个线程去执行，因此任务可以并发执行，互不影响。
 * 只有当任务执行的时间到来时，ScheduleExecutor才会真正启动一个线程。
 * 缺点：没有简单的办法实现一个周期的定时任务的调度。
 *
 * newFixedThreadPool:创建一个工作线程数量的线程池
 * newScheduledThreadPool:可以定时轮询的线程池
 * newSingleThreadExecutor:创建一个唯一的线程来执行任务
 * newCachedThreadPool:创建一个可缓存的线程池
 *
 * 中断策略：
 * AbortPolicy：终止，它是默认执行策略
 * CallerRunsPolicy：调用者运行
 * DiscardPolicy：遗弃
 * DiscardOldestPolicy：遗弃最旧的
 */
public class ScheduledExecutorDemo implements Runnable{

    @Override
    public void run() {
        System.out.println("任务执行：" + new Date());
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        // 每周二晚上21:44执行一个定时任务
        Calendar calendar = Calendar.getInstance();
        long currentDateLong = calendar.getTime().getTime();
        System.out.println("当前时间：" + calendar.getTime().toString());
        Calendar earliestDate = Calendar.getInstance();//最近一次执行时间
        earliestDate.set(Calendar.DAY_OF_WEEK,3);// 周二
        earliestDate.set(Calendar.HOUR_OF_DAY,21);
        earliestDate.set(Calendar.MINUTE,44);
        earliestDate.set(Calendar.SECOND,0);
        long earliestLong = earliestDate.getTime().getTime();
        long delay = earliestLong - currentDateLong;
        long period = 7*24*3600*1000;
        service.scheduleAtFixedRate(new ScheduledExecutorDemo(),delay,period, TimeUnit.MILLISECONDS);
    }
}
