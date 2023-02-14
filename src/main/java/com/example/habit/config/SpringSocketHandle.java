package com.example.habit.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class SpringSocketHandle extends AbstractWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("SpringSocketHandle, 收到新的连接: " + session.getId());
    }

//    @Override

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        String msg = "SpringSocketHandle, 连接：" + session.getId() +  "，已收到消息。";
//        System.out.println(msg);
//        session.sendMessage(new TextMessage(msg));
//    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("WS 连接发生错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("WS 关闭连接");
    }

    // 支持分片消息
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
