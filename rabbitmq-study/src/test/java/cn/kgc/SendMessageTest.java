package cn.kgc;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Author: Kyler
 * Time: 2022/7/8 11:59
 * Target:
 */
@SpringBootTest
public class SendMessageTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    // 简单模型
    @Test
    public void testSendMessage1() {
        rabbitTemplate.convertAndSend("simple.queue", "hello,queue1");
    }

    // 工作模型
    @Test
    public void testSendMessage2() throws InterruptedException {
        // 每秒钟生产五十条数据
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend("simple.queue", "hello" + i);
            Thread.sleep(20);
        }
    }

    /**
     * 广播模型，每个队列都要接受消息
     */
    @Test
    public void sendFanoutExchange() {
        String exchangeName = "fanout.exchange";
        String message = "hello,everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    /**
     * direct模型，指定队列接受消息
     */
    @Test
    public void sendDirectExchange() {
        String exchangeName = "direct.exchange";
        String message = "hello,red!";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    /**
     * topic 模型
     */
    @Test
    public void sendTopicExchange() {
        String exchangeName = "topic.exchange";
        String message = "今天天气不错";
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }
}
