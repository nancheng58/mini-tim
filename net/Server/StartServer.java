package net.Server;

import DB.DBConnection;

import net.Server.Server.ServerManager;

import java.io.IOException;

public class StartServer {
    public static void main(String [] args)
    {
        try {
            DBConnection DB=new DBConnection();
            System.out.println("数据库连接成功");
            ServerManager sm=new ServerManager();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
