package GUI;

import GUI.base.CButton;
import GUI.base.CLabel;
import GUI.base.CPanel;
import GUI.Login_Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import GUI.base.font;
import net.Client.ChatClient;
import net.Client.Post;
import net.Json.ClassJson;
import net.Json.MessageJson;

public class CreateClass extends JDialog {
    // 窗体高度
    private int width = 540;
    // 窗体宽度
    private int height = 400;
    // 阴影大小
    private int shadowSize = 8;
    private MyFrame myFrame;

    CreateClass(MyFrame myFrame, String title) {
        super(myFrame, title);
        this.myFrame = myFrame;
        //setModal(true);
        init();
        //setUndecorated(true);
        setBackground(Color.white);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        CPanel inputs = new CPanel();
        inputs.setLayout(new BoxLayout(inputs,BoxLayout.Y_AXIS));
        inputs.setBackground(Color.WHITE);
        inputs.setBorder(BorderFactory.createCompoundBorder(inputs.getBorder(), BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY)));
        inputs.setBorder(BorderFactory.createCompoundBorder(inputs.getBorder(), BorderFactory.createEmptyBorder(40, 0, 0, 0)));
        inputs.setPreferredSize(new Dimension(width, height/3));

        CLabel label = new CLabel("创建班级");
        label.setFont(font.createFont(18));
        label.setPreferredSize(new Dimension(300, 30));
        inputs.add(label);
        var gradeId = new JTextField(20);
        gradeId.setText("年级(如2018)");
        gradeId.setPreferredSize(new Dimension(300, 45));
        inputs.add(gradeId);
        gradeId.addFocusListener(new MyFocusListener("年级(如2018)", gradeId, 1));//添加焦点事件反映

        var classId = new JTextField(20);
        classId.setText("班级(如2)");
        classId.setPreferredSize(new Dimension(300, 45));
        inputs.add(classId);
        classId.addFocusListener(new MyFocusListener("班级(如2)", classId, 1));//添加焦点事件反映

        CButton add = new CButton("创建");
        add.addActionListener(e -> {
            if (gradeId.getText() != ""&&classId.getText() != "") {
                var json = new ClassJson();
                json.classid = gradeId.getText()+"000" + classId.getText();
                json.gradeIndex=Integer.parseInt(gradeId.getText());
                json.classIndex=Integer.parseInt(classId.getText());
                json.avatar=-1;
                json.intro="我是一个新班级";
                var result = Post.getInstance().createClass(json);
                if (result.code == 0) {
                    JOptionPane.showMessageDialog(this, result.props);
                    SendClassMessage(json.classid, "大家好，我创建了新班级。");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, result.props);
                }
            }
        });
        add.setPreferredSize(new Dimension(300, 45));
        inputs.add(add);

        add(inputs);
    }

    public void SendClassMessage(String classId, String msg) {
        var message = new MessageJson();
        message.type = 1;
        message.receiver = classId;
        message.sender = myFrame.mainStatus.getUserId();
        message.time = Calendar.getInstance().getTimeInMillis();
        message.text = msg;
        ChatClient.getInstance().sendMessage(message);
    }

    class MyFocusListener implements FocusListener {
        String info;
        JTextField jtf;
        int x;

        public MyFocusListener(String info, JTextField jtf, int tmp) {
            this.info = info;
            this.jtf = jtf;
            this.x = tmp;
        }

        @Override
        public void focusGained(FocusEvent e) {//获得焦点的时候,清空提示文字
            String temp = jtf.getText();
            if (temp.equals(info)) {
                jtf.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {//失去焦点的时候,判断如果为空,就显示提示文字
            String temp = jtf.getText();
            if (temp.equals("")) {
                jtf.setText(info);
            }
        }
    }
}