package GUI;
import DB.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Register_Frame extends JFrame implements ActionListener, Serializable {
    JLabel _username=new JLabel("用户名:");
    JTextField username=new JTextField(20);
    JLabel _pwd=new JLabel("请输入初始密码:");
    JPasswordField pwd=new JPasswordField(20);
    JLabel _repwd=new JLabel("请再次输入密码:");
    JPasswordField repwd=new JPasswordField(20);
    JButton regist=new JButton("注册");
    JButton clearregist=new JButton("清空");
    JButton cancelregist=new JButton("取消");
    JPanel _top=new JPanel();
    JPanel _registcenter=new JPanel();
    JPanel _floor=new JPanel();
    JLabel _grade=new JLabel("年级:");
    JPanel grade=new JPanel();
    private String[] ia={"大一","大二","大三","大四"};
    private JCheckBox[] jcb_ia=new JCheckBox[ia.length];
    JComboBox classroom;
    JLabel _classroom=new JLabel("班级:");
    private String[] da={"1班","2班","3班","4班","5班","6班","7班","8班","数媒班","新工科"};
    public Register_Frame()
    {
        super("注册页面");
        init();
    }
    public void init()
    {
        setSize(480,320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //面板设置
        _username.setHorizontalAlignment(SwingConstants.RIGHT);
        _pwd.setHorizontalAlignment(SwingConstants.RIGHT);
        _repwd.setHorizontalAlignment(SwingConstants.RIGHT);
        _classroom.setHorizontalAlignment(SwingConstants.RIGHT);
        _grade.setHorizontalAlignment(SwingConstants.RIGHT);
        classroom=new JComboBox(da);
        classroom.setMaximumRowCount(9);
        for(int i=0;i<jcb_ia.length;i++)
        {
            jcb_ia[i]=new JCheckBox(ia[i]);
            grade.add(jcb_ia[i]);
        }
        _registcenter.add(_username);
        _registcenter.add(username);
        _registcenter.add(_pwd);
        _registcenter.add(pwd);
        _registcenter.add(_repwd);
        _registcenter.add(repwd);
        _registcenter.add(_grade);
        _registcenter.add(grade);
        _registcenter.add(_classroom);
        _registcenter.add(classroom);
        _floor.add(regist);
        _floor.add(clearregist);
        _floor.add(cancelregist);
            //带标题缺省边框
        _registcenter.setLayout(new GridLayout(6,2));
        _registcenter.setBorder(BorderFactory.createTitledBorder("请填写用户注册信息"));
        _top.add(new JLabel("欢迎你的加入"));
            //_floor.add(new JLabel("请填写注册信息"));
        add(_top,BorderLayout.NORTH);
        add(_registcenter,BorderLayout.CENTER);
        add(_floor,BorderLayout.SOUTH);
        //连接事件源
        regist.addActionListener(this);
        clearregist.addActionListener(this);
        cancelregist.addActionListener(this);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        StringBuffer get_string =new StringBuffer();
        if(e.getSource()==regist)
        {
            String usernamestring=username.getText().trim();
            if(usernamestring.equals(""))
            {
                JOptionPane.showMessageDialog(this,"用户名不能为空，请重新输入");
                username.requestFocus(true);
                return;
            }
            else{
                get_string.append("欢迎");
                get_string.append("用户:").append(usernamestring);
                get_string.append("的注册!\n");
            }
            String s1=new String(pwd.getPassword());
            String s2=new String(repwd.getPassword());
            if(!s1.equals(s2))
            {
                JOptionPane.showMessageDialog(this,"两次密码输入不一致，请重新输入");
                pwd.setText("");
                repwd.setText("");
                pwd.requestFocus(true);
                return ;
            }
            else{
                get_string.append("您的密码是:").append(s1);
            }
            JOptionPane.showMessageDialog(this,get_string.toString());
            /*
                注册逻辑code.
            */
            int x=0;
            for(int i=0;i<ia.length;i++)
            {
                if(jcb_ia[i].isSelected()==true) {x=i;break;}

            }
            String selclass=classroom.getSelectedItem().toString();

        }
        else if(e.getSource()==clearregist)
        {
            username.setText("");
            pwd.setText("");
            repwd.setText("");
        }
        else
        {
            System.exit(0);
        }
    }
//    public static void main(String[] args)
//    {
//        Register_Frame win=new Register_Frame();
//    }
}