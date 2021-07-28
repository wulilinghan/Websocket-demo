package top.b0x0.boot.chatroom.config;

import top.b0x0.boot.chatroom.handler.WebSocketHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * EnableWebSocket 开启websocket
 *
 * @author Vimmone 分配链接，注入hander
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandlerImpl webSocketHandlerImpl;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandlerImpl, "/ChatRoom");
    }
}