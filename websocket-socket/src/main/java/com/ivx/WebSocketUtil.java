package com.ivx;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;



import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote websocket的使用
 * @since 2022/9/19 11:31
 */
@Component
@ServerEndpoint("/websocket/{uid}")
@Slf4j
public class WebSocketUtil {
    private String uid;

    private Session session;

    public static final CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet<>();

    //连接时执行
    @OnOpen
    public void onOpen(@PathParam("uid") String uid, Session session) {
        this.session = session;
        this.uid = uid;
        webSocketSet.add(this);
        log.info("链接成功,用户id{}", uid);
    }

    //关闭时执行
    @OnClose
    public void onClose() {
        log.info("连接：{} 关闭", this.uid);
        webSocketSet.remove(this);
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message) {
        System.out.println("message:" + message);
    }


    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户id为：{}的连接发送错误", this.uid);
        error.printStackTrace();
    }

    /**
     * 发送消息
     */
    public boolean sendMessage(String message, String uid) throws IOException {
        boolean result = false;
        for (WebSocketUtil webSocketUtil : webSocketSet) {
            if (Objects.equals(webSocketUtil.uid, uid)) {
                webSocketUtil.session.getBasicRemote().sendText(message);
                result = true;
            }
        }
        return result;
    }
}
