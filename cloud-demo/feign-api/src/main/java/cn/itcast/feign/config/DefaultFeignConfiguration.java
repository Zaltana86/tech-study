package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {
    public static final String FEIGN_PREFIX = "feign";
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
