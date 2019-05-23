package DB.api;

import DB.DBConnection;
import net.Json.UserJson;
import net.Server.Server.ServerManager;
import net.base.Message;

import java.net.ServerSocket;
import java.sql.SQLException;

/**
 * 处理用户相关信息，负责与数据库的通信
 */
public class UserApi {
    private static boolean check(UserJson user) throws SQLException{
        var sql = "SELECT username FROM user WHERE username=? AND password=?";
        var ps= DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        var rs=ps.executeQuery();
        return rs.next();
    }
    public static Message login(UserJson user){
        try {
            if(check(user)){
                var checkToken=ServerManager.getToken(user.username);
                //防止重复登录
                if (checkToken!=-1&&getStatus(checkToken)==1) return new Message(-1, Integer.MIN_VALUE,"不能重复登陆");
                setUserStatus(user.getUsername(),1);
                var token=0;
                while (ServerManager.islogin(token))
                {
                    token=(int) (Math.random()*1e5);

                }
                ServerManager.addLoginer(token,user.getUsername());
                return new Message(0,token,"登陆成功");
            }
            else return new Message(-1, Integer.MIN_VALUE,"账号或密码错误");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    private static void setUserStatus(String username,int status)
    {
        try {
            var sql="UPDATE user SET status=?,lastonline=? WHERE username=?";
            var ps=DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,status);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            ps.setDate(2,java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3,username);
            ps.executeLargeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static Message updaterecord(UserJson user)
    {
        try {
            var sql="UPDATE user SET gamerecord=? WHERE username=?";
            var ps=DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,user.gamescore);
            ps.setString(2,user.username);
            ps.executeLargeUpdate();
            return new Message(0,0,"破纪录啦");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new Message(-1,0,"更新失败");
    }
    public static void logout(int token)
    {
        var username=ServerManager.getUsername(token);
        setUserStatus(username,0);
    }
    public static int getStatus(int token) throws SQLException
    {
        var username =ServerManager.getUsername(token);
        var sql = "SELECT status FROM user WHERE username=?";
        var ps=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setString(1,username);
        var rs=ps.executeQuery();
        if(rs.next()) return rs.getInt("status");// query status
        return 0;
    }
    public static UserJson getUserInfo(String username)
    {
        try {
            var updateStatus = "SELECT * FROM user WHERE username=?;";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(updateStatus);
            ps.setString(1, username);
            var rs = ps.executeQuery();
            if (rs.next()) {
                var user = new UserJson();
                user.username = username;
                user.password = "qwq";
                user.id=rs.getString("userid");
                user.avatar = rs.getInt("avatar");
                user.username = rs.getString("username");
                user.status = rs.getInt("status");
                user.gamescore = rs.getInt("gamerecord");
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Message register(UserJson user)
    {
        return new Message(0,0,"");
    }

}
