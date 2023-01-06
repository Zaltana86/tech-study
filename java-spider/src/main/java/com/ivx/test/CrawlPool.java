package com.ivx.test;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote http连接池的使用
 * @since 2022/9/1 15:02
 */

public class CrawlPool {
    public static void main(String[] args) {
        // 连接管理器
        PoolingHttpClientConnectionManager phcm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        phcm.setMaxTotal(100);
        // 设置每个主机的最大连接数
        phcm.setDefaultMaxPerRoute(10);

        doGet(phcm);
        doGet(phcm);
    }

    private static void doGet(PoolingHttpClientConnectionManager phcm) {
        // 通过连接池获取客户端对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(phcm).build();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 需要归还到连接池，所以不需要关闭客户端对象
        }
    }
}
