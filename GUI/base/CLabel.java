package GUI.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;

/**
 * @description: 标签控件
 **/
public class CLabel extends JLabel {
    private Duration animationTime = Duration.ofMillis(200);

    private Color textColor = Color.WHITE;
    private Color normalColor = new Color(0x515A5A);
    private Color hoverColor = new Color(0x616A6B);
    private Color pressColor = new Color(0x424949);

    private boolean isPressed = false;
    private boolean isRollover = false;

    protected ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    public void addActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public CLabel() { }
    public void addListener()
    {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isRollover = true;
                setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isRollover = false;
                setBorder(BorderFactory.createEmptyBorder());

            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                // setTextColor();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                //setTextColor();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                actionListener.actionPerformed(new ActionEvent(CLabel.this, ActionEvent.ACTION_PERFORMED, CLabel.this.getText()));
            }
        });
    }
    public CLabel(String text) {
        super(text);
        setFont(font.font);



    }
}
