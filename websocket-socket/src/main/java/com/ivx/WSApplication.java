package com.ivx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/19 11:43
 */
@SpringBootApplication
@EnableWebSocket
@EnableScheduling
public class WSApplication {
    public static void main(String[] args) {
        LocalCache.loadingCache.put("key", "value");
        SpringApplication.run(WSApplication.class, args);
    }

    /**
     * 初始化Bean，它会自动注册使用了 @ServerEndpoint 注解声明的 WebSocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    // @Scheduled(cron = "0/1 * * * * *")
    // public void queryState() {
    //     Cache<String, String> loadingCache = LocalCache.loadingCache;
    //     String value = loadingCache.get("key", LocalCache::getValueFromDB);
    //     if (value == null) {
    //         System.out.println("key失效");
    //     } else {
    //         System.out.println("key未失效");
    //     }
    // }
}
