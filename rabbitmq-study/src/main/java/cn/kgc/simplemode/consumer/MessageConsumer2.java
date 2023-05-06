package cn.kgc.simplemode.consumer;


import cn.kgc.util.RabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Author: Kyler
 * Time: 2022/7/7 16:11
 * Target: 每个消费者从队列接受
 */

public class MessageConsumer2 {
    public static void main(String[] args) throws Exception {
        System.out.println("消费者2启动成功");
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);  // 每次取一个消息
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("接受消息成功！" + new String(body));   // 手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false); // multiple
                // System.out.println("确认消息");
            }
        };
        // 关闭自动确认
        channel.basicConsume(queueName, false, consumer);
    }
}
