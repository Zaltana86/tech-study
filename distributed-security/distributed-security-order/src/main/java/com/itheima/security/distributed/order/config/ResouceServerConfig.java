package com.itheima.security.distributed.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {


    public static final String RESOURCE_ID = "res1";

    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)//资源 id
                // 通过jwt来验证，不通过远程验证
                .tokenStore(tokenStore)
                // .tokenServices(tokenService())//验证令牌的服务
                .stateless(true);
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // http
        //         .authorizeRequests()
        //         .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
        //         .and().csrf().disable()
        //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_API')")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**，其它的请求可以访问

        ;


    }

    // //资源服务令牌解析服务
    // @Bean
    // public ResourceServerTokenServices tokenService() {
    //     //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
    //     RemoteTokenServices service = new RemoteTokenServices();
    //     service.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
    //     service.setClientId("c1");
    //     service.setClientSecret("secret");
    //     return service;
    // }

}
