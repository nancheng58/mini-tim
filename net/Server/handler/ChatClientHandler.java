package net.Server.handler;
import net.base.Csocket;
import net.Server.Server.CServer;
import net.Server.handler.ChatMessageHandler;
import net.Server.Server.ChatServer;
import net.Server.handler.ClientHandler;
import net.base.Message;
import net.Json.MessageJson;
/**
 * @description：聊天客户端处理器
 */
import java.io.IOException;

public class ChatClientHandler extends ClientHandler {
    private String username = "-1";

    public String getUsername() {
        return username;
    }

    public ChatClientHandler(Csocket socket, CServer server, ChatMessageHandler handler) {
        super(socket, server);
        this.messageHandler = handler;
        ((ChatMessageHandler) this.messageHandler).setClientHandler(this);
    }

    @Override
    public void update() {
        new Thread(() -> {
            try {
                while (true) {
                    Message message = Message.parse(client.readUTF());
                    System.out.println("接收到客户端发送来的消息");
                    messageHandler.handleMessage(message);
                }
            } catch (Exception e) {

                ((ChatServer) server).logout(username);
                server.remove(this);
            }
        }).start();
    }

    public void sendMessageToServer(MessageJson json) {
        ((ChatServer) server).sendMessage(json);
    }

    public void sendNewMessage(MessageJson message) {
        try {
            client.writeUTF(new Message(0, 0, message.toString()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserId(String props) {
        username = props;
        ((ChatServer) server).login(username, this);
    }
    }