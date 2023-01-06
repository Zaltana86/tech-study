package com.ivx;


import cn.hutool.extra.spring.SpringUtil;
import com.ivx.event.MyApplicationEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/11/9 17:48
 */
@SpringBootTest
public class SpringEventTest {
    @Test
    public void testEvent() {
        MyApplicationEvent myApplicationEvent = new MyApplicationEvent(this, "zhangsan", 10);
        SpringUtil.publishEvent(myApplicationEvent);
    }
}
