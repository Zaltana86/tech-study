package ivx;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/23 9:54
 */
public class CaffeineCacheTest {
    public static void main(String[] args) {
        //创建guava cache
        Cache<String, String> loadingCache = Caffeine.newBuilder()
                //cache的初始容量
                .initialCapacity(5)
                //cache最大缓存数
                .maximumSize(10)
                //设置写缓存后n秒钟过期
                .expireAfterWrite(17, TimeUnit.SECONDS)
                //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
                //.expireAfterAccess(17, TimeUnit.SECONDS)
                .build();
        String key = "key";
        // 往缓存写数据
        loadingCache.put(key, "value");

        // 获取value的值，如果key不存在，给一个默认的value
        String value = loadingCache.get("key", CaffeineCacheTest::getValueFromDB);
        System.out.println(value);
        // 删除key
        loadingCache.invalidate(key);
        System.out.println(getValueFromDB(null));
    }

    private static String getValueFromDB(String key) {
        return null;
    }
}
