package com.ivx;


import javax.websocket.*;
import java.io.IOException;

@ClientEndpoint
public class WebSocketClient {
    private Session session;
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        // Map<String, Object> userProperties = session.getUserProperties();
        System.out.println("客户端连接成功！");
    }
    @OnMessage
    public void onMessage(String message) {
        System.out.println("客户端收到消息: " + message);
    }
    @OnClose
    public void onClose() {
        System.out.println("客户端连接关闭！");
    }
    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
