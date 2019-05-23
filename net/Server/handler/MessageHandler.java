package net.Server.handler;

import net.base.Message;

// 处理消息类
public abstract class MessageHandler {
    public abstract Message handleMessage(Message message);
}
