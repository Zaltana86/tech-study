package cn.kgc.bootmq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author: Kyler
 * Time: 2022/7/8 11:57
 * Target:
 */

@Component
@RabbitListener(queues = "queue2")
public class MessageListener2 {
    // @RabbitHandler
    // public void receiveMessage(String message) {
    //     System.out.println("接收到消息: " + message);
    // }
    @RabbitHandler
    public void receiveMessage(Map<String,Object> map) {  // 这里是特定的格式
        System.out.println("接收到消息: " + map.get("stuNo"));
    }
}
