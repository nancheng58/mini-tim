package net.Server.handler;

import net.Server.Server.ServerManager;
import net.base.Message;
import net.Json.MessageJson;

public class ChatMessageHandler extends MessageHandler {

    ChatClientHandler clientHandler;
    public void setClientHandler(ChatClientHandler clientHandler)
    {
        this.clientHandler=clientHandler;
    }
    public Message handleMessage(Message message)
    {
        switch (message.code)
        {
            case 1:
                System.out.println("消息处理器启动");
                var json= MessageJson.parser(message.props);
                clientHandler.sendMessageToServer(json);
                break;
            case 2:
                clientHandler.setUserId(ServerManager.getUsername(message.result));

        }
        //接受提示
        return null;
    }
}
