package com.ivx.strategy;


import com.ivx.interfacetest.Parent;
import org.junit.Test;

import java.util.ServiceLoader;
import java.util.regex.Pattern;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/11/3 9:30
 */

public class InterfaceImplTest {
    @Test
    public void testListImpl() {
        ServiceLoader<Parent> serviceLoader = ServiceLoader.load(Parent.class);
        for (Parent next : serviceLoader) {
            next.print();
        }
    }

}
