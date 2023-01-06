package cn.kgc.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Author: Kyler
 * Time: 2022/7/7 15:34
 * Target:
 */

public class RabbitmqUtil {
    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // 配置连接参数
        factory.setHost("212.129.223.35");
        factory.setPort(5672);
        factory.setVirtualHost("/kgc");
        factory.setUsername("kyler");
        factory.setPassword("123.com");
        return factory.newConnection();
    }
}
