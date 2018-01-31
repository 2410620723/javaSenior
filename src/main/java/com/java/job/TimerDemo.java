package com.java.job;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * java.util.Timer的定时任务调度
 * @author zxm
 */
public class TimerDemo extends TimerTask {

    @Override
    public void run() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("timer execution:"+format.format(this.scheduledExecutionTime()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * timer 的优缺点
     * 所有的任务都是由同一个线程来调度，所以所有任务的执行串行，
     * 意味在同一时间只能执行一个任务，而前一个任务的延迟都会影响后面任务的执行。
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        // 每次执行时间为上次任务结束起向后推一个时间间隔，不会延后。
        timer.schedule(new TimerDemo(),1000,1000);

        // 每次执行时间为上次任务开始起向后推一个时间间隔，会存在并发问题
//        timer.scheduleAtFixedRate(new TimerDemo(),1000,1000);
    }
}
