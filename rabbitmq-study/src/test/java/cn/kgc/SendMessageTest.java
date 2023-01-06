package cn.kgc;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Kyler
 * Time: 2022/7/8 11:59
 * Target:
 */
@SpringBootTest
public class SendMessageTest {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSendMessage() {
        Map<String,Object> map = new HashMap<>();
        map.put("stuNo",1);
        map.put("stuName","zs");
        map.put("age",18);
        rabbitTemplate.convertAndSend("exchangeDirect","key2",map);
        // rabbitTemplate.convertAndSend("queue1","队列2接收到");
    }
}
