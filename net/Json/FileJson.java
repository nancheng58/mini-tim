package net.Json;

public class FileJson extends Json{
    public int fileId = -1;
    public String name = "";
    public String path = "";
    public String owner = "-1";
    public long length = 0;
    public String classId;
    public long time;

    public static FileJson parse(String json) {
        return Parser.fromJson(json, FileJson.class);
    }
}
