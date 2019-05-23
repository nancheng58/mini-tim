package net.Client;
import bean.Constant;
import net.Json.*;
import com.google.gson.reflect.TypeToken;
import net.base.Csocket;
import net.base.Message;
import net.Json.Parser;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private int token = Integer.MAX_VALUE;
    private static Post instance;
    private Csocket socket;

    public static Post getInstance() {
        return instance;
    }


    public Post() throws IOException {
        instance = this;
        socket = new Csocket(new Socket(Constant.HOST,Constant.INFOPROT));
    }

    private synchronized Message post(Message message) {
        try {
            socket.writeUTF(message.toString());
            return Message.parse(socket.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message(-1, 0, "空结果错误");
    }
    public synchronized Message login(UserJson user)
    {
        Message result=post(new Message(Constant.LOGIN,token,user.toString()));
        if(result.code==0) token=result.result;
        return result;
    }
    public synchronized UserJson getUserInfo(String username) {
        return getUserInfo(new UserJson(username));
    }

    public synchronized UserJson getUserInfo(UserJson user) {
        var r = post(new Message(Constant.USERINFO, token, user.toString()));
        return r.code < 0 ? null : UserJson.parse(r.props);
    }
    public synchronized Message register(UserJson user) {
        return post(new Message(Constant.REGISTER, token, user.toString()));
    }
    public synchronized Message refreshRecord(UserJson user)
    {
        return post(new Message(Constant.REFRESHROCORD,token,user.toString()));
    }

    public synchronized int getAvatarId(String username, boolean isClass) {
        return isClass ? getClassInfo(new ClassJson(username)).avatar : getUserInfo(new UserJson(username)).avatar;
    }

    public synchronized List<ClassJson> getMyClass() {
        var result = post(new Message(Constant.MYCLASS, token, ""));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<ClassJson>>() {}.getType());
    }

    public synchronized List<UserJson> getChatUser() {
        var result = post(new Message(Constant.MYCHAT, token, ""));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<UserJson>>() {}.getType());
    }

    public synchronized List<MessageJson> getMessage(MessageJson info) {
        var result = post(new Message(Constant.CHATMSG, token, info.toString()));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<MessageJson>>() {}.getType());
    }

    public synchronized ClassJson getClassInfo(ClassJson classJson) {
        var result = post(new Message(Constant.CLASSINFO, token, classJson.toString()));
        return result.code < 0 ? null : ClassJson.parse(result.props);
    }

    public synchronized List<MemberJson> getMembers(ClassJson classJson) {
        var result = post(new Message(Constant.MEMBERS, token, classJson.toString()));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<MemberJson>>() {}.getType());
    }

    /**
     * 创建班级
     * @param json 需要设置id，年级，班级
     * @return 结果
     */
    public synchronized Message createClass(ClassJson json) {
        return post(new Message(Constant.CREATECLASS, token, json.toString()));
    }

    /**
     * 加入班级
     * @param json 需要设置班级号
     * @return 结果
     */
    public synchronized Message addClass(ClassJson json) {
        return post(new Message(Constant.ADDCLASS, token, json.toString()));
    }

    public synchronized Message setAdmin(MemberJson member) {
        return post(new Message(Constant.SETADMIN, token, member.toString()));
    }

    public List<NoticeJson> getNoticeList(String classId) {
        var result = post(new Message(Constant.NOTICELIST, token, new ClassJson(classId).toString()));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<NoticeJson>>() {}.getType());
    }

    public synchronized Message publishNotice(NoticeJson notice) {
        return post(new Message(Constant.ADDNOTICE, token, notice.toString()));
    }

    public synchronized NoticeJson getNotice(int noticeId) {
        var result = post(new Message(Constant.NOTICEINFO, token, new NoticeJson(noticeId).toString()));
        return result.code < 0 ? null : NoticeJson.parse(result.props);
    }

    public synchronized Message publishVote(NoticeJson notice) {
        return post(new Message(Constant.ADDVOTE, token, notice.toString()));
    }

    public synchronized List<VoteJson> getVoteList(String classId) {
        var result = post(new Message(Constant.VOTELIST, token, new ClassJson(classId).toString()));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<VoteJson>>() {}.getType());
    }

    public synchronized List<MessageJson> getAdviceList(VoteJson vote) {
        var result = post(new Message(Constant.ADVICELIST, token, vote.toString()));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<MessageJson>>() {}.getType());
    }

    public synchronized Message updateNotice(NoticeJson notice) {
        return post(new Message(Constant.UPDATENOTICE, token, notice.toString()));
    }

    public synchronized Message startVote(VoteJson vote) {
        vote.status = 1;
        return post(new Message(Constant.SETVOTE, token, vote.toString()));
    }

    public synchronized Message addVoteAdvice(String sug, int voteId) {
        var msg = new NoticeJson();
        msg.noticeId = voteId;
        msg.text = sug;
        return post(new Message(Constant.ADDADVICE, token, msg.toString()));
    }

    public synchronized Message vote(String username, int voteId, String classId, int agree) {
        var vote = new VoteJson();
        vote.voteId = voteId;
        vote.classId = classId;
        vote.member = username;
        if (agree == 1) {
            vote.agree = 1;
        } else {
            vote.disagree = 1;
        }
        return post(new Message(Constant.VOTE, token, vote.toString()));
    }

    public synchronized List<FileJson> getFileList(ClassJson json) {
        var result = post(new Message(Constant.FILELIST, token, json.toString()));
        if (result.code < 0) return new ArrayList<>();
        return Parser.fromJson(result.props, new TypeToken<List<FileJson>>() {}.getType());
    }

    public synchronized void addFile(FileJson fileJson) {
        post(new Message(Constant.ADDFILE, token, fileJson.toString()));
    }
}