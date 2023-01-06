package com.ivx;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/8/4 11:06
 * Target:
 */
@Slf4j(topic = "sys-user")  // 设置日志名
@SpringBootTest
public class SecurityTest {
    @Resource
    private WebApplicationContext webApplicationContext;
    @Test
    public void test() {
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            // assert patternsCondition != null;
            // System.out.println(patternsCondition.getPatterns());
            System.out.println(patternsCondition);
        }
    }

    @Test
    public void testBcrypt() {
        String gensalt = BCrypt.gensalt();
        System.out.println(gensalt);
        String encrypt1 = BCrypt.hashpw("123.com", gensalt);// 生成随机的盐，但是都能匹配
        String encrypt2 = BCrypt.hashpw("123.com", gensalt);
        System.out.println(encrypt1);
        //$2a$10$dew9aDvdGbK01xsIGmBwG.pcYfekhuP/NQFyLsLveHiAmArPXlr4q
        System.out.println(encrypt2);
        System.out.println(BCrypt.checkpw("123.com", encrypt1));
        System.out.println(BCrypt.checkpw("123.com", encrypt2));
        // String encode = new BCryptPasswordEncoder().encode("123.com");
        // System.out.println(BCrypt.checkpw("12709160", "$2a$10$pUk7IbZqMIdiOUQpfN6C8.6zwnwgCUOKtpRDNbTliuiDriCgx1Oj."));
    }

    @Test
    public void testLog() {
        log.debug("你好============================");
        log.info("你好============================");
        log.warn("hello============================");
        log.error("hello============================");
    }

    @Test
    public void testClone() {
        CloneTest cloneTest = new CloneTest();
        cloneTest.setName("张三");
        cloneTest.setAge(18);
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        cloneTest.setList(objects);
        CloneTest cloneTest1 = cloneTest.clone();
        cloneTest1.setName("lis");
        cloneTest1.getList().add(2);

        System.out.println(cloneTest);
        System.out.println(cloneTest1);


    }
    @Data
    private static class CloneTest implements Cloneable {
        private String name;
        private int age;
        private List<Integer> list = new ArrayList<>();
        @Override
        public CloneTest clone() {
            try {
                return (CloneTest) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
