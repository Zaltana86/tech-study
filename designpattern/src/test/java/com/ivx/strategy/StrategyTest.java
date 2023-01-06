package com.ivx.strategy;


import org.junit.Test;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/11/2 10:47
 * @apiNote
*/

public class StrategyTest {
    @Test
    public void testStrategy() {
        System.out.println(StrategyContextHolder.getStrategy(1).getCompanyName());
    }
}
