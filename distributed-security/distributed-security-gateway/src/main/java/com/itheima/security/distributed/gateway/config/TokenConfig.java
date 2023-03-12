package com.itheima.security.distributed.gateway.config;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration
public class TokenConfig {

    private String SIGNING_KEY = "uaa123";

    @Bean
    public TokenStore tokenStore() {
        //JWT令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String secret = "secret";
        KeyPair keyPair = SecureUtil.generateKeyPair("RSA", 1024, secret.getBytes(StandardCharsets.UTF_8));

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String verifierKey = "-----BEGIN PUBLIC KEY-----\n" + Base64Encoder.encode(publicKey.getEncoded())
                + "\n-----END PUBLIC KEY-----";
        // converter.setVerifierKey(verifierKey); //对称秘钥，资源服务器使用该秘钥来验证
        converter.setVerifierKey(verifierKey);
        return converter;
    }

   /* @Bean
    public TokenStore tokenStore() {
        //使用内存存储令牌（普通令牌）
        return new InMemoryTokenStore();
    }*/
}
