package net.Client;

import GUI.Login_Frame;

import java.awt.*;
import java.io.IOException;

public class StartClient {
    public static void main(String[] args) {
        try {
            new Post();
            new ChatClient();
            new FileClient();
            EventQueue.invokeLater(Login_Frame::new);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
