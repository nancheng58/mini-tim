package net.Server.handler;

import bean.Constant;
import net.Client.FileClient;
import net.Json.*;
import DB.api.ClassApi;
import DB.api.MessageApi;
import DB.api.UserApi;
import net.base.Message;
import net.Json.Parser;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;


/**
 * @description: 处理信息获取
 **/
public class InfoMessageHandler extends MessageHandler{

    private int token = Integer.MIN_VALUE;

    public void logout() {
        UserApi.logout(token);
    }
    @Override
    public Message handleMessage(Message message) {
        Message result = null;

        switch (message.code) {
            // 登录
            case Constant.LOGIN:
                result = UserApi.login(UserJson.parse(message.props));
                token = result.result;
//                System.out.println("接入登陆api");
                break;
            // 注册
            case Constant.REGISTER:
                result = UserApi.register(UserJson.parse(message.props));
                break;
            // 获取用户信息
            case Constant.USERINFO:
                var user = UserApi.getUserInfo(UserJson.parse(message.props).username);
                result = new Message(0, 0, user == null ? "" : user.toString());
                break;
            // 获取班级信息
            case Constant.CLASSINFO:
                result = ClassApi.getClassInfo(ClassJson.parse(message.props).classid);
                break;
            // 获取用户聊天内容
            case Constant.CHATMSG:
                result = MessageApi.getMessage(token, MessageJson.parser(message.props));
                break;
            case Constant.MYCLASS:
                result = ClassApi.getMyClass(token);
                break;
            case Constant.MYCHAT:
                result = MessageApi.getNewChat(token);
                break;
            case Constant.MEMBERS:
                result = new Message(0, 0, Parser.toJson(ClassApi.getMembers(ClassJson.parse(message.props).classid)));
                break;
            case Constant.ADDCLASS:
                result = ClassApi.addClass(message.result, ClassJson.parse(message.props));
                break;
            case Constant.CREATECLASS:
                result = ClassApi.createClass(message.result, ClassJson.parse(message.props));
                break;
            case Constant.SETADMIN:
                var member = MemberJson.parse(message.props);
                result = ClassApi.updateMember(token, member.userId, member.classId, member.type);
                break;
            case Constant.NOTICELIST:
                result = ClassApi.getNoticeList(ClassJson.parse(message.props));
                break;
            case Constant.ADDNOTICE:
                result = ClassApi.addNotice(NoticeJson.parse(message.props));
                break;
            case Constant.UPDATENOTICE:
                result = ClassApi.updateNotice(NoticeJson.parse(message.props));
                break;
            case Constant.NOTICEINFO:
                result = ClassApi.getNotice(NoticeJson.parse(message.props));
                break;
            case Constant.ADDVOTE:
                result = ClassApi.addVote(NoticeJson.parse(message.props));
                break;
            case Constant.VOTELIST:
                result = ClassApi.getVoteList(ClassJson.parse(message.props));
                break;
            case Constant.ADVICELIST:
                result = ClassApi.getAdviceList(VoteJson.parse(message.props));
                break;
            case Constant.SETVOTE:
                result = ClassApi.setVoteStatus(VoteJson.parse(message.props));
                break;
            case Constant.VOTE:
                result = ClassApi.vote(VoteJson.parse(message.props));
                break;
            case Constant.ADDADVICE:
                result = ClassApi.addAdvice(NoticeJson.parse(message.props));
                break;
            case Constant.FILELIST:
                result = ClassApi.getFileList(ClassJson.parse(message.props));
                break;
            case Constant.ADDFILE:
                result = ClassApi.addFile(FileJson.parse(message.props));
            case Constant.REFRESHROCORD:
                result = UserApi.updaterecord(UserJson.parse(message.props));
                break;
        }
        savelog(result);
        return result;
    }
    private void savelog(Message result) {
        try {
            FileClient.createDirs(Constant.getLogPath());
            var path = Constant.getLogPath()+"\\"+"log.log";
            var file = new File(path);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("日志文件创建失败");
                }
            }
            var fout = new FileOutputStream(file, true);
            var writer = new PrintWriter(fout);
            writer.println(result.toString());
            writer.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}