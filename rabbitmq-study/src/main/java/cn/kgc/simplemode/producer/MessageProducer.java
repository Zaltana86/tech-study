package cn.kgc.simplemode.producer;


import cn.kgc.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * Author: Kyler
 * Time: 2022/7/7 15:43
 * Target: 发布到队列里面
 */

public class MessageProducer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel(); // 创建信道
        channel.queueDeclare("simple.queue", false, false, false, null);
        for (int i = 0; i < 50; i++) {
            channel.basicPublish("", "simple.queue", MessageProperties.PERSISTENT_TEXT_PLAIN, ("helloworld" + i).getBytes(StandardCharsets.UTF_8));
        }
        System.out.println("消息发送完毕");
        // 释放资源
        channel.close();
        connection.close();
    }
}
