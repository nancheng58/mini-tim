package net.Server.Server;
import net.Server.handler.FileClientHandler;
import net.Server.handler.FileMessageHandler;
import net.base.Csocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileServer extends CServer{
    private Map<String, FileClientHandler> fileMap=new HashMap<>();

    public FileServer (int port) throws IOException {
        super(port);

    }

    @Override
    public void update() {
        try {
            while (true)
            {
                var handle=new FileClientHandler(new Csocket(serverSocket.accept()),this,new FileMessageHandler());
                clients.add(handle);
                handle.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
