package net.Server.Server;
import net.Server.handler.ChatClientHandler;
import net.Json.MemberJson;
import DB.api.ClassApi;
import DB.api.MessageApi;
import net.base.Csocket;
import net.Server.handler.ChatMessageHandler;
import net.Json.MessageJson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer extends CServer {
    private Map<String, ChatClientHandler> chatMap = new HashMap<>();
    private static ChatServer instance;

    public static ChatServer getInstance() {
        return instance;
    }

    public ChatServer(int port) throws IOException {
        super(port);
        instance = this;
    }

    @Override
    public void update() {
        while (true) {
            try {
                var handler = new ChatClientHandler(new Csocket(serverSocket.accept()), this, new ChatMessageHandler());
                handler.start();
                clients.add(handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void login(String userId,ChatClientHandler handler) {
        logout(userId);     // 确保用户注销
        chatMap.put(userId, handler);
    }



    public void sendMessage(MessageJson message) {
        if (message.type == 0) {
            sendMessageToClient(message.sender, message);
            sendMessageToClient(message.receiver, message);
        } else {
            MessageApi.addMessage(message);       // 将群组消息添加到数据库
             List<MemberJson> members = ClassApi.getMembers(message.receiver);
            for (var i : members) {
                sendMessageToClient(i.userId, message);
            }
        }
    }
    private void sendMessageToClient(String userId, MessageJson message) {
        // 如果用户在线
        if (chatMap.containsKey(userId)) {
            // 发送消息提醒
            var receiver = chatMap.get(userId);
            if (receiver == null) return;
            receiver.sendNewMessage(message);
        } else if (message.type == 0) {     // 普通消息，群组消息此时已经在数据库中.
            // 用户离线，将消息添加至数据库
            MessageApi.addMessage(message);
        }
    }
    public void logout(String userId) {chatMap.remove(userId); }
}
