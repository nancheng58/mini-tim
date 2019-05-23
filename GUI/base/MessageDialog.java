package GUI.base;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class MessageDialog extends JDialog {
    public MessageDialog(Frame parent, String msg, String title) {
        super(parent, title, true);
        initView(msg);
    }

    public MessageDialog(Dialog parent, String msg, String title) {
        super(parent, title, true);
        initView(msg);
    }

    private void initView(String msg) {
        setResizable(false);
        //setMinimumSize(new Dimension(200, 100));
        var panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0xFDFEFE));
        var label = new JLabel(msg);
        label.setFont(font.createFont(15));

        panel.add(label);

        JPanel button = new JPanel();
        button.setBackground(new Color(0xFDFEFE));
        button.setBorder(new EmptyBorder(20, 0, 10, 0));
        CButton confirm = new CButton("确定");
        //confirm.setRadius(7);
        confirm.setPreferredSize(new Dimension(80, 30));
        confirm.addActionListener(e->dispose());
        button.add(confirm);

        panel.add(button);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

