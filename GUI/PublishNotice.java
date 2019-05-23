package GUI;

import GUI.base.CButton;
import GUI.base.CPanel;
import GUI.base.CTextPane;
import bean.User;
import net.Client.ChatClient;
import net.Client.Post;
import net.Json.MessageJson;
import net.Json.NoticeJson;
import GUI.base.font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class PublishNotice extends JFrame{
    NoticeJson noticeJson;
    MyFrame myFrame;
    private int width = 700;
    private int height = 400;
    private User mainStatus;
    private CTextPane textEditor=new CTextPane();
    public PublishNotice(MyFrame myFrame)
    {
        this.myFrame=myFrame;
        this.mainStatus=myFrame.mainStatus;
        setTitle("NoticeEditor");
//        setUndecorated(true);
        setSize(width, height);
        setLocationRelativeTo(null);
        // 设置文本编辑器
        textEditor.setFont(font.createFont(15));
        textEditor.setMinimumSize(new Dimension(0, 0));
        textEditor.setBackground(new Color(0xFBFCFC));
        textEditor.setBorder(new EmptyBorder(20, 20, 0, 0));
        var editorScrollPane = new JScrollPane(textEditor);
        editorScrollPane.setPreferredSize(new Dimension(450, 400));
        editorScrollPane.setBorder(null);
        editorScrollPane.setOpaque(false);
        editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        editorScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        var editorPane = new CPanel(new BorderLayout());
        editorPane.setBackground(new Color(0xFDFEFE));
        editorPane.setPreferredSize(new Dimension(450, height));
        editorPane.add(editorScrollPane, BorderLayout.CENTER);

        var panel = new CPanel(new FlowLayout(FlowLayout.RIGHT));
        var tip=new CButton("发布");
        panel.add(tip);
        tip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var notice = new NoticeJson();
                setEnabled(false);
                    notice.sender = mainStatus.getUserId();
                    notice.time = Calendar.getInstance().getTimeInMillis();
                    notice.text = textEditor.getText();
                    notice.classId = mainStatus.getTargetId();
                    var message = Post.getInstance().publishNotice(notice);
                    if (message.code >= 0) {
                        setEnabled(true);
                        JOptionPane.showMessageDialog(PublishNotice.this, "发布成功");
                        dispose();
                        var ntMsg = createMessageJson(3, "有新公告！");
                        ChatClient.getInstance().sendMessage(ntMsg);
                        myFrame.updateNotice(mainStatus.getTargetId());
                    } else {
                        JOptionPane.showMessageDialog(PublishNotice.this, "发布失败");
                    }
            }
        });
        add(editorPane, BorderLayout.CENTER);
        add(panel,BorderLayout.SOUTH);
        setVisible(true);
    }
    public MessageJson createMessageJson(int type, String text) {
        var ntMsg = new MessageJson();
        ntMsg.type = type;
        ntMsg.sender = mainStatus.getUserId();
        ntMsg.text = text;
        ntMsg.receiver = mainStatus.getTargetId();
        ntMsg.time = Calendar.getInstance().getTimeInMillis();
        return ntMsg;
    }
}