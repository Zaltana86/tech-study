package redistest;

/*
Author: Inklo
Time: 2022/4/6 22:08
Target:
*/

import com.itcast.util.RedisPoolUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {
    // string
    @Test
    public void test1() {
        // 获取连接对象
        Jedis jedis = new Jedis("localhost", 6379);  // 使用默认的主机和端口可以不写
        // 数据操作
        jedis.set("username", "zs");
        // 设置过期时间
        jedis.setex("password", 10, "123");
        // 需要关闭连接32
        jedis.close();
    }

    // hash
    @Test
    public void test2() {
        // 获取连接对象
        Jedis jedis = new Jedis("localhost", 6379);
        // 数据操作
        jedis.hset("user", "username", "zs");
        jedis.hset("user", "password", "123");
        // 获取hash单个值
        String username = jedis.hget("user", "username");
        System.out.println(username);
        // 获取所有值
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            System.out.println(key + ":" + user.get(key));
        }
        // 需要关闭连接
        jedis.close();
    }

    // list
    @Test
    public void test3() {
        // 获取连接对象
        Jedis jedis = new Jedis("localhost", 6379);
        // 数据操作
        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", "a", "b", "c");
        // cbaabc
        String popLeft = jedis.lpop("mylist");
        String popRight = jedis.rpop("mylist");
        System.out.println(popLeft);
        System.out.println(popRight);
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
        // 需要关闭连接
        jedis.close();
    }

    // set
    @Test
    public void test4() {
        // 获取连接对象
        Jedis jedis = new Jedis("localhost", 6379);
        // 数据操作
        // jedis.sadd("myset", "a", "b", "c");
        Set<String> myset = jedis.smembers("myset");
        // 判断集合的某个值是否存在
        Boolean exist = jedis.sismember("myset", "a");
        System.out.println(exist);
        System.out.println(myset);
        // 返回影响的数量
        Long srem = jedis.srem("myset", "a");
        System.out.println(srem);
        // 需要关闭连接
        jedis.close();
    }

    // sortedset
    @Test
    public void test5() {
        // 获取连接对象
        Jedis jedis = new Jedis("localhost", 6379);
        // 数据操作
        jedis.zadd("mysort", 1, "zs"); // 分越低排名越前
        jedis.zadd("mysort", 5, "ls");
        jedis.zadd("mysort", 3, "ww");
        Set<String> mysort = jedis.zrange("mysort", 0, -1);
        System.out.println(mysort);
        // 需要关闭连接
        jedis.close();
    }

    // 使用jedis连接池
    @Test
    public void test6() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置配置
        config.setMaxTotal(50); // 设置最大连接数量
        config.setMaxWaitMillis(3000); // 设置最大等待时间
        // 获取连接池对象
        // JedisPool pool = new JedisPool(); // 可以不指定参数
        JedisPool pool = new JedisPool(config, "localhost", 6379);
        Jedis jedis = pool.getResource();
        // 数据操作
        jedis.set("username", "INKLO");
        System.out.println(jedis.get("username"));
        // 需要归还连接至连接池
        jedis.close();
    }

    // 使用jedis的连接池工具类
    @Test
    public void test7() {
        Jedis jedis = RedisPoolUtil.getJedis();
        // 数据操作
        jedis.set("username", "zs");
        System.out.println(jedis.get("username"));
        // 需要归还连接至连接池
        jedis.close();

    }

}
