package com.ivx;


import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/8/19 13:46
 * @apiNote
*/
@SpringBootApplication
@EnableScheduling  // 支持Scheduling
public class QuartzApplication {
    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(QuartzApplication.class,args);
        TestScheduler.start();
    }
}
