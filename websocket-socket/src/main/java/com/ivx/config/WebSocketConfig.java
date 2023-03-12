package com.ivx.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2023/2/14 15:24
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serviceEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
