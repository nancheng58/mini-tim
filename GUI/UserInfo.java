package GUI;

import GUI.base.CLabel;
import GUI.base.CPanel;
import net.Json.UserJson;
import util.PictureUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInfo extends JFrame {
    UserJson userJson;
    CPanel panel=new CPanel();
    CPanel paneln=new CPanel();
    CPanel panels=new CPanel();
    private CLabel exitButton = new CLabel();
    private JLabel name ;
    private JLabel score;
    MyFrame main;
    public UserInfo(UserJson userJson,MyFrame main)
    {
        this.main=main;
        this.userJson=userJson;
        init();
    }
    private void init()
    {

        setUndecorated(true);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        paneln.setLayout(new BoxLayout(paneln, BoxLayout.X_AXIS));

        JLabel picture = new JLabel();
        ImageIcon image = main.getAvatar(userJson.avatar);
        name=new JLabel("ID: "+userJson.username,JLabel.CENTER);
        name.setFont(GUI.base.font.createFont(25));
        image.setImage(image.getImage().getScaledInstance(300, 200, Image.SCALE_FAST));
        picture.setIcon(image);
        picture.setOpaque(false);
        score=new JLabel("2048 Sorce:"+((Integer)userJson.gamescore).toString(),JLabel.CENTER);
        score.setFont(GUI.base.font.createFont(25));
//        paneln.setLayout(null);
//        exitButton.setBounds(269, 0, 31, 20);
        exitButton.setIcon(PictureUtil.getPicture("close.png"));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
//        picture.setBounds(21, 130, 90, 90);
        picture.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.add(picture,BorderLayout.EAST);
        paneln.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paneln.add(exitButton);
        panels.setLayout(new BoxLayout(panels,BoxLayout.Y_AXIS));
        panels.add(name);
        panels.add(score);
        add(paneln,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        add(panels,BorderLayout.SOUTH);
        setVisible(true);
    }
}
