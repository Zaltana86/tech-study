package com.ivx.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote java 第一个爬虫程序
 * @since 2022/9/1 9:59
 */
@Slf4j
@SuppressWarnings("")
public class CrawlFirst {
    public static void main(String[] args) throws Exception {
        // 客户端对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 发起get请求
        // HttpGet httpGet = new HttpGet("https://www.galaxy7.top");
        //** 设置请求参数 get参数
        URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com");
        uriBuilder.setParameter("userName", "张三").build();
        /// HttpGet httpGet = new HttpGet(uriBuilder.build());
        HttpPost httpPost = new HttpPost(uriBuilder.build());

        // post 里面带表单参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("key","lisi"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params,StandardCharsets.UTF_8);
        httpPost.setEntity(urlEncodedFormEntity);
        // 获取响应
        CloseableHttpResponse httpResponse = null;
        try {
            /// httpResponse = httpClient.execute(httpGet);
            httpResponse = httpClient.execute(httpPost);
            // 如果状态码是200
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 获取响应体
                HttpEntity entity = httpResponse.getEntity();
                String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(content + "==============");
                /// log.error("hello");
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
