package top.b0x0.boot.chatroom.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Vimmone 处理用户与消息模块
 */
@Component
public class UserService {
    private static final List<WebSocketSession> IN_MEMORY_USERS = new CopyOnWriteArrayList<WebSocketSession>();
    private static final List<TextMessage> IN_MEMORY_TEXT_MESSAGE = new CopyOnWriteArrayList<TextMessage>();
    private final Map<WebSocketSession, String> sessionNameMap = new ConcurrentHashMap<WebSocketSession, String>();

    public void addUser(WebSocketSession session) throws IOException {
        IN_MEMORY_USERS.add(session);
        for (TextMessage message : IN_MEMORY_TEXT_MESSAGE) {
            // 新用户登陆，发送全部历史信息给该用户
            session.sendMessage(message);
        }
        for (WebSocketSession onlineSession : sessionNameMap.keySet()) {
            // 用户刚上线，发送全部在线用户名字，让前端维护
            session.sendMessage(new TextMessage("a2" + sessionNameMap.get(onlineSession)));
        }
    }

    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
            throws IOException {
        // TODO Auto-generated method stub
        String message = webSocketMessage.getPayload() + "";
        // 自定义密钥，判断请求内容
        String request = message.substring(0, 1);
        // 链接成功获得在线用户的名字
        if ("a".equals(request)) {
            // 获得名字
            String name = message.substring(1);
            // 发送给前端维护在线人数列表
            this.sendMessageToUsers(new TextMessage("a2" + name));
            String begin = name.length() + name + "大家好，我是" + name + ",请多多关照";
            // 加入历史信息记录
            IN_MEMORY_TEXT_MESSAGE.add(new TextMessage(begin));
            // 开场白
            this.sendMessageToUsers(new TextMessage(begin));
            sessionNameMap.put(webSocketSession, name);
            return;// 链接后获得开头语后结束
        }
        IN_MEMORY_TEXT_MESSAGE.add(new TextMessage(message));
        this.sendMessageToUsers(new TextMessage(message));
    }

    public void sendMessageToUsers(TextMessage message) throws IOException {
        // 发送消息给全部在线用户
        for (WebSocketSession user : IN_MEMORY_USERS) {
            // 判断是否异常链接
            if (user.isOpen()) {
                user.sendMessage(message);
            }
        }
    }


    /**
     * 用户异常处理
     *
     * @param session /
     * @throws IOException /
     */
    public void error(WebSocketSession session) throws IOException {
        // 先移除队列
        IN_MEMORY_USERS.remove(session);
        // 获得异常用户的名字
        String name = sessionNameMap.get(session);
        // 发送给前端维护在线人数列表
        this.sendMessageToUsers(new TextMessage("a1" + name));
        // 结束语句
        String end = name.length() + name + "我先下线了，大家再见！";
        // 加入历史信息记录
        IN_MEMORY_TEXT_MESSAGE.add(new TextMessage(end));
        // 结束语
        this.sendMessageToUsers(new TextMessage(end));
        // 删除该用户
        sessionNameMap.remove(session);
    }
}
