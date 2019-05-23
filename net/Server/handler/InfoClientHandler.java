package net.Server.handler;

import net.base.Csocket;
import net.Server.Server.CServer;
import net.base.Message;

/**
 * @description: 用于处理信息
 **/
public class InfoClientHandler extends ClientHandler {

    public InfoClientHandler(Csocket socket, CServer server, MessageHandler infoHandler) {
        super(socket, server);
        this.messageHandler = infoHandler;
    }

    @Override
    public void update() {
        new Thread(()-> {
            try {
                while (true) {
                    // 获得消息
                    var request = Message.parse(client.readUTF());
//                    System.out.println("收到消息");
                    // 获取执行结果
                    var result = messageHandler.handleMessage(request);;
                    // 将结果写入
                    if (result != null)
                    {
                        client.writeUTF(result.toString());
                    }
                    else client.writeUTF("");
                }
            } catch (Exception e) {
                e.printStackTrace();

                ((InfoMessageHandler)messageHandler).logout();
                server.remove(this);
            }
        }).start();
    }
}
