package com.ivx.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/8/30 17:38
 * @apiNote  token的配置类
*/
@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore() {

        // token存于内存
        return new InMemoryTokenStore();
    }
}
