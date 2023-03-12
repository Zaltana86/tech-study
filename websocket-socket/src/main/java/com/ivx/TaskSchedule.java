package com.ivx;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2023/2/14 15:27
 */

@Component
public class TaskSchedule {
    @Resource
    private WebSocketUtil webSocketUtil;

    @Scheduled(cron = "0 * * 14 * ?")
    public void send() throws IOException, InterruptedException {
        boolean result = webSocketUtil.sendMessage("hello", "1");
        while (!result) {
            System.out.println("发送失败,等待用户上线");
            Thread.sleep(5000);
            result = webSocketUtil.sendMessage("hello", "1");
        }
        System.out.println("发送成功" + "当前用户数" + WebSocketUtil.webSocketSet.size());
    }
}
