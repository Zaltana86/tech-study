package com.ivx;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/11/10 11:03
 */
public class LocalCache {
    //创建guava cache
    public static final Cache<String, String> loadingCache = Caffeine.newBuilder()
            //cache的初始容量
            .initialCapacity(5)
            //cache最大缓存数
            .maximumSize(10)
            //设置写缓存后n秒钟过期
            .expireAfterWrite(10, TimeUnit.SECONDS)
            //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
            //.expireAfterAccess(17, TimeUnit.SECONDS)
            .build();



    public static String getValueFromDB(String s) {
        return null;
    }
}
