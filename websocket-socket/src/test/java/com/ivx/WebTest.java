package com.ivx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivx.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/22 15:39
 */

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // 开启mvc虚拟调用
public class WebTest {
    @Test
    public void testWeb(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users");
        //执行请求
        ResultActions action = mvc.perform(builder);
        //匹配执行状态（是否预期值）
        //定义执行状态匹配器
        StatusResultMatchers status = MockMvcResultMatchers.status();
        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher skyler = content.json(new ObjectMapper().writeValueAsString(new User("skyler", "123.com")));
        action.andExpect(skyler);
        //定义预期执行状态
        ResultMatcher ok = status.isOk();
        //使用本次真实值与预期结果对比
        action.andExpect(ok);
    }

    @Test
    public void testWebSocket() throws Exception {
        // WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        // List<Session> sessionList = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        //     String uri = "ws://localhost:8080/websocket/" + i;
        //     Session session = webSocketContainer.connectToServer(WebSocketClient.class, URI.create(uri));
        //     session.getBasicRemote().sendText("你好");
        //     sessionList.add(session);
        // }
        // Thread.sleep(10000);
        // sessionList.forEach(session -> {
        //     try {
        //         session.close();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // });
        System.out.println("helloworld================");
    }

    @Test
    public void testHttpClient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/users/hello");
        CloseableHttpResponse response = null;
        try {
            // 3. 执行GET请求
            response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            // 4. 获取响应实体
            HttpEntity entity = response.getEntity();
            // 5. 处理响应实体
            if (entity != null) {
                System.out.println("长度：" + entity.getContentLength());
                System.out.println("内容：" + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Value("${test.a}")
    private String a;
    @Test
    public void test1() {
        System.out.println(a);
    }
}
