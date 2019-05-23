package bean;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.Serializable;
import java.net.URL;

import javax.swing.*;


import GUI.MyFrame;

public class Emoticon extends JDialog implements  Serializable {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JScrollPane jScrollPane1;

    private JLabel label[] = new JLabel[105];
    private String marks[] = new String[label.length];

    private MyFrame chatPane;
    private String fn[] = new String[label.length];
    public Emoticon(MyFrame chatPane) {
        this.chatPane = chatPane;
        initGUI();
        init();
    }

    private void initGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 300);
        setUndecorated(true);
        setAlwaysOnTop(true);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new GridLayout(12, 0));

        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(contentPane);
        getContentPane().add(jScrollPane1, BorderLayout.CENTER);
//        jScrollPane1.getVerticalScrollBar().setUI(new JScrollBarUI());
        // 屏蔽横向滚动条
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        URL url=null;
        String fileName = "";
        for (int i = 0; i < label.length; i++) {
            fileName= Constant.getFaceReceivePath()+"\\"+i+ ".gif";
            label[i] = new JLabel(new ImageIcon(fileName,fileName), SwingConstants.CENTER);
            label[i].setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
            label[i].setToolTipText(":)" + i);
            marks[i] = ":)" + i;
            label[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JLabel temp = (JLabel) e.getSource();
                    temp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    JLabel temp = (JLabel) e.getSource();
                    temp.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel temp = (JLabel) e.getSource();
                    ImageIcon pic = (ImageIcon)temp.getIcon();
                    chatPane.insertIcon(pic);
                    dispose();
                    chatPane.image = null;
                }
            });
            contentPane.add(label[i]);
        }
    }
    public void init()
    {
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                //super.focusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                setVisible(false);
                dispose();
                chatPane.image = null;
            }
        });
    }

}
