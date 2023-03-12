package com.ivx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/19 11:43
 */
@SpringBootApplication
@EnableScheduling
public class WSApplication {
    public static void main(String[] args) {
        LocalCache.loadingCache.put("key", "value");
        SpringApplication.run(WSApplication.class, args);

    }

}
