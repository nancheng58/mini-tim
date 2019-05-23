package net.Server.Server;

import bean.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerManager {
    private static Map<Integer,String> loginer=new HashMap<>();
    public ServerManager() throws IOException
    {
        ChatServer chatServer=new ChatServer(Constant.CHATPROT);
        InfoServer infoServer=new InfoServer(Constant.INFOPROT);
        FileServer fileServer=new FileServer(Constant.FILEPROT);
        new Thread(chatServer::update).start();
        new Thread(infoServer::update).start();
        new Thread(fileServer::update).start();

    }
    public static boolean islogin(int key)
    {
        return loginer.containsKey(key);
    }
    public static String getUsername(int key)
    {
        return loginer.get(key);
    }
    public static int getToken(String name)
    {
        for(Integer key : loginer.keySet()) {
            String value = loginer.get(key);
            if (value.equals(name)) return key;
        }
        return -1;
    }
    public static void addLoginer(int token,String username)
    {
        loginer.put(token,username);
    }
    public static void removeLoginer(int key )
    {
        loginer.remove(key);
    }
}
