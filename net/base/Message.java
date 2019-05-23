package net.base;

import net.Json.Parser;

public class Message {
    public int code; //识别massage类型
    public int result;//结果
    public String props;
    public Message() // 空
    {
        this(0,-100,"");
    }
    public Message(int code,String props)
    {
        this.code=code;
        this.props=props;
    }
    public Message(int code,int result,String props)
    {
        this.code=code;
        this.props=props;
        this.result=result;
    }
    public String toString() {
        return Parser.toJson(this);
    }
    public static Message parse(String json)
    {
        return Parser.fromJson(json,Message.class);
    }
}
