package GUI;
import net.Json.UserJson;
import net.Client.Post;
import util.PictureUtil;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
public class Login_Frame extends JFrame implements ActionListener,Serializable ,KeyListener{
    String _username = "用户名";
    private JTextField username = new JTextField(20);
    JLabel _pwd = new JLabel("密码");
    JPasswordField pwd = new JPasswordField(20);
    JTextField pwdfield = new JTextField(20);
    JButton login = new JButton("登录");
    JButton regilogin = new JButton("注册");
    JButton cancellogin = new JButton("返回");
    private JLabel minButton = new JLabel();
    private JLabel exitButton = new JLabel();
    JPanel _logincenter;
    private Point point = new Point();
//    DBConnection DB = new DBConnection();
//    private DBOperation DBopera = new DBOperation(DB);
    private UserJson user = new UserJson();

    public Login_Frame() {
        super("登录页面");
        setUndecorated(true);
        this.addKeyListener(this);
        init();
        initLister();
    }
    public void keyPressed(KeyEvent e) {
        //组合键x`
        System.out.println("组合键");
        if (Login_Frame.this.isVisible() == true && e.isAltDown() && e.getKeyCode() == KeyEvent.VK_X) {

            Login_Frame.this.setVisible(false);
        }
    }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
    public void init() {
        try {
            setSize(400, 300);


            JPanel _logincenter = new JPanel() {
                //背景设置
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon image = PictureUtil.getPicture("login_bg.png");//获取图像
                    image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST)); //调整图像的分辨率以适应容器
                    image.paintIcon(this, g, 0, 0);
                }
            };
            _logincenter.setLayout(null);
            _logincenter.setBorder(BorderFactory.createLineBorder(Color.GRAY));


            //头像设置
            JPanel _picture = new JPanel();
            JLabel picture = new JLabel();
            ImageIcon image = PictureUtil.getPicture("1.jpg");

            image.setImage(image.getImage().getScaledInstance(92, 92, Image.SCALE_FAST));
            picture.setIcon(image);
            picture.setOpaque(false);
            _logincenter.add(picture);

            picture.setBounds(21, 130, 90, 90);
            picture.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            //面板设置


            // 账号 密码
            username.setText(_username);
            _logincenter.add(username);
            username.setBounds(121, 130, 183, 30);
            _logincenter.add(pwd);
            _logincenter.add(_pwd);

            pwd.setBounds(121, 160, 183, 30);
            _pwd.setBounds(121, 130, 183, 30);
            username.addFocusListener(new MyFocusListener(_username, username, 1));//添加焦点事件反映
            //最小化按钮
            _logincenter.add(minButton);
            minButton.setBounds(338, 0, 31, 20);
            minButton.setIcon(PictureUtil.getPicture("minimize.png"));

            //关闭按钮
            _logincenter.add(exitButton);
            exitButton.setBounds(369, 0, 31, 20);
            exitButton.setIcon(PictureUtil.getPicture("close.png"));
            //登录按钮
            _logincenter.add(login);
            login.setBounds(150, 200, 120, 30);

            //注册按钮
            _logincenter.add(regilogin);
            regilogin.setBounds(314, 130, 62, 30);

            //退出按钮
            _logincenter.add(cancellogin);
            cancellogin.setBounds(314, 160, 62, 30);

            add(_logincenter, BorderLayout.CENTER);
            //连接事件源
            login.addActionListener(this);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLister() {
        //主窗体鼠标拖动
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });
        // 最小化按钮事件
        minButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                minButton.setIcon(PictureUtil.getPicture("minimize.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                minButton.setIcon(PictureUtil.getPicture("minimize_active.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setVisible(false);
            }
        });
        // 退出按钮事件
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(PictureUtil.getPicture("close.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(PictureUtil.getPicture("close_active.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //tray.remove(icon);
                System.exit(0);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuffer get_string = new StringBuffer();
        if (e.getSource() == login) {
            String usernamestring = username.getText().trim();
            if (usernamestring.equals("")) {
                JOptionPane.showMessageDialog(this, "用户名不能为空，请重新输入");
                username.requestFocus(true);
                return;
            }

            String pwdstring = new String(pwd.getPassword());
            if (pwdstring.equals("")) {
                JOptionPane.showMessageDialog(this, "密码不能为空，请重新输入");
                pwd.setText("");
                pwd.requestFocus(true);
                return;
            }

            /*登录逻辑code*/
            user.setUsername(usernamestring);
            user.setPassword(pwdstring);
            new Thread(() ->
            {
                var result = Post.getInstance().login(user);
                if (result.code == 0) {
                    System.out.println(usernamestring + "登陆成功");
                    new MyFrame(usernamestring, result.result);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, result.props);
                    //JOptionPane.showMessageDialog(this, "该用户不存在，请重新输入");
                }
            }).start();

        }
        if (e.getSource() == regilogin) {
            new Register_Frame();
        }
    }
    class MyFocusListener implements FocusListener {
        String info;
        JTextField jtf;
        int x;
        public MyFocusListener(String info, JTextField jtf,int tmp) {
            this.info = info;
            this.jtf = jtf;
            this.x=tmp;
        }

        @Override
        public void focusGained(FocusEvent e) {//获得焦点的时候,清空提示文字
            String temp = jtf.getText();
            if (temp.equals(info)) {
                jtf.setText("");
            }
            if(x==2) {
                _pwd.setText("");
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