package net.Client;

import net.Json.MessageJson;

public interface ChatListener {
    void ReceiveMessage(MessageJson message);
}
