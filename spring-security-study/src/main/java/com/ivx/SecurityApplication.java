package com.ivx;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/8/3 11:46
 * Target:
 */

@SpringBootApplication
@MapperScan("com.ivx.dao")
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}
