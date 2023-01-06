package com.ivx.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/8/30 15:29
 */
@Configuration
@EnableAuthorizationServer   // 开启授权服务
public class SecurityConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private TokenStore tokenStore;

    @Resource
    private ClientDetailsService clientDetailService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 内存方式存储
                .withClient("c1")  // 客户端id
                .secret(new BCryptPasswordEncoder().encode(BCrypt.gensalt())) // 客户端密钥
                .resourceIds("res1")  // 资源列表
                .authorizedGrantTypes("authorization_code","password"
                        ,"client_credentials","implicit","refresh_token")
                .scopes("all")  // 允许授权的范围
                .autoApprove(false)
                .redirectUris("https://www.baidu.com");  // 验证的回调地址
    }
     // 配置令牌的服务
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailService);  // 客户端信息服务
        service.setSupportRefreshToken(true);  // 是否刷新令牌
        service.setTokenStore(tokenStore);   // 令牌存储策略
        service.setAccessTokenValiditySeconds(7200);  // 令牌有效期是两小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌的默认有效期是3天
        return service;
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}
