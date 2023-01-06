package cn.kgc.pubsubmode.consumer;


import cn.kgc.util.RabbitmqUtil;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * Author: Kyler
 * Time: 2022/7/7 16:11
 * Target: 发布订阅消息模型  使用Fanout广播模型，订阅这个交换器的所有队列都会收到，
 * 还有direct 和 topic订阅模型
 */
public class MessageConsumer1 {
    public static void main(String[] args) throws Exception {
        System.out.println("消费者1启动成功");
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);  // 每次取一个消息
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("接受消息成功:" + new String(body));   // 手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false); // multiple
                // System.out.println("确认消息");
            }
        };
        // 关闭自动确认
        channel.basicConsume("queue2", false, consumer);
    }
}
