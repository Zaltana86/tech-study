package com.ivx;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/8/22 14:03
 * @apiNote  测试使用@Scheduled注解
*/

@Component
public class ScheduledTest {
    @Scheduled(cron="0/5 * * ? * *") // 默认上个任务执行完下个任务才执行 单线程的
    public void start() throws InterruptedException {
        System.out.println("hello world");
        Thread.sleep(5000);
    }
    @Scheduled(cron="0/3 * * ? * *")   // 开启多线程两个任务互不影响
    public void start1() {
        System.out.println("你好，世界！");
    }
}
