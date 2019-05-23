package GUI;

import GUI.base.*;
import bean.User;
import net.Client.ChatClient;
import net.Client.Post;
import net.Json.MessageJson;
import net.Json.NoticeJson;
import net.Json.VoteJson;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

/*
*发布投票
* VoteType
*   0为审议投票
*   1为开始投票
*/
public class PublishVote extends JFrame{
    NoticeJson noticeJson;
    MyFrame myFrame;
    private int width = 700;
    private int height = 400;
    private User mainStatus;
    private int VoteType;
    private CTextPane textEditor=new CTextPane();
    JScrollPane editorScrollPane = new JScrollPane(textEditor);
    CPanel editorPane = new CPanel(new BorderLayout());
    CPanel panel = new CPanel(new FlowLayout(FlowLayout.RIGHT));
    private VoteJson vote;
    private NoticeJson notice;
    private CPanel advicePane = new CPanel();;
    public PublishVote(MyFrame myFrame,int VoteType)
    {
        this.myFrame=myFrame;
        this.mainStatus=myFrame.mainStatus;
        setTitle("VoteEditor");
//        setUndecorated(true);
        setSize(width, height);
        setLocationRelativeTo(null);
        this.VoteType=VoteType;
        add(panel,BorderLayout.NORTH);
        add(editorPane, BorderLayout.CENTER);
//        add(adviceScrollPane,BorderLayout.SOUTH);
        setVisible(true);
    }
    public PublishVote(MyFrame myFrame,int VoteType,VoteJson voteJson,NoticeJson notice)
    {
        this(myFrame,VoteType);
        this.vote=voteJson;
        this.notice=notice;
    }
    public void init()
    {
        // 设置文本编辑器
        textEditor.setFont(font.createFont(15));
        textEditor.setMinimumSize(new Dimension(0, 0));
        textEditor.setBackground(new Color(0xFBFCFC));
        textEditor.setBorder(new EmptyBorder(20, 20, 0, 0));
        editorScrollPane.setPreferredSize(new Dimension(width, 200));
        editorScrollPane.setBorder(null);
        editorScrollPane.setOpaque(false);
        editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        editorScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        editorPane.setBackground(new Color(0xFDFEFE));
        editorPane.setPreferredSize(new Dimension(width, 400));
        editorPane.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
        editorPane.add(editorScrollPane, BorderLayout.CENTER);

        advicePane.setLayout(new BoxLayout(advicePane, BoxLayout.Y_AXIS));
        advicePane.setBackground(new Color(0xFBFEFE));
        var adviceScrollPane = new JScrollPane(advicePane);
        adviceScrollPane.setPreferredSize(new Dimension(width, 50));
        adviceScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        adviceScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        adviceScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        adviceScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        adviceScrollPane.setBorder(null);
        editorPane.add(adviceScrollPane, BorderLayout.SOUTH);

        var tip=new CButton("");
        if(VoteType==0) tip.setText("发布投票并开始审议");
        else tip.setText("审议完毕并开始投票");
        panel.add(tip);
        if(VoteType==0)
            tip.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    var notice = new NoticeJson();
                    setEnabled(false);
                    notice.sender = mainStatus.getUserId();
                    notice.time = Calendar.getInstance().getTimeInMillis();
                    notice.text = textEditor.getText();
                    notice.classId = mainStatus.getTargetId();
                    var message = Post.getInstance().publishVote(notice);
                    if (message.code >= 0) {
                        setEnabled(true);
                        JOptionPane.showMessageDialog(PublishVote.this, "发布成功，开始审议");
                        dispose();
                        var ntMsg = createMessageJson(4, "有新投票等待审议哦！");
                        ChatClient.getInstance().sendMessage(ntMsg);
                        myFrame.updateNotice(mainStatus.getTargetId());
                    } else {
                        JOptionPane.showMessageDialog(PublishVote.this, "发布失败");
                    }
                }
            });
        else
        {
            var sugs = Post.getInstance().getAdviceList(vote);
            textEditor.setText(notice.text);

            addTips(sugs);System.out.println("qwqqwqwqwqwqwqwqwqwqw");
            tip.addMouseListener(new MouseAdapter() {
                @Override

                public void mouseClicked(MouseEvent e) {
                    setEnabled(false);
                        var nt = new NoticeJson();
                        nt.sender = mainStatus.getUserId();
                        nt.text = textEditor.getText();
                        nt.classId = "-1";
                        nt.noticeId = vote.noticeId;
                        var message = Post.getInstance().updateNotice(nt);
                        if (message.code >= 0) {
                            setEnabled(true);
                            Post.getInstance().updateNotice(nt);
                            Post.getInstance().startVote(vote);
                            JOptionPane.showMessageDialog(PublishVote.this, "投票已发布");
                            var ntMsg = createMessageJson(5, "有新投票哦。");
                            ChatClient.getInstance().sendMessage(ntMsg);
                            dispose();
                            myFrame.updateVote(mainStatus.getTargetId());
                        } else {
                            JOptionPane.showMessageDialog(PublishVote.this, "发布失败");
                        }

                    }
                });
    }}
    public MessageJson createMessageJson(int type, String text) {
        var ntMsg = new MessageJson();
        ntMsg.type = type;
        ntMsg.sender = mainStatus.getUserId();
        ntMsg.text = text;
        ntMsg.receiver = mainStatus.getTargetId();
        ntMsg.time = Calendar.getInstance().getTimeInMillis();
        return ntMsg;
    }
    private void addTips(List<MessageJson> list) {
        if (list == null) return;
        for (var i : list) {
            createTips(i);
        }
    }

    private int count = 0;
    private void createTips(MessageJson message) {
        var pane = new JPanel();
        pane.setOpaque(false);
        var textArea = new JTextArea();
        textArea.setFont(font.createFont(13));
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(width, 50));

        textArea.setBackground(new Color(count++ % 2 == 1 ? 0xECF0F1 : 0xF0F3F4));
        textArea.setText("建议" +count+"："+message.text);
        textArea.setEditable(false);
        textArea.setBorder(new EmptyBorder(10, 10, 5, 5));
        pane.add(textArea);
        advicePane.add(pane);
    }
}