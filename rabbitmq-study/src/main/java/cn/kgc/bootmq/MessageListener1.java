package cn.kgc.bootmq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author: Kyler
 * Time: 2022/7/8 11:52
 * Target:
 */
@Component
@RabbitListener(queues = "sms_code")
public class MessageListener1 {
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("接收到消息： " + message);
    }
}
