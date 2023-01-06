package com.ivx.test;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/10/12 15:40
 */
public class t {
    @Test
    public void test(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        Integer reduce = integers.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
    }
}
