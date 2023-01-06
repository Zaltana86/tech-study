package com.ivx.test;


import cn.hutool.http.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote request 配置类
 * @since 2022/9/1 15:20
 */

public class CrawlConfig {
    public static void main(String[] args)  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000) // 从连接池获取连接的最长时间
                .setConnectTimeout(1000)           // 创建连接的最长时间
                .setSocketTimeout(10 * 1000)       // 数据传输的最长时间
                .build();
        HttpGet httpGet = new HttpGet("https://www.galaxy7.top");
        httpGet.setConfig(requestConfig);
        // 获取响应
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            // 如果状态码是200
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.HTTP_OK) {
                // 获取响应体
                HttpEntity entity = httpResponse.getEntity();
                String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(content + "==============");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
