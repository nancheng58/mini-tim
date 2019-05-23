package net.Server.handler;

import net.Server.Server.CServer;
import net.base.Csocket;
import net.base.Message;

/**
 * @description:
 **/
public class FileClientHandler extends ClientHandler {
    public FileClientHandler(Csocket socket, CServer server, FileMessageHandler handler) {
        super(socket, server);
        this.messageHandler = handler;
    }

    @Override
    public void update() {
        new Thread(()-> {
            try {
                var message = Message.parse(client.readUTF());
                ((FileMessageHandler)messageHandler).setSocket(client);
                var result = messageHandler.handleMessage(message);
                if (result != null) client.writeUTF(result.toString());
            } catch (Exception e) {
                e.printStackTrace();
                server.remove(this);
            }
        }).start();
    }
}
