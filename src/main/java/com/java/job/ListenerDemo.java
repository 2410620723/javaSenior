package com.java.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class ListenerDemo implements JobListener {
    @Override
    public String getName() {
        return "myListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("执行之前调用的方法");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        // 与TriggerListen一同使用
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("任务执行完毕");
        if (e != null) {
            System.out.println("发送邮件给管理员");
        }
    }
}
