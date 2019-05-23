package net.Server.Server;

import net.base.Csocket;
import net.Server.handler.InfoClientHandler;
import net.Server.handler.InfoMessageHandler;

import java.io.IOException;

public class InfoServer extends CServer{


    public InfoServer(int port) throws IOException
    {
        super(port);
    }
    public void update() {
        while (true)
        {
            try {
                var handler=new InfoClientHandler(new Csocket(serverSocket.accept()),this,new InfoMessageHandler());
                handler.start();
                clients.add(handler);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
