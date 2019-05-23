package net.Server.Server;

import net.Server.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public abstract class CServer {
    protected List<ClientHandler> clients;
    protected ServerSocket serverSocket;

    public CServer(int port) throws IOException {
        clients = new ArrayList<>();
        serverSocket = new ServerSocket(port);
    }

    /**
     * 服务更新
     */
    public abstract void update();

    /**
     * 从客户端列表中移除
     * @param des 移除的客户端
     */
    public void remove(Object des) {
        clients.remove(des);
    }

    public List<ClientHandler> getClients() {
        return clients;
    }
}
