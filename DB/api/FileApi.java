package DB.api;

import DB.DBConnection;
import bean.Constant;
import net.Json.FileJson;
import net.base.Message;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * @description: file API
 **/
public class FileApi {
    public static Message addFile(FileJson file) {
        try {
            var sql = "INSERT INTO file (name, path, owner,time,size) VALUES (?,?,?,?,?);";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, file.name);
            ps.setString(2, file.path);
            ps.setString(3, file.owner);
            ps.setTimestamp(4, new Timestamp(file.time));
            ps.setLong(5, file.length);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            var result = new FileJson();
            result.fileId = -1;
            if (rs.next()) {
                result.fileId = rs.getInt(1);
            }
            result.name = file.name;
            result.owner = file.owner;
            result.path = file.path;
            return new Message(0, 0, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Message getFile(FileJson fileJson) {
        try {
            var sql = "SELECT * FROM file WHERE fileid=?;";
            var ps = DBConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, fileJson.fileId);
            var rs = ps.executeQuery();
            var result = new FileJson();
            result.fileId = -1;
            result.path = Constant.getServerFileSavePath();
            if (rs.next()) {
                result.fileId = rs.getInt("fileid");
                result.owner = rs.getString("owner");
                result.name = rs.getString("name");
                result.path = rs.getString("path");
                result.time = rs.getTimestamp("time").getTime();
                result.length=rs.getLong("size");
            }
            return new Message(0, 0, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
