package net.Json;

public class MessageJson extends Json{
    public String sender;
    public String receiver;
    public String text;
    public int id;
    public int type;
    public long time;
    public static MessageJson parser(String json)
    {
        return Parser.fromJson(json,MessageJson.class);
    }
}
