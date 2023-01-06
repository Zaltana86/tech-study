package com.ivx.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/8/3 11:57
 * Target: websecurity配置
 */

// @EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     // 在内存中创建用户 有授予权限标识符
    //     manager.createUser(User.withUsername("张三").password("123.com").authorities("p1").build());
    //     manager.createUser(User.withUsername("李四").password("123.com").authorities("p2").build());
    //     return manager;
    // }

    /**
     * 定义密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Customizer<CsrfConfigurer<HttpSecurity>> customizer = csrfCustomizer -> {
            RequestMatcher requestMatcher = new IpAddressMatcher("127.0.0.1");
            csrfCustomizer.ignoringRequestMatchers(requestMatcher);  // 允许本地访问
        };
        http.csrf(customizer) // 跨域访问
                .authorizeRequests()
                // .antMatchers("/user/r/r1").hasAuthority("p1") // 基于方法的授权
                // .antMatchers("/user/r/r2").hasAuthority("p2")
                .antMatchers("/user/r/**").authenticated()  // r资源都需要认证
                .anyRequest().permitAll() // 除了r资源全部可以访问
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // 如果是分布式的可以设置为never
                .and().formLogin()  // 允许表单登陆访问
                .successForwardUrl("/user/login_success");   // 登陆成功后的页面地址
    }
}
