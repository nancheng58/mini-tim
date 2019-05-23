package bean;

import javax.swing.text.html.HTMLEditorKit;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 全局变量类
 */
public class Constant {

    public static final int CHATPROT=8848;
    public static final int FILEPROT=8849;
    public static final int INFOPROT=8850;
    public static final String HOST = "localhost";
    public static final int LOGIN=0;
    public static final int REGISTER=1;
    public static final int USERINFO=2;
    public static final int CLASSINFO = 3;     // 班级信息
    public static final int CHATMSG = 4;       // 获取用户聊天内容
    public static final int FILEINFO = 5;      // 获取文件信息
    public static final int VOTEINFO = 6;      // 获取投票信息
    public static final int NOTICEINFO = 7;    // 获取公告信息
    public static final int VOTE = 8;       // 进行投票操作
    public static final int MYCLASS = 9;       // 我的班级
    public static final int MYCHAT = 10;       // 我的好友
    public static final int MEMBERS = 11;       // 获取成员列表
    public static final int CREATECLASS = 12;  // 创建班级
    public static final int ADDCLASS = 13;     // 添加班级
    public static final int SETADMIN = 14;     // 设置管理员
    public static final int ADDNOTICE = 15;     // 发布公告
    public static final int UPDATENOTICE = 16;     // 修改公告内容，用于投票的那个确认文稿
    public static final int NOTICELIST = 17;    // 获取公告信息
    public static final int ADDVOTE = 18;      // 发布投票
    public static final int VOTELIST = 19;     // 获取投票列表
    public static final int SETVOTE = 20;       // 开始投票
    public static final int ADDADVICE = 21;        // 添加建议
    public static final int ADVICELIST = 22;       // 建议列表
    public static final int FILELIST = 23;     // 文件列表
    public static final int ADDFILE = 24;      // 添加文件
    public static final int REFRESHROCORD = 25;      // 添加文件

    /**
     * 缓存文件夹路径
     * @return
     */
    private static String getCachePath() {
        return "E:\\keshe\\src\\util\\resource";
    }

    /**
     * 将文件图片缓存至此文件夹
     * @return
     */
    public static String getFileCachePath() {
        return getCachePath() + "\\cache";
    }

    public static String getFileCachePath(String fileId) {
        return getFileCachePath() + "\\" + fileId;
    }

    /**
     * 得到聊天记录保存文件夹
     * @return
     */
    public static String getRecordPath() {
        return getCachePath() + "\\record";
    }

    /**
     * 得到聊天记录文件路径
     * @param id 用户id或者班级id
     * @return
     */
    public static String getRecordPath(String id) {
        return getRecordPath() + "\\" + id;
    }
    public static String getLogPath()
    {
        return getServerFileSavePath();
    }
    public static String getRecordPath(String userid, String sender) {
        return getRecordPath(userid) + "\\" + sender;
    }

    public static String getServerFileSavePath() {//服务器文件夹
        var cal = Calendar.getInstance();
        var year = cal.get(Calendar.YEAR);
        var month = cal.get(Calendar.MONTH);
        var date = cal.get(Calendar.DATE);
        return "E:\\yjykeshe";
    }
    public static String getFileReceivePath() {
        return getCachePath() + "\\files";
    }

    public static String getImageReceivePath() {
        return getCachePath() + "\\image";
    }
    public static String getFaceReceivePath() {
        return getImageReceivePath() + "\\face";
    }
    public static String getScreenShotPath(){ return getImageReceivePath()+"\\ScreenShot";}
    public static String getFilesize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }


}
