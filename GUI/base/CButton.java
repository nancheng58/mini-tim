package GUI.base;

import util.PictureUtil;
import util.RendererUtil;

import javax.swing.*;
import java.awt.*;

public class CButton extends JButton {
    private boolean showCount = false;
    private int countSize = 14;
    private int margin = 12;
    private double radiusWidth = 0.0, radiusHeight=7.0;

    public void setShowCount(boolean showCount) {
        this.showCount = showCount;
        getParent().repaint();
    }

    public boolean isShowCount() {
        return showCount;
    }

    public CButton(String text) {
        super(text);
    }
    //消息提醒
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!showCount) return;
        ImageIcon icon = PictureUtil.getPicture("111.png");
        icon.setImage(icon.getImage().getScaledInstance(countSize, countSize, Image.SCALE_DEFAULT));
        g.drawImage(icon.getImage(), getWidth() - countSize - margin, getHeight() / 2 - countSize / 2, null);
    }
}
