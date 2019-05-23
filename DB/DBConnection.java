package DB;
import java.io.Serializable;
import java.sql.*;
public class DBConnection  implements Serializable {
    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    //数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/info?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "19991231";
    private static DBConnection instance=new DBConnection();
    public static DBConnection getInstance() {
        return instance;
    }

    private Statement state;
    private Connection connection = null;
    public DBConnection(){

        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            state=connection.createStatement();

            //mysql查询语句
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection == null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Connection getConnection()
    {
        return connection;
    }
    public Statement getStatement()
    {
        return state;
    }
    public ResultSet query(String sql) throws SQLException
    {
        return state.executeQuery(sql);
    }
    public int updata(String sql) throws SQLException
    {
        return state.executeUpdate(sql);
    }
    public void closeConnection(){//关闭数据库连接
        try{
            state.close();connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}