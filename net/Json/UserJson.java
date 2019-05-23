package net.Json;

public class UserJson  extends Json{
    public String username;
    public String id;
    public int avatar;
    public int status;
    public String password;
    public int gamescore;
    public UserJson()
    {

    }
    public UserJson(String username)
    {
        this.username=username;
    }
    public UserJson(String username,String id)
    {
        this.username=username;
        this.id=id;
    }
    public String getPassword(){ return password;}
    public String getUsername()
    {
        return username;
    }
    public String getId(){return id;}
    public void setUsername(String username) {
        this.username = username;this.id=id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(String id)
    {
        this.id=id;this.username = username;
    }
    public static UserJson parse(String json)
    {
        return Parser.fromJson(json,UserJson.class);
    }

}
