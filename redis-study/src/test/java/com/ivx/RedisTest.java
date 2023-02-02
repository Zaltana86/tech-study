package com.ivx;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2023/1/17 10:36
 */
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testOps() {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set("name", "skyler");
        Object name = stringObjectValueOperations.get("name");
        System.out.println("name:" + name);
        // 可以通过redisTemplate自动序列化和反序列化，
        // 也可以StringRedisTemplate来存储String存之前要对值进行序列化和取进行反序列化
        stringObjectValueOperations.set("user:1", new Person("skyler", 19));
        Person person = (Person) stringObjectValueOperations.get("user:1");
        System.out.println(person);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class Person {
        private String name;
        private Integer age;
    }

}
