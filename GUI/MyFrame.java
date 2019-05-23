package GUI;
import GUI.base.*;
import bean.User;
import bean.Constant;

import net.Client.ChatListener;
import net.Json.*;
import net.Client.ChatClient;
import net.Client.FileClient;
import net.Client.Post;
import bean.Emoticon;
import util.ImageUtil;
import util.PictureUtil;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import bean.*;
public class MyFrame extends JFrame implements ActionListener,Serializable, ChatListener,KeyListener {
    private CPanel top=new CPanel();
    private CPanel picpane=new CPanel();
    private CPanel toolPane = new CPanel();

    private JLabel minButton=new JLabel();
    private JLabel exitButton=new JLabel();

    private Point point = new Point();
    private CTextPane msgInput;
    public Emoticon image;
    private int width = 1100;
    private int height = 600;
    private int titleBarHeight = 60;
    private int paneHeight = height - titleBarHeight;
    private int groupWidth = 300;
    private int paneWidth = width - groupWidth;
    private int chatTitleBarHeight = 50;
    private int chatMessageHeight = 160;
    private int chatHeight = paneHeight - chatMessageHeight - chatTitleBarHeight;
    private int msgToolBarHeight = 30;
    private int msgSendHeight = 35;
    private int msgTextAreaHeight = chatHeight - msgSendHeight - msgToolBarHeight;
    private int chatBarHeight = 60;
    private int groupHeight = paneHeight - chatBarHeight;
    private int avatarSize = 45;

