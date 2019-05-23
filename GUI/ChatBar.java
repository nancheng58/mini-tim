package GUI;

import GUI.base.CButton;
import GUI.base.CPanel;
import util.ImageUtil;
import GUI.base.font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @description: 创建消息列表
 **/
public class ChatBar {
    private int type = 0;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    private String id;

    public String getId() {
        return id;
    }

    private CPanel myGroup;
    private CButton myGroupButton;
    public void addActionListener(ActionListener listener) {
        myGroupButton.addActionListener(listener);
    }

    private String title;
    public String getTitle() {
        return title;
    }
    public ChatBar(String id, String title, ImageIcon avatar, int type, int asize, int gwidth, int gheight) {
        this.id = id;
        this.title = title;
        //消息列表设置
        myGroup = new CPanel();
        myGroup.setPreferredSize(new Dimension(gwidth, gheight));
        myGroup.setMaximumSize(new Dimension(gwidth, gheight));
        myGroup.setBackground(new Color(0xFDFEFE));
        myGroupButton = new CButton(title);
        myGroupButton.setFocusPainted(false);myGroupButton.setBackground(Color.BLACK);
        myGroupButton.setContentAreaFilled(false);
        myGroupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e)
            {
                 myGroupButton.setBackground(Color.BLACK);
            }
        });
        myGroupButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xD0D3D4)));
        myGroupButton.setFont(font.createFont(14));
        myGroupButton.setHorizontalAlignment(SwingConstants.LEFT);
        this.type = type;
        if (avatar == null) avatar = ImageUtil.getRoundImageIcon(new ImageIcon("images/avatar.jpg").getImage());
        avatar.setImage(avatar.getImage().getScaledInstance(asize, asize, Image.SCALE_DEFAULT));
        myGroupButton.setIcon(avatar);
        myGroupButton.setPreferredSize(new Dimension(gwidth, gheight));
        myGroup.add(myGroupButton);
    }
    public CPanel getPane() {
        return  myGroup;
    }
    public JButton getArea() {
        return myGroupButton;
    }
    public void showCount(boolean show) {
        myGroupButton.setShowCount(show);
    }
    public boolean isShowCount() {
        return myGroupButton.isShowCount();
    }
}