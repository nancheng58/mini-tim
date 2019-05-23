package net.Json;


public class ClassJson extends Json {
    public String classid;
    public int gradeIndex;
    public int classIndex;
    public int avatar = -1;
    public String intro;

    public static ClassJson parse(String json) {
        return Parser.fromJson(json, ClassJson.class);
    }

    public ClassJson() {}

    public ClassJson(String classid) {
        this.classid = classid;
    }
}
