package com.ivx;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class TestScheduler {
    public static void start() throws SchedulerException {
        // 获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // scheduler.shutdown();
        // scheduler.standby();  // 挂起
        // scheduler.start();
        // 定义任务调度实例, 并与TestJob绑定
        JobDataMap jobDataMap1 = new JobDataMap();
        JobDataMap jobDataMap2 = new JobDataMap();
        jobDataMap1.put("key1", "value1");
        jobDataMap2.put("key2", "value2");

        JobDetail job = JobBuilder.newJob(TestJob.class)
                .withIdentity("testJob", "testJobGroup")
                .usingJobData(jobDataMap1).usingJobData("count", 1)
                .build();


        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever(5)
                .withRepeatCount(2);  // simpletrigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "testTriggerGroup")
                .usingJobData(jobDataMap2)
                .startAt(new Date())
                // .startNow()
                // .endAt(DateUtil.parse("2022-8-19 14:03:59", DatePattern.NORM_DATETIME_FORMAT))
                .withSchedule(simpleScheduleBuilder
                )
                .build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
         /*
        CronTrigger
        跟 SimpleTrigger 执行间隔时间触发的相比，
        CronTrigger 更加灵活，它是基于日历的作业调度器。
        使用 CronTrigger 我们可以执行某个时间点执行，例如 “每天的凌晨1点执行”、
        “每个工作日的 12 点执行”，也可以像 SimpleTrigger 那样执行一个开始时间和结束时间运行任务
        秒 分 时 每月第几天 月 每周第几天 年 年可以不写
        如每年六月一号 0:00
        0 0 0 1 6 ? *
         */
        // CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("* 59 15 * * ?");  // ? 表示每一个 每月周五触发


        // // 定义触发器, 会马上执行一次, 接着5秒执行一次
        // Trigger trigger = TriggerBuilder.newTrigger()
        //         .withIdentity("testTrigger", "testTriggerGroup")
        //         .usingJobData(jobDataMap2)
        //         .startAt(new Date())
        //         // .startNow()
        //         // .endAt(DateUtil.parse("2022-8-19 14:03:59", DatePattern.NORM_DATETIME_FORMAT))
        //         .withSchedule(cronScheduleBuilder
        //         )
        //         .build();
        // // 使用触发器调度任务的执行
        // scheduler.scheduleJob(job, trigger);
        // // 开启任务
        // scheduler.start();
    }
}
