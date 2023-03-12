package com.itheima.security.distributed.uaa.config;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

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
        converter.setKeyPair(keyPair); //对称秘钥，资源服务器使用该秘钥来验证
        return converter;
    }

    // @Bean
    // public TokenStore tokenStore() {
    //     //使用内存存储令牌（普通令牌）
    //     return new InMemoryTokenStore();
    // }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String secret = "secret";
        KeyPair keyPair = SecureUtil.generateKeyPair("RSA", 1024, secret.getBytes(StandardCharsets.UTF_8));

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String verifierKey = "-----BEGIN PUBLIC KEY-----\n" + Base64Encoder.encode(publicKey.getEncoded())
                + "\n-----END PUBLIC KEY-----";
        System.out.println(verifierKey);
        // converter.setVerifierKey(verifierKey); //对称秘钥，资源服务器使用该秘钥来验证
        // 生成公钥并保存到文件
        // byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        // // 生成私钥并保存到文件
        // byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        // FileUtil.writeBytes(publicKeyBytes, "d:/test-file/a.pub");
        // FileUtil.writeBytes(privateKeyBytes, "d:/test-file/a.pri");





    }


    // public static String convertToOpenSSHFormat(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
    //     // Get the encoded PKCS#8 representation of the private key
    //     byte[] pkcs8EncodedKey = privateKey.getEncoded();
    //
    //     // Parse the PKCS#8 key into a PrivateKeyInfo object
    //     PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(pkcs8EncodedKey);
    //
    //     // Create a PKCS#1 representation of the private key
    //     PKCS8EncodedKeySpec pkcs1KeySpec = new PKCS8EncodedKeySpec(privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded());
    //
    //     // Generate the OpenSSH format key
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     try (PEMWriter sshWriter = new PEMWriter(new java.io.OutputStreamWriter(outputStream))) {
    //         PKCS1EncodedKeySpec pkcs1EncodedKeySpec ;
    //         sshWriter.writeObject(PKCS1EncodedKeySpec(pkcs1KeySpec.getEncoded()));
    //     }
    //     return new String(outputStream.toByteArray());
    // }
}
