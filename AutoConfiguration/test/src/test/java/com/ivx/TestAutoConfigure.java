package com.ivx;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/8/26 10:16
 */
@SpringBootTest
public class TestAutoConfigure {
    @Resource
    private AutoConfigureTest autoConfigureTest;
    @Test
    public void test() {
        System.out.println(autoConfigureTest);
    }
}
