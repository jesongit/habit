package com.example.habit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SpringSocketConfig implements WebSocketConfigurer {

    // https://juejin.cn/post/7095940082187632677
    // https://www.jianshu.com/p/5d3386d88722
    @Autowired
    private SpringSocketHandle springSocketHandle;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(springSocketHandle, "/ws").setAllowedOrigins("*");
    }
}
