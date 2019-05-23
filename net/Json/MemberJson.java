package net.Json;

public class MemberJson extends Json{
    public String userId;
    public String classId;
    public int type;

    public static MemberJson parse(String json) {
        return Parser.fromJson(json, MemberJson.class);
    }
}
