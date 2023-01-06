package com.ivx;

import org.quartz.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@PersistJobDataAfterExecution   // 加了这个注解可以实现有状态的job
public class TestJob implements Job {

    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("次数:" + count);
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        jobDataMap.put("count", ++count);
        System.out.println(jobDataMap.get("key1"));
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("key2"));
        String data = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("START DATA BACKUP, current time ：" + data);
    }
}