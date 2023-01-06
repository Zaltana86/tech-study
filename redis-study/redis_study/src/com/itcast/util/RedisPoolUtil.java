package com.itcast.util;

/*
Author: Inklo
Time: 2022/4/6 23:31
Target:
redis的连接池工具类
*/

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class RedisPoolUtil {
    static JedisPool pool;
    static {
        String path = Objects.requireNonNull(RedisPoolUtil.class.getClassLoader().getResource("conf/redis.properties")).getPath();
        Properties property = new Properties();
        try {
            property.load(new FileReader(path));
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(property.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(property.getProperty("maxIdle")));
            config.setMaxWaitMillis(Long.parseLong(property.getProperty("maxWaitMillis")));
            pool = new JedisPool(config,property.getProperty("host"), Integer.parseInt(property.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 通过数据连接池来获取连接
    public static Jedis getJedis() {
        return pool.getResource();
    }
}
