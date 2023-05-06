package cn.kgc.bootmq;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Author: Kyler
 * Time: 2022/7/8 11:52
 * Target:
 */
@Component
public class MessageListener {
    // @RabbitHandler
    @RabbitListener(queues = "simple.queue")
    public void receiveMessage1(String message) throws InterruptedException {
        System.out.println("方法1.....接收到消息： [" + message + "]  " + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void receiveMessage2(String message) throws InterruptedException {
        System.err.println("方法2.....接收到消息： [" + message + "]  " + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void fanoutQueue1Message(String message) {
        System.out.println("fanout.queue1.....接收到消息： [" + message + "]  " + LocalTime.now());
    }

    @RabbitListener(queues = "fanout.queue2")
    public void fanoutQueue2Message(String message) {
        System.out.println("fanout.queue2.....接收到消息： [" + message + "]  " + LocalTime.now());
    }

    // 通过注解的方式声明创建队列 ---direct
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("direct.queue1"),
                    exchange = @Exchange("direct.exchange"),
                    key = {"blue", "red"}
            )
    )
    public void directQueue1Message(String message) {
        System.out.println("direct.queue1.....接收到消息：[" + message + "] ");
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("direct.queue2"),
                    exchange = @Exchange(value = "direct.exchange", type = ExchangeTypes.DIRECT), // 默认为direct
                    key = {"yellow", "red"}
            )
    )
    public void directQueue2Message(String message) {
        System.out.println("direct.queue2.....接收到消息：[" + message + "] ");
    }

    // topic交换机
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("topic.queue1"),
                    exchange = @Exchange(value = "topic.exchange", type = ExchangeTypes.TOPIC),
                    key = "china.#"
            )
    )
    public void topicQueue1Message(String message) {
        System.out.println("topic.queue1....接收到消息：[" + message + "]");
    }

    // topic交换机
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("topic.queue2"),
                    exchange = @Exchange(value = "topic.exchange", type = ExchangeTypes.TOPIC),
                    key = "#.news"
            )
    )
    public void topicQueue2Message(String message) {
        System.out.println("topic.queue2....接收到消息：[" + message + "]");
    }
}
