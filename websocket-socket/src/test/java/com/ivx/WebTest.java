package com.ivx;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/22 15:39
 */

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // 开启mvc虚拟调用
public class WebTest {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testWeb(@Autowired MockMvc mvc) throws Exception {
        // MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users");
        // //执行请求
        // ResultActions action = mvc.perform(builder);
        // //匹配执行状态（是否预期值）
        // //定义执行状态匹配器
        // StatusResultMatchers status = MockMvcResultMatchers.status();
        // ContentResultMatchers content = MockMvcResultMatchers.content();
        // ResultMatcher skyler = content.json(new ObjectMapper().writeValueAsString(new User("skyler", "123.com")));
        // action.andExpect(skyler);
        // //定义预期执行状态
        // ResultMatcher ok = status.isOk();
        // //使用本次真实值与预期结果对比
        // action.andExpect(ok);
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
}
