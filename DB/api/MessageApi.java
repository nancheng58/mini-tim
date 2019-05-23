package DB.api;

import DB.DBConnection;
import net.Json.MessageJson;
import net.Json.UserJson;
import net.Server.Server.ServerManager;
import net.base.Message;
import net.Json.Parser;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class MessageApi {
    public static Message getNewChat(int token) {
        try {
            var username = ServerManager.getUsername(token);
            var sql = "SELECT sender FROM message WHERE receiver=? GROUP BY sender;";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, username);
            var rs = ps.executeQuery();
            List<UserJson> list = new ArrayList<>();
            while (rs.next()) {
                var u = UserApi.getUserInfo(rs.getString("sender"));
                if (u != null)
                    list.add(u);
            }
            return new Message(0, 0, Parser.toJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean removeMessage(int msgid) {
        try {
            var sql = "DELETE FROM message WHERE msgid = ?;";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, msgid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static List<MessageJson> getNormalMessage(MessageJson messageJson) {
        try {
            var sql = "SELECT * FROM message WHERE type=0 AND receiver=? AND sender=? ORDER BY time;";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, messageJson.receiver);
            ps.setString(2, messageJson.sender);
            var rs = ps.executeQuery();
            List<MessageJson> list = new ArrayList<>();
            while (rs.next()) {
                var msg = new MessageJson();
                msg.id = rs.getInt("msgid");
                msg.receiver = rs.getString("receiver");
                msg.sender = rs.getString("sender");
                msg.text = rs.getString("text");
                msg.time = rs.getTimestamp("time").getTime();
                msg.type = rs.getInt("type");
                list.add(msg);
                // 阅读过消息后就移除这条消息
                removeMessage(msg.id);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // 得到群组消息
    private static List<MessageJson> getClassMessage(int token, MessageJson messageJson) {
        try {
            var sql = "SELECT * FROM message WHERE type=1 AND receiver=? AND time > ? ORDER BY time;";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, messageJson.receiver);
            ps.setTimestamp(2, ClassApi.getLastVisitedTime(token, messageJson.receiver));

            ClassApi.setLastVisitedTime(token, messageJson.receiver, new Timestamp(Calendar.getInstance().getTimeInMillis()));

            var rs = ps.executeQuery();
            List<MessageJson> list = new ArrayList<>();
            while (rs.next()) {
                var msg = new MessageJson();
                msg.id = rs.getInt("msgid");
                msg.receiver = rs.getString("receiver");
                msg.sender = rs.getString("sender");
                msg.text = rs.getString("text");
                msg.time = rs.getTimestamp("time").getTime();
                msg.type = rs.getInt("type");
                list.add(msg);
                // 群组消息不清数据库
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Message getMessage(int token, MessageJson messageJson) {
        try {
            //if (!(username.equals(messageJson.sender) || username.equals(messageJson.receiver))) return null;
            if (messageJson.type == 0) {
                return new Message(0, 0, Parser.toJson(getNormalMessage(messageJson)));
            } else {
                return new Message(0, 0, Parser.toJson(getClassMessage(token, messageJson)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMessage(MessageJson json) {
        try {

            var sql = "INSERT INTO message (sender, receiver, text, time, type) VALUES (?,?,?,?,?);";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, json.sender);
            ps.setString(2, json.receiver);
            ps.setString(3, json.text);
            ps.setTimestamp(4, new Timestamp(json.time));
            ps.setInt(5, json.type);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