    private int noticeWidth = 600;
    private int noticeHeight = 250;
    UserJson me;
    public User mainStatus;
    private SystemTray tray;// 系统托盘
    private TrayIcon trayIcon;
    boolean Tipflag=false;
    public MyFrame(String username,int token)
    {
        super("班级事务管理系统");
        try {
            //变为windows风格
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.addKeyListener(this);

        setUndecorated(true);
        mainStatus = new User();
        mainStatus.setUserId(username);
        mainStatus.setToken(token);
        me=Post.getInstance().getUserInfo(username);
//        me.avatar=getAvatarId(username,false);
        ChatClient.getInstance().login(mainStatus.getToken());
        ChatClient.getInstance().addListener(this);
        init();
        initLister();
        setSize(1100,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        setTray();
    }
    //状态托盘
    private void setTray() {
        ImageIcon icon = PictureUtil.getPicture("tray.jpg");
        // 获得Image对象
        Image image = icon.getImage();
        // 创建托盘图标
        trayIcon = new TrayIcon(image);
        // 为托盘添加鼠标适配器
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyFrame.this.setVisible(true);//显示窗口
                Tipflag = false;
            }
        });
        trayIcon.setToolTip("ID:" + me.username + "\n" + "状态:在线");
        PopupMenu trayMenu = new PopupMenu();
        var open = new MenuItem("Open");
        var shutdown = new MenuItem("Exit");
        var addClass = new MenuItem("AddClass");
        var createClass = new MenuItem("CreateClass");
        var game = new MenuItem("2048 game");
        trayMenu.add(open);
        trayMenu.addSeparator();//分界
        trayMenu.add(createClass);
        trayMenu.add(addClass);
        trayMenu.addSeparator();//分界
        trayMenu.add(game);
        trayMenu.addSeparator();//分界
        trayMenu.add(shutdown);
        trayIcon.setPopupMenu(trayMenu);
        shutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame.this.setVisible(true);//显示窗口
            }
        });
        addClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClass(MyFrame.this, "加入班级");
            }
        });
        createClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateClass(MyFrame.this, "创建班级");
            }
        });
        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game2048.run(new Game2048(),900,800,mainStatus);
            }
        });
        tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        //托盘闪烁
        new Thread(() ->
        {
            try {
                while (true) {
//                    System.out.println(Tipflag);
                    if(this.isVisible()==true) Tipflag=false;
                        if (Tipflag == true)
                        {

                            for (int i = 0; i < 10; i++) {
                                trayIcon.setImage(tk.createImage(""));
                                Thread.sleep(200);
                                trayIcon.setImage(tk.createImage("E:\\keshe\\src\\util\\resource\\image\\tray.jpg"));
                                Thread.sleep(200);
                            }
                        }
                    Thread.sleep(1000);
                    }

            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void init()
    {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        initTop();initChat();
        setMyAvatar();
        System.out.println("获取头像");
        getMessageList();
        System.out.println("获取消息列表");
        addOldChat();
    }
    CImage picture;
    public void initTop()
    {
        //标题bar
        top.setLayout(new BorderLayout());
        top.setBackground(new Color(0x6699FF));
        top.setPreferredSize(new Dimension(1100, 60));//随窗口拖动
        add(top,BorderLayout.NORTH);

        //头像区域
        picpane.setLayout(new BoxLayout(picpane, BoxLayout.X_AXIS));//水平排列
        picpane.setPreferredSize(new Dimension(150, 60));

        ImageIcon image=PictureUtil.getPicture("rounded_corners.png");
        image.setImage(image.getImage().getScaledInstance(45,45,Image.SCALE_FAST));
        picture=new CImage(image);
        picture.setPreferredSize(new Dimension(45, 45));
        picpane.setBorder(new EmptyBorder(0, 25, 0, 0));
        picpane.setBackground(new Color(0x6699FF));
        picpane.add(picture);
        top.add(picpane, BorderLayout.WEST);
        picture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UserInfo(me,MyFrame.this);
            }
        });

        //工具栏区域
        toolPane.setLayout(new BoxLayout(toolPane, BoxLayout.X_AXIS));
        toolPane.setBackground(new Color(0x6699FF));
        toolPane.setPreferredSize(new Dimension(150, 60));
        top.add(toolPane, BorderLayout.EAST);
        minButton.setBounds(1000, 0, 31, 20);
        minButton.setIcon(PictureUtil.getPicture("minimize.png"));
        exitButton.setBounds(1050, 0, 31, 20);
        exitButton.setIcon(PictureUtil.getPicture("close.png"));
        minButton.setPreferredSize(new Dimension(30, 30));
        exitButton.setPreferredSize(new Dimension(30, 30));
        toolPane.add(minButton);
        toolPane.add(exitButton);
    }
    CLabel chatTitle = new CLabel("欢迎使用本系统");
    JPanel chatTool = new JPanel();
    JPanel centerPane = new JPanel(new BorderLayout());//主面板
    private CPanel cardPane = new CPanel(new CardLayout());
    JPanel chatTitleBar = new JPanel(new BorderLayout());
    JLabel back=new JLabel();
    private void initChat()
    {

        centerPane.setBackground(new Color(0xF2F3F4));
        centerPane.setPreferredSize(new Dimension(paneWidth, paneHeight));
        add(centerPane, BorderLayout.CENTER);
        // 标题栏

        chatTitleBar.setBackground(new Color(0xFDFEFE));
        chatTitleBar.setPreferredSize(new Dimension(paneWidth, chatTitleBarHeight));
        chatTitleBar.setBorder(BorderFactory.createCompoundBorder(chatTitleBar.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xD0D3D4))));
        chatTitle.setFont(font.createFont(18));
        chatTitle.setBorder(BorderFactory.createCompoundBorder(chatTitle.getBorder(),
                BorderFactory.createEmptyBorder(0, 25, 0, 0)));
        chatTitleBar.add(chatTitle, BorderLayout.WEST);

        // 功能切换工具栏
        chatTool.setLayout(new BoxLayout(chatTool, BoxLayout.X_AXIS));
        chatTool.setBackground(new Color(0xFDFEFE));
        chatTool.setPreferredSize(new Dimension(230, chatTitleBarHeight));
        // 聊天标签
        // 聊天标签栏
        CLabel chatLabel = new CLabel(" 聊天 ");
        chatLabel.setFont(font.createFont(15));
        chatLabel.addActionListener(this);
        chatTool.add(chatLabel);
        chatLabel.addListener();
        // 公告标签
        CLabel noticeLabel = new CLabel(" 公告 ");
        noticeLabel.setFont(font.createFont(15));
        noticeLabel.addActionListener(this);
        chatTool.add(noticeLabel);
        noticeLabel.addListener();
        // 文件标签
        CLabel fileLabel = new CLabel(" 文件 ");
        fileLabel.setFont(font.createFont(15));
        fileLabel.addActionListener(this);
        chatTool.add(fileLabel);
        fileLabel.addListener();
        // 投票标签
        CLabel voteLabel = new CLabel(" 投票 ");
        voteLabel.setFont(font.createFont(15));
        voteLabel.addActionListener(this);
        chatTool.add(voteLabel);
        voteLabel.addListener();

        chatTool.setVisible(true);
        chatTitleBar.add(chatTool, BorderLayout.EAST);
        centerPane.add(chatTitleBar, BorderLayout.NORTH);
        centerPane.add(cardPane, BorderLayout.CENTER);


        ImageIcon png1=PictureUtil.getPicture("TIMbackground.png");
        png1.setImage(png1.getImage().getScaledInstance(800,paneHeight,Image.SCALE_FAST));
        back.setIcon(png1);
        // 以下区域分为若干个标签栏
        initChatCard();
        initFriendsListCard();
        initVoteCard();
        initFileCard();
        initNoticeCard();
        //centerPane.add(back);
        cardPane.setVisible(false);
    }

    //表情插入
    public void insertIcon(ImageIcon icon) {
        msgInput.insertIcon(icon);
    }

    // 用于切换公告、聊天、投票、文件

    private final String CHAT_CARD = "CHAT";
    private final String VOTE_CARD = "VOTE";
    private final String NOTICE_CARD = "NOTICE";
    private final String FILE_CARD = "FILE";

    private void switchCardPane(String target) {
        ((CardLayout)cardPane.getLayout()).show(cardPane, target);
    }
    public void actionPerformed(ActionEvent e) {
        var command = e.getActionCommand().trim();
        switch (command) {
            case "聊天":
                switchCardPane(CHAT_CARD);
                break;
            case "文件":
                switchCardPane(FILE_CARD);
                updateFile(mainStatus.getTargetId());
                break;
            case "投票":
                switchCardPane(VOTE_CARD);
                updateVote(mainStatus.getTargetId());
                break;
            case "公告":
                switchCardPane(NOTICE_CARD);
                updateNotice(mainStatus.getTargetId());
                break;

        }
    }
    CLabel sendpicture=new CLabel();
    CLabel screenshot=new CLabel();
    CLabel emoticon=new CLabel();
    CLabel shake=new CLabel();
    CPanel chatPane = new CPanel();
    public void initChatCard()
    {
        CPanel chatCard = new CPanel(new BorderLayout());
        // 聊天区域

        chatPane.setLayout(new BoxLayout(chatPane, BoxLayout.Y_AXIS));
        //chatPane.setPreferredSize(new Dimension(paneWidth, chatHeight));

        chatPane.setBackground(new Color(0xFDFEFE));
        chatPane.setBackground(Color.white);

        var chatScrollPane = new JScrollPane(chatPane);
        chatScrollPane.setBorder(null);
        chatScrollPane.setPreferredSize(new Dimension(paneWidth, chatHeight));
        chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        chatScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        chatCard.add(chatScrollPane, BorderLayout.CENTER);

        // 发送消息区域
        CPanel msgPane = new CPanel(new BorderLayout());
        msgPane.setBackground(new Color(0xFDFEFE));
        msgPane.setPreferredSize(new Dimension(paneWidth, chatMessageHeight));
        chatCard.add(msgPane, BorderLayout.SOUTH);
        // 发送消息工具栏
        CPanel toolBar = new CPanel(new FlowLayout(FlowLayout.LEFT));
        ((FlowLayout)toolBar.getLayout()).setHgap(10);
        toolBar.setPreferredSize(new Dimension(paneWidth, msgToolBarHeight));
        toolBar.setBackground(new Color(0xFDFEFE));
        toolBar.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        toolBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0xD0D3D4)), toolBar.getBorder()));
        toolBar.add(sendpicture);

        // 插入图片按钮
        sendpicture.addListener();
        ImageIcon image=PictureUtil.getPicture("sendpicture.png");
        image.setImage(image.getImage().getScaledInstance(20,20,Image.SCALE_FAST));
        sendpicture.setIcon(image);
        sendpicture.addActionListener(this);
        sendpicture.setPreferredSize(new Dimension(20, 20));

        // 表情按钮
        emoticon.addListener();
        emoticon.setPreferredSize(new Dimension(20, 20));
        emoticon.setToolTipText("表情");
        emoticon.setIcon(PictureUtil.getPicture("emoticon.png"));
        toolBar.add(emoticon);

        // 抖动按钮
        shake.addListener();
        shake.setBounds(70, 320, 20, 20);
        shake.setToolTipText("抖动");
        shake.setIcon(PictureUtil.getPicture("shake.png"));
        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shake();
                sendMessage("$发送了一个窗口抖动。");
            }
        });
        toolBar.add(shake);

        // 画笔按钮
        CLabel paint = new CLabel();
        paint.addListener();
        paint.setPreferredSize(new Dimension(20, 20));
        ImageIcon image2=PictureUtil.getPicture("paint.png");
        image2.setImage(image2.getImage().getScaledInstance(20,20,Image.SCALE_FAST));
        paint.setIcon(image2);
        paint.addActionListener(this);
        toolBar.add(paint);
        paint.addActionListener(e->new Draw(MyFrame.this));

        // 截图按钮
        screenshot.addListener();
        ImageIcon image3=PictureUtil.getPicture("screenCapture.png");
        image3.setImage(image3.getImage().getScaledInstance(20,20,Image.SCALE_FAST));
        screenshot.setIcon(image3);
        screenshot.setPreferredSize(new Dimension(20, 20));
        screenshot.addActionListener(this);
        toolBar.add(screenshot);
        msgPane.add(toolBar, BorderLayout.NORTH);

        // 发送消息文本区域
        msgInput = new CTextPane();
        msgInput.setFont(font.createFont(15));
        msgInput.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 12));

        JScrollPane msgScrollPane = new JScrollPane(msgInput);
        msgScrollPane.setBorder(null);
        msgScrollPane.setPreferredSize(new Dimension(paneWidth, msgTextAreaHeight));
        msgScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        msgScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        msgScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));

        toolBar.setBackground(new Color(0xFDFEFE));
        msgPane.add(msgScrollPane, BorderLayout.CENTER);
        // 发送按钮
        msgInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    sendMessage(msgInput.getMessage());
                    msgInput.setText("");
                }
                if(e.isControlDown()&&e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_X)
                {
                    ScreenShot rd =new ScreenShot(MyFrame.this);
                }
            }
        });
        var sendPane = new JPanel(new BorderLayout());
        JButton send = new JButton("发送");
        // 发送功能
        send.addActionListener(e-> {
            sendMessage(msgInput.getMessage());
            msgInput.setText("");
        });
        send.setPreferredSize(new Dimension(100, 30));
        sendPane.setBackground(new Color(0xFDFEFE));
        sendPane.add(send, BorderLayout.EAST);
        sendPane.setBorder(new EmptyBorder(8, 0, 8, 8));
        msgPane.add(sendPane, BorderLayout.SOUTH);
        cardPane.add(chatCard, CHAT_CARD);

        //switchCardPane("CHAT");
    }
    private JPanel voteCard;
    private void initVoteCard() {
        var publish = new CButton("发布新投票");
        var publishPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        publishPane.setBackground(new Color(0xECF0F1));
        publish.addActionListener(e-> {
            // 判断是否是管理员
            if (mainStatus.isAdmin()) {
                new PublishVote(MyFrame.this,0).init();
            } else {
                new MessageDialog(this, "只有管理员可以发布投票，您可以申请管理员身份。", "我不能那么做");
            }
        });

        publishPane.add(publish);

        var pane = new JPanel(new BorderLayout());
        voteCard = new JPanel();
        voteCard.setBackground(new Color(0xF4F6F7));
        voteCard.setLayout(new BoxLayout(voteCard, BoxLayout.Y_AXIS));
        var scrollPane = new JScrollPane(voteCard);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scrollPane.setBackground(new Color(0xF4F6F7));
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(publishPane, BorderLayout.NORTH);
        cardPane.add(pane, VOTE_CARD);
    }

    public void updateVote(String classId) {
        var list = Post.getInstance().getVoteList(classId);
        if (list == null) return;
        voteCard.removeAll();
        for (var i : list) {
            var pane = createVote(i);
            voteCard.add(pane);
        }
        voteCard.revalidate();
        voteCard.repaint();
    }

    private JPanel createVote(VoteJson vote) {
        var pane = new JPanel(new BorderLayout());
        pane.setPreferredSize(new Dimension(paneWidth, noticeHeight + 75));
        pane.setMaximumSize(new Dimension(paneWidth, noticeHeight + 75));
        var notice = Post.getInstance().getNotice(vote.noticeId);
        var noticePane = createNotice(notice);
        pane.add(noticePane, BorderLayout.CENTER);

        var operator = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operator.setBorder(new EmptyBorder(0, 120, 0, 120));
        operator.setBackground(new Color(0xF0F3F4));
        switch (vote.status) {
            case 0:
                // 刚刚发布，等待审议
                if (notice.sender.equals(mainStatus.getUserId())) {
                    var update = new CButton("修改");
                    update.addActionListener(e-> {
                        new PublishVote(MyFrame.this,1,vote,notice).init();
                    });
                    operator.add(update);
                } else {
                    var advice = new CButton("建议");
                    advice.addActionListener(e-> {
                        var sug = JOptionPane.showInputDialog("输入您的建议（匿名）");
                        if (sug != null) {
                            Post.getInstance().addVoteAdvice(sug, vote.voteId);
                            new MessageDialog(this, "建议添加成功", "成功");
                        }
                    });
                    operator.add(advice);
                }
                break;
            case 1:
                // 投票阶段
                var agree = new CButton("同意");
                var disagree = new CButton("不同意");
                agree.addActionListener(e-> {
                    var msg = Post.getInstance().vote(mainStatus.getUserId(), vote.voteId, mainStatus.getTargetId(), 1);
                    if (msg.code >= 0) {
                        new MessageDialog(this, "投票成功！", "成功");
                    } else {
                        new MessageDialog(this, msg.props, "失败");
                    }
                    updateVote(mainStatus.getTargetId());
                });
                disagree.addActionListener(e-> {
                    var msg = Post.getInstance().vote(mainStatus.getUserId(), vote.voteId, mainStatus.getTargetId(), -1);
                    if (msg.code >= 0) {
                        new MessageDialog(this, "投票成功！", "成功");
                    } else {
                        new MessageDialog(this, msg.props, "失败");
                    }
                    updateVote(mainStatus.getTargetId());
                });
                var result = new CLabel("投票中：" + vote.agree + "人同意，" + vote.disagree + "人不同意");
                operator.add(result);;
                operator.add(agree);
                operator.add(disagree);
                break;
            case 2:
                result = new CLabel("投票完毕：" + vote.agree + "人同意，" + vote.disagree + "人不同意");
                operator.add(result);;
        }
        pane.add(operator, BorderLayout.SOUTH);
        return pane;
    }
    private JPanel fileCard;

    public File chooseFile() {
        JFileChooser chooser = new JFileChooser();
        // 限制文件是为了安全考虑
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "选择一个文件", "jpg", "png", "zip", "exe", "rar", "7z", "psd", "cpp", "mp4", "avi", "flv", "fbx");
        chooser.setFileFilter(filter);

        if(chooser.showOpenDialog(new TextField()) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    //文件列表
    private JPanel createFile(FileJson file) {
        var filePane = new JPanel(new FlowLayout(FlowLayout.LEFT,100,0));
        var pane = new JPanel(new BorderLayout());
        filePane.setBorder(new EmptyBorder(10, 0, 0, 0));
        filePane.setPreferredSize(new Dimension(paneWidth * 8 / 10, 40));
        filePane.setMaximumSize(new Dimension(paneWidth , 40));
        // 添加文件
        var icon =PictureUtil.getPicture("file.jpg");
        ImageUtil.scaleImage(icon, 30);
        var image = new CLabel();image.setIcon(icon);image.setText(file.name);
        filePane.add(image);
        var label = new CLabel(file.name);
        var button = new CButton("下载");
        button.addActionListener(e-> {
            if (e.getActionCommand().equals("下载"))
            {
                FileClient.getInstance().addDownloadListener((p, l)-> {
                    button.setText(p / (double)l + "%");
                }, true);
                var msg = FileClient.getInstance().download(file.fileId, Constant.getFileReceivePath(), false);
                if (msg.code >= 0) {
                    button.setText("打开");
                    JOptionPane.showMessageDialog(this, "下载成功");
                }
            }
            else{
                try
                {
                    Desktop.getDesktop().open(new File(Constant.getFileReceivePath()+"\\"+file.name));
                }
                catch (IOException ee)
                {
                    ee.printStackTrace();
                }
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        filePane.add(new CLabel(sdf.format(file.time)));
        filePane.add(new CLabel(Constant.getFilesize(file.length)));
        var sender = Post.getInstance().getUserInfo(file.owner);
        filePane.add(new CLabel(sender.username));
        filePane.add(button);

        return filePane;
    }
    //更新文件列表
    private void updateFile(String classId) {
        var tot=0;
        var list = Post.getInstance().getFileList(new ClassJson(classId));
        if (list == null) return;
        fileCard.removeAll();
        for (var i : list) {
            tot++;
            var pane = createFile(i);
            fileCard.add(pane);
        }
        tip.setText("共有"+tot+"个文件");
        fileCard.revalidate();
        fileCard.repaint();
    }
    CLabel tip=new CLabel();
    private void initFileCard()
    {

        var open = new CButton("打开下载文件夹");
        open.addActionListener(e-> {
            try {
                Desktop.getDesktop().open(new File(Constant.getFileReceivePath()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        var upload = new CButton("上传文件");
        var uploadPane = new CPanel();
        uploadPane.setLayout(new BoxLayout(uploadPane,BoxLayout.Y_AXIS));
        var uploadPane1 = new CPanel(new FlowLayout(FlowLayout.RIGHT));
        uploadPane1.setBorder(BorderFactory.createCompoundBorder(uploadPane1.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xD0D3D4))));
        uploadPane1.setBackground(new Color(0xFDFEFE));
        upload.addActionListener(e-> {
            // 上传文件
            var file = chooseFile();
            if (file != null) {
                var fileJson = new FileJson();
                fileJson.path = file.getParent() + "\\" + file.getName();
                fileJson.owner = mainStatus.getUserId();
                fileJson.name = file.getName();
                fileJson.length = file.length();
                fileJson.time=Calendar.getInstance().getTimeInMillis();
                var msg = FileClient.getInstance().upload(fileJson);
                if (msg.code >= 0) {
                    JOptionPane.showMessageDialog(this, "文件上传成功");
                    fileJson.fileId = msg.code;
                    fileJson.classId = mainStatus.getTargetId();
                    Post.getInstance().addFile(fileJson);
                } else {
                    JOptionPane.showMessageDialog(this, "文件上传失败");
                }
                updateFile(mainStatus.getTargetId());
            }

        });
        uploadPane1.add(tip);
        uploadPane1.add(open);
        uploadPane1.add(upload);
        var uploadPane2 = new CPanel(new FlowLayout(FlowLayout.LEFT,120,0));
        JLabel filename=new JLabel();
        JLabel uploadtime=new JLabel();
        JLabel filesize=new JLabel();
        JLabel uploader=new JLabel();
        filename.setText("文件");
        uploadtime.setText("更新时间");
        filesize.setText("大小");
        uploader.setText("上传者");
        uploadPane2.add(filename);
        uploadPane2.add(uploadtime);
        uploadPane2.add(filesize);
        uploadPane2.add(uploader);
        uploadPane.add(uploadPane1);
        uploadPane.add(uploadPane2);
        var pane = new CPanel(new BorderLayout());
        fileCard = new CPanel();
        fileCard.setBackground(new Color(0xFDFEFE));
        fileCard.setLayout(new BoxLayout(fileCard, BoxLayout.Y_AXIS));
        var scrollPane = new JScrollPane(fileCard);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scrollPane.setBackground(new Color(0xFDFEFE));
        pane.setBackground(new Color(0xFDFEFE));
        pane.add(uploadPane, BorderLayout.NORTH);
        pane.add(scrollPane, BorderLayout.CENTER);


        cardPane.add(pane, FILE_CARD);
    }

    private JPanel friend;
    private void initFriendsListCard()
    {
        // 好友列表区域面板
        CPanel westPane = new CPanel();
        westPane.setBackground(new Color(0xF7F9F9));
        westPane.setPreferredSize(new Dimension(groupWidth, paneHeight));
        add(westPane, BorderLayout.WEST);

        // 好友标题栏
        JPanel groupTitleBar = new JPanel(new BorderLayout());
        groupTitleBar.setBackground(new Color(0xFDFEFE));
        groupTitleBar.setPreferredSize(new Dimension(groupWidth, chatTitleBarHeight));
        groupTitleBar.setBorder(BorderFactory.createCompoundBorder(groupTitleBar.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(0xD0D3D4))));
        CLabel groupTitle = new CLabel("消息列表");
        groupTitle.setFont(font.createFont(18));
        groupTitle.setBorder(BorderFactory.createCompoundBorder(groupTitle.getBorder(),
                BorderFactory.createEmptyBorder(0, 25, 0, 0)));     // Margin
        groupTitleBar.add(groupTitle, BorderLayout.CENTER);
        //groupTitleBar.removeMouseListener();
        westPane.add(groupTitleBar, BorderLayout.NORTH);

        // 好友列表
        friend = new JPanel();
        friend.setOpaque(false);
        friend.setBackground(new Color(0xFDFEFE));
        friend.setLayout(new BoxLayout(friend, BoxLayout.Y_AXIS));

        JScrollPane groupPane = new JScrollPane(friend);
        groupPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        groupPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        groupPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        groupPane.getVerticalScrollBar().setUnitIncrement(16);
        groupPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(0xD0D3D4)));
        groupPane.setPreferredSize(new Dimension(groupWidth, groupHeight));
        westPane.add(groupPane, BorderLayout.CENTER);
    }
    void chatTo(UserJson user) {
        if (user.username.equals(mainStatus.getUserId())) {
            new JDialog(this,"不能跟自己聊");
        } else {
            toFront();
            addAndGetChatBar(user).showCount(false);
            setChatPane(user);
            switchCardPane(CHAT_CARD);
        }
    }

    /**
     * 得到目标头像
     */
    public ImageIcon getAvatar(int avatarId) {
        var path = Constant.getImageReceivePath();
        if (!new File(path + "\\" + avatarId).exists()) {
            var msg = FileClient.getInstance().download(avatarId, path, true);
            return new ImageIcon(msg.props);
        }
        return new ImageIcon(path + "\\" + avatarId);
    }
    private int getAvatarId(String userid, boolean isClass) {
        return Post.getInstance().getAvatarId(userid, isClass);
    }
    private CLabel avatarImage=new CLabel();

    /**
     * 设置左上角的头像
     */
    private void setMyAvatar() {
        var avatar = getAvatar(getAvatarId(mainStatus.getUserId(), false));
        picture.setIcon(avatar);
        picture.setPreferredSize(new Dimension(avatarSize, avatarSize));
        picture.setRound();
    }

    private void addChatBar(ChatBar chatBar) {
        chatBars.add(chatBar);
        friend.add(chatBar.getPane());
    }

    private void repaintChatBar() {
        chatBars.sort((a, b) -> {
            if (a.getType() == b.getType()) {
                if (a.isShowCount() && b.isShowCount()) return 0;
                else if (a.isShowCount()) return -1;
                else return 1;
            } else if (a.getType() > b.getType()) {
                return -1;
            } else {
                return 1;
            }
        });
        friend.removeAll();
        for (var i : chatBars) {
            friend.add(i.getPane());
        }
        friend.revalidate();
        friend.repaint();
    }
    /**
     * 获取旧聊天记录文件夹中的用户
     */
    private List<String> getOldChatUser() {
        List<String> files = new ArrayList<>();
        File file = new File(Constant.getRecordPath(mainStatus.getUserId()));

        File[] tempList = file.listFiles();
        if (tempList == null) return null;
        for (File aTempList : tempList) {
            if (aTempList.isFile()) {
                files.add(aTempList.getName());
            }
        }
        return files;
    }
    private void addOldChat() {
        var list = getOldChatUser();
        if (list == null) return;
        for(var u : list) {
            var user = new UserJson();
            user.username = u;
            user = Post.getInstance().getUserInfo(user);
            if (user != null) addAndGetChatBar(user).showCount(false);
        }
    }
    private void getMessageList() {
        var list = Post.getInstance().getMyClass();
        if (list == null) {
            chatTitle.setText("您尚未加入班级");
            chatTool.setVisible(false);
            return;
        }
        for (final ClassJson classJson : list) {
            String title = classJson.gradeIndex + "级" + classJson.classIndex + "班";
            var avatar = getAvatar(classJson.avatar);

            final var chatBar = new ChatBar(classJson.classid, title, avatar, 999, 40, groupWidth, 70);
            chatBar.addActionListener(e -> {
                setChatPane(classJson);
                chatBar.showCount(false);
            });
            addChatBar(chatBar);
        }
        var list2 = Post.getInstance().getChatUser();
        if (list2 == null) {
            return;
        }
        for (var userJson : list2) {
            String title = userJson.username;
            ImageIcon avatar = getAvatar(userJson.avatar);
            final var chatBar = new ChatBar(userJson.username, title, avatar, 0, 40, groupWidth, 70);
            chatBar.addActionListener(e -> {
                setChatPane(userJson);
                chatBar.showCount(false);
            });
            addChatBar(chatBar);
        }
    }

    public void sendMessage(String msg)
    {
        new Thread(()-> {
            try {
                if(msg.equals("")) return;
                var result = new StringBuffer();
                var m = Pattern.compile("(?<!\\\\)<([^<>]*)(?<!\\\\)>").matcher(msg);
                while (m.find()) {
                    var img = new FileJson();
                    img.owner = mainStatus.getUserId();
                    img.path = m.group(1);
                    m.appendReplacement(result, "<" + FileJson.parse(FileClient.getInstance().upload(img).props).fileId + ">");
                }
                m.appendTail(result);
                var message = new MessageJson();
                message.type = mainStatus.isClass() ? 1 : 0;
                message.receiver = mainStatus.getTargetId();
                message.sender = mainStatus.getUserId();
                message.time = Calendar.getInstance().getTimeInMillis();
                message.text = result.toString();
                ChatClient.getInstance().sendMessage(message);
                System.out.println("消息发送成功");

            } catch (Exception e) {
                new JDialog(this, "消息发送失败");
            }
        }).start();
    }

    private List<ChatBar> chatBars = new ArrayList<>();
    private ChatBar addAndGetChatBar(ClassJson json) {
        // 判断是否已经被添加
        for (var i : chatBars) {
            if (i.getId().equals(json.classid)) {
                return i;
            }
        }
        json = Post.getInstance().getClassInfo(json);
        var chatBar = new ChatBar(json.classid, json.gradeIndex + "级" + json.classIndex + "班", getAvatar(json.avatar), 999, 40, groupWidth, 70);
        ClassJson finalJson = json;
        chatBar.addActionListener(e-> {
            setChatPane(finalJson);
            chatBar.showCount(false);
        });
        addChatBar(chatBar);
        repaintChatBar();
        return chatBar;
    }
    private ChatBar addAndGetChatBar(UserJson user) {
        // 判断是否已经被添加
        for (var i : chatBars) {
            if (i.getId().equals(user.getUsername())) {
                return i;
            }
        }
        user = Post.getInstance().getUserInfo(user);
        var chatBar = new ChatBar(user.getUsername(), user.getId(),getAvatar(user.avatar), 0, 40, groupWidth, 70);
        UserJson finalUser = user;
        chatBar.addActionListener(e-> {
            setChatPane(finalUser);
            chatBar.showCount(false);
        });
        addChatBar(chatBar);
        return chatBar;
    }
    private MessageJson createClassChatMessageJson() {
        var message = new MessageJson();
        message.receiver = mainStatus.getTargetId();
        message.type = 1;
        return message;
    }
    /**
     * 添加聊天气泡到面板
     */
    UserJson userJ[]=new UserJson[101];
    MemberJson memberJson[]=new MemberJson[101];
    private void addChatBubble(int i,ChatBubble bubble, int avatarId, String sender) {
        userJ[i]=Post.getInstance().getUserInfo(sender);
        memberJson[i]=new MemberJson();
        memberJson[i].userId=sender;memberJson[i].classId=mainStatus.getTargetId();
        var avatar = new CImage(getAvatar(avatarId));
        avatar.setPreferredSize(new Dimension(30, 30));
        avatar.setRound();
        avatar.setToolTipText(sender); //提示信息
        CPanel avatarPane = new CPanel();
        avatarPane.add(avatar);
        avatarPane.setOpaque(false);
        avatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1)
                {
                    if(bubble.isClass()) chatTo(userJ[i]);
                    else
                    {
                        if(e.getClickCount()==1)new UserInfo(userJ[i],MyFrame.this);
                    };
                }
                if(e.getClickCount()==2) if(bubble.isClass()) new MyDialog(MyFrame.this,true,memberJson[i],"确定设置其为管理员吗");

            }
        });
        CPanel panel = new CPanel(new FlowLayout(bubble.isLeft() ? FlowLayout.LEFT : FlowLayout.RIGHT));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        panel.setOpaque(false);

        if (bubble.getPreferredSize().width > paneWidth * 0.65) {
            bubble.setPreferredSize((int) (paneWidth * 0.65));
        }
        if (bubble.isLeft()) panel.add(avatarPane);
        panel.add(bubble);
        if (!bubble.isLeft()) panel.add(avatarPane);
        avatarPane.setPreferredSize(new Dimension(avatarPane.getPreferredSize().width, bubble.getPreferredSize().height));
        panel.setMaximumSize(new Dimension(Short.MAX_VALUE, panel.getPreferredSize().height));
        chatPane.add(panel);
    }

    private void addChatBubble(List<MessageJson> list, boolean append) {
        int maxChatCount = 10;
        int tot=0;
        if (list.size() > maxChatCount && !append) {// 取原先消息的10条显示
            list = list.subList(list.size() - maxChatCount - 1, list.size());
        }
        for (var i : list) {
            addChatBubble(++tot,createChatBubble(i), getAvatarId(i.sender, false), Post.getInstance().getUserInfo(i.sender).username);
        }
    }

    private List<String> getRecord(String id) {
        try {
            var path = Constant.getRecordPath(mainStatus.getUserId(), id);
            var file = new File(path);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            var fin = new FileInputStream(file);
            var reader = new BufferedReader(new InputStreamReader(fin));
            var line = "";
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null)   {
                lines.add(line);
            }
            reader.close();
            fin.close();
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    private void removeChatBubble() {
        chatPane.removeAll();
        chatPane.revalidate();
        chatPane.repaint();
    }
    // 重新加载消息记录
    private void reloadRecord(MessageJson message) {
        // 先获取最新的消息记录
        var list = Post.getInstance().getMessage(message);
        var target = message.type == 0 ? message.sender : message.receiver;
        if (list != null) saveRecord(target, list);
        removeChatBubble();
        var lines = getRecord(target);
        // 最多显示50条消息
        List<MessageJson> msgList = new ArrayList<>();
        for (var i : lines) {
            var msg = MessageJson.parser(i);
            if (msg.type >= 2) {
                var json = Post.getInstance().getClassInfo(new ClassJson(message.receiver));
                showImportant(msg, json.gradeIndex + "级" + json.classIndex + "班");
            }
            msgList.add(msg);
        }
        // 将消息列表加入气泡
        addChatBubble(msgList, false);
    }

    // 获取群组消息并添加到面板
    private List<MessageJson> getClassMessage(MessageJson message) {
        return Post.getInstance().getMessage(message);
    }

    // 设置聊天面板内容（私聊）
    private void setChatPane(UserJson userJson) {
        if (mainStatus.getTargetId().equals(userJson.username)) return;
        if (!cardPane.isVisible()) cardPane.setVisible(true);
        chatTitle.setText(userJson.username);
        chatTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UserInfo(userJson,MyFrame.this);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                chatTitle.setFont(GUI.base.font.createunderFont(18));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                chatTitle.setFont(GUI.base.font.createFont(18));
            }
        });
        chatTool.setVisible(false);
        var msg = new MessageJson();
        mainStatus.setTargetId(userJson.username);
        mainStatus.setClass(false);
        msg.sender = userJson.username;
        msg.receiver = mainStatus.getUserId();
        msg.type = 0;
        switchCardPane(CHAT_CARD);
        reloadRecord(msg);
    }

    // 设置聊天面板内容（群聊）
    private void setChatPane(ClassJson classJson) {
        if (mainStatus.getTargetId().equals(classJson.classid)) return;
        if (!cardPane.isVisible())
        {
            chatTitleBar.setVisible(true);
            cardPane.setVisible(true);
            System.out.println("显示聊天页面");

        }
        chatTitle.setText(classJson.gradeIndex + "级" + classJson.classIndex + "班");
        chatTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {

                chatTitle.setFont(GUI.base.font.createunderFont(18));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                chatTitle.setFont(GUI.base.font.createFont(18));
            }
        });
        chatTool.setVisible(true);
        var msg = new MessageJson();
        mainStatus.setTargetId(classJson.classid);
        mainStatus.setClass(true);
        msg.receiver = classJson.classid;
        msg.type = 1;
        var json = new ClassJson();
        json.classid = mainStatus.getTargetId();
        var members = Post.getInstance().getMembers(json);
        for (var i : members) {
            if (i.userId.equals(mainStatus.getUserId())) {
                if (i.type <= 1) {
                    mainStatus.setAdmin(true);
                } else {
                    mainStatus.setAdmin(false);
                }
                break;
            }
        }
        switchCardPane(CHAT_CARD);
        reloadRecord(msg);
    }
    //保存聊天记录
    private void saveRecord(String id, List<MessageJson> list) {
        try {
            FileClient.createDirs(Constant.getRecordPath(mainStatus.getUserId()));
            var path = Constant.getRecordPath(mainStatus.getUserId(), id);
            var file = new File(path);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    new JDialog(this,"聊天记录文件创建失败");
                }
            }
            var fout = new FileOutputStream(file, true);
            var writer = new PrintWriter(fout);
            for (var i : list) {
                writer.println(i.toString());
            }
            writer.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    JPanel noticeCard =new JPanel();
    public void updateNotice(String classId) {
        var list = Post.getInstance().getNoticeList(classId);
        if (list == null) return;
        noticeCard.removeAll();
        for (var i : list) {
            var pane = createNotice(i);
            noticeCard.add(pane);
        }
        noticeCard.revalidate();
        noticeCard.repaint();
    }

    private JPanel createEditorPane(String html) {
        var pane = new JPanel();
        pane.setBackground(new Color(0xF4F6F7));
        var webview = new CTextPane();
        webview.setFont(GUI.base.font.font);
        var webScrollPane = new JScrollPane(webview);
        webScrollPane.setPreferredSize(new Dimension(noticeWidth, noticeHeight-20));
        webScrollPane.setBorder(null);
        webScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        webScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        webScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        webScrollPane.getVerticalScrollBar().setUnitIncrement(15);
        webScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        webview.setText(html);
        pane.setBorder(null);
        pane.add(webScrollPane);
        return pane;
    }

    private JPanel createNotice(NoticeJson notice) {
        var pane = new JPanel(new BorderLayout());
        pane.setPreferredSize(new Dimension(paneWidth, noticeHeight));
        pane.setMaximumSize(new Dimension(paneWidth, noticeHeight));
        var noicetitle=new JLabel(notice.title,JLabel.CENTER);
        noicetitle.setFont(GUI.base.font.createunderFont(20));
        pane.add(noicetitle,BorderLayout.NORTH);
        pane.add(createEditorPane(notice.text), BorderLayout.CENTER);

        var infoPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        var sender = Post.getInstance().getUserInfo(notice.sender);
        infoPane.add(new CLabel( sender.username+" 发表于 " ));
        infoPane.add(new CLabel( LocalDateTime.ofInstant(Instant.ofEpochMilli(notice.time),
                TimeZone.getDefault().toZoneId()).format(DateTimeFormatter.ofPattern("MM-dd HH:mm")) + "  "));
        infoPane.setBackground(new Color(0xF0F3F4));
        infoPane.setBorder(new EmptyBorder(0, 120, 0, 120));
        pane.add(infoPane, BorderLayout.SOUTH);
        return pane;
    }
    private void initNoticeCard() {
        var publish = new CButton("发布新公告");
        var publishPane = new CPanel(new FlowLayout(FlowLayout.RIGHT));
        publishPane.setBackground(new Color(0xECF0F1));
        publish.addActionListener(e-> {
//            // 判断是否是管理员
            if (mainStatus.isAdmin()) {
                new PublishNotice(MyFrame.this);

            } else {
                JOptionPane.showMessageDialog(this, "只有管理员才有权限发布公告，您可以申请权限。");
            }
        });

        publishPane.add(publish);
        var pane = new CPanel(new BorderLayout());
        noticeCard = new CPanel();
        noticeCard.setBackground(new Color(0xF4F6F7));
        noticeCard.setLayout(new BoxLayout(noticeCard, BoxLayout.Y_AXIS));
        var scrollPane = new JScrollPane(noticeCard);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scrollPane.setBackground(new Color(0xF4F6F7));
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(publishPane, BorderLayout.NORTH);
        cardPane.add(pane, NOTICE_CARD);
    }

    private void showImportant(MessageJson message, String title) {
        if (message.type == 3) {
            // 有新公告
            new MessageDialog(this, title + "有一条新公告", "重要！");
            updateNotice(mainStatus.getTargetId());
        } else if (message.type == 4) {
            // 有新投票
            new MessageDialog(this, title + "有新投票等待审议", "重要！");
            updateVote(mainStatus.getTargetId());
        } else if (message.type == 5) {
            // 有新投票
            new MessageDialog(this, title + "有投票正在进行", "重要！");
            updateVote(mainStatus.getTargetId());
        }
    }
    public void ReceiveMessage(MessageJson message) {
        // 收到服务器发送的消息
        if(message.text!=null&&message.text.charAt(0)=='$') Shake();

        if (message.type == 0) {
            //私聊
            // 获取真正的接收者，如果发送者是自己的话 那接收者就是消息的接收者，否则是消息发送者
            var receiver = mainStatus.getUserId().equals(message.sender) ? message.receiver : message.sender;
            if(mainStatus.getUserId().equals(message.receiver))
            {
                Tipflag=true;TipMusic.play();
            }
            var user = new UserJson();
            user.setUsername(receiver);
            var chatBar = addAndGetChatBar(Post.getInstance().getUserInfo(user));
            var list = new ArrayList<MessageJson>();
            list.add(message);
            // 1.保存聊天记录
            saveRecord(receiver, list);
            // 判断当前聊天窗口是否是同一个
            if (mainStatus.getTargetId().equals(receiver)) {
                // 当前正是和这条消息中的人聊天
                // 把消息添加到当前聊天窗口
                addChatBubble(list, true);
                chatPane.revalidate();
                Rectangle rect = new Rectangle(0,chatPane.getPreferredSize().height,10,10);
                chatPane.scrollRectToVisible(rect);
            } else {
                // 添加新消息提示
                chatBar.showCount(true);
            }
        } else {
            // 群聊
            if(!mainStatus.getUserId().equals(message.sender))
            {
                Tipflag=true;TipMusic.play();
            }
            var receiver = message.receiver;
            var json = new ClassJson();
            json.classid = receiver;
            var chatBar = addAndGetChatBar(json); // 将班级添加到列表中
            if (mainStatus.getTargetId().equals(receiver)) {
                // 获取群消息，然后添加到面板
                var list = getClassMessage(createClassChatMessageJson());
                saveRecord(receiver, list);
                addChatBubble(list, true);
                chatPane.revalidate();
                //chatPane.repaint();
                Rectangle rect = new Rectangle(0,chatPane.getPreferredSize().height,10,10);
                chatPane.scrollRectToVisible(rect);
            } else {
                // 添加新消息提示
                chatBar.showCount(true);
            }
            if (message.sender.equals(mainStatus.getUserId())) return;

            showImportant(message, chatBar.getTitle());
        }
    }
    /**
     * 创建聊天气泡
     * @param message 聊天内容
     * @return 创建的聊天气泡
     */
    private ChatBubble createChatBubble(MessageJson message) {
        var bubble = new ChatBubble(!message.sender.equals(mainStatus.getUserId()));
        bubble.setBackground(new Color(bubble.isLeft() ? 0xECF0F1 : 0x5DADE2));
        bubble.setForeground(new Color(bubble.isLeft() ? 0x424949 : 0xFFFFFF));

        switch (message.type) {
            case 0:
                bubble.isClass=false;
                break;
            case 1:
                bubble.isClass=true;
                break;
            case 3:
                bubble.insertText("我发布了一条新公告，请前往查看。\n");
                break;
            case 4:
                bubble.insertText("我发布了一个新投票审议。\n");
                break;
            case 5:
                bubble.insertText("我发布了一个新投票。\n");
                break;
            case 6:
                bubble.insertText("投票已完成：\n");
        }
        // 解析消息
        var text = message.text;
        // 移除多余空行
        text = text.replaceAll("(?m)^\\s+$", "");
        var m = Pattern.compile("(?<!\\\\)<([^<>]*)(?<!\\\\)>").matcher(text);
        while (m.find()) {
            var path = Constant.getFileCachePath();
            var file = new File(Constant.getFileCachePath(m.group(1)));
            if (!file.exists()) FileClient.getInstance().download(Integer.parseInt(m.group(1)), path, true);
            var result = new StringBuffer();
            m.appendReplacement(result, "");
            bubble.insertText(result.toString());
            var img = new ImageIcon(Constant.getFileCachePath(m.group(1)));
            ImageUtil.scaleImage(img, paneWidth / 2);
            // 图片自动换行显示
            bubble.insertText("\n");
            bubble.insertIcon(img);
        }
        var result = new StringBuffer();
        m.appendTail(result);
        bubble.insertText(result.toString());

        return bubble;
    }
    //窗口抖动
    private void Shake()
    {
        var x=MyFrame.this.getX();
        var y=MyFrame.this.getY();
        for(int i=0;i<10;i++)
        {
            if((i&1)==0) { x+=3; y+=3; }
            else { x-=3;y-=3; }
            MyFrame.this.setLocation(x,y);
            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    public void keyPressed(KeyEvent e) {
        //组合键x`
        System.out.println("组合键");
        if (MyFrame.this.isVisible() == true && e.isAltDown() && e.getKeyCode() == KeyEvent.VK_X) {

            MyFrame.this.setVisible(false);
        }
        else if (MyFrame.this.isVisible() == false && e.isAltDown() && e.getKeyCode() == KeyEvent.VK_X) {

            MyFrame.this.setVisible(true);
        }
    }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
    private void initLister()
    {

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
        sendpicture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                sendpicture.setForeground(Color.black);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                sendpicture.setForeground(Color.red);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("选择一张图片", "jpg", "png","gif");
                fileChooser.setFileFilter(filter);
                int rtn = fileChooser.showOpenDialog(null);
                if(rtn == JFileChooser.APPROVE_OPTION) {
                    var filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    ImageIcon img = new ImageIcon(filePath);
                    ImageUtil.scaleImage(img, 80);
                    msgInput.insertIcon(img);
                }
            }
        });
        emoticon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                emoticon.setBorder(BorderFactory.createEmptyBorder());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                emoticon.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (null == image) {
                    image = new Emoticon(MyFrame.this);
                    image.setVisible(true);
                    // 设置控件相对于父窗体的位置
                    Point loc = getLocationOnScreen();
                    image.setBounds(loc.x + 100, loc.y + 200, 350, 300);
                }
                image.requestFocus();
            }
        });
        screenshot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenShot rd =new ScreenShot(MyFrame.this);

            }
        });
    }
}
class MyDialog extends JDialog implements ActionListener{
    private JButton _ok =new JButton("YES");//定义按键文本
    private MemberJson memberJson;
    public MyDialog(JFrame x, boolean modal,MemberJson memberJson, String title)
    {
        super(x,title,modal);
        this.memberJson=memberJson;
        init();
    }
    void init()
    {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//关闭时隐藏窗口
        setSize(300,200);
        setLocationRelativeTo(null);
        _ok.addActionListener(e -> {
            Post.getInstance().setAdmin(memberJson);
        });
        add(_ok, BorderLayout.SOUTH);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        dispose();//关闭窗体并释放资源
    }
}