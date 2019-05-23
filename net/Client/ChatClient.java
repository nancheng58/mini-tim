package net.Client;

import bean.Constant;
import net.base.Csocket;
import net.Json.MessageJson;
import net.base.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
        private static ChatClient instance;
        private Csocket socket;
        private int token = -23333;
        private List<ChatListener>msgListener=new ArrayList<>();
        public void addListener(ChatListener listener){ msgListener.add(listener);}
        public ChatClient() throws IOException {
            instance = this;
            socket = new Csocket(new Socket(Constant.HOST, Constant.CHATPROT));
            readThread.start();
        }

        public static ChatClient getInstance() {
            return instance;
        }

        private Thread readThread = new Thread(()-> {
            try {
                while (true) {
                    var msg = MessageJson.parser(Message.parse(socket.readUTF()).props);
                    for (var i : msgListener) {
                        i.ReceiveMessage(msg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        public void login(int token)
        {
            this.token=token;
            try {
                socket.writeUTF(new Message(2,token,"").toString());
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        public void sendMessage(MessageJson msg) {
            try {

                socket.writeUTF(new Message(1,0,msg.toString()).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
