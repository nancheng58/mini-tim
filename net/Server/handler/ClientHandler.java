package net.Server.handler;

import net.Server.Server.CServer;
import net.base.Csocket;

public abstract class ClientHandler {
    protected CServer server;
    protected Csocket client;
    protected MessageHandler messageHandler = null;

    public ClientHandler(Csocket socket, CServer server) {
        this.client = socket;
        this.server = server;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void start() {
        update();
    }

    /**
     * 更新处理
     */
    public abstract void update();
}