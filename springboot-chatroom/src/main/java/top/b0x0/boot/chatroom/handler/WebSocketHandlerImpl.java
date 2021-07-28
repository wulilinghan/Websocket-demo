package top.b0x0.boot.chatroom.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author vimmone 消息处理中心
 */
@Service
public class WebSocketHandlerImpl implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandlerImpl.class);

    @Autowired
    private UserService userService;

    /**
     * 同原生注解里的 @OnOpen 功能
     *
     * @param session /
     * @throws Exception /
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("链接成功......");
        userService.addUser(session);
    }

    /**
     * 同原生注解里的  @OnMessage 功能
     *
     * @param webSocketSession /
     * @param webSocketMessage /
     * @throws Exception /
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
            throws Exception {
        userService.handleMessage(webSocketSession, webSocketMessage);

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        logger.debug("链接出错，关闭链接......");
        userService.error(webSocketSession);

    }

    /**
     * 同原生注解里的 @OnClose 功能
     *
     * @param webSocketSession /
     * @param closeStatus      /
     * @throws Exception /
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("链接关闭......" + closeStatus.toString());
        userService.error(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}