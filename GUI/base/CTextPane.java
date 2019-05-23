package GUI.base;



import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 文本编辑器
 **/
public class CTextPane extends JTextPane {
    public CTextPane() {
        initView();
    }

    protected ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    private void initView() {
        setFont(font.font);
    }

    private List<ImageIcon> images = new ArrayList<>();
    private void parse() {
        images.clear();
        var root = getStyledDocument().getRootElements()[0];
        var n = root.getElementCount();
        for (var i = 0; i < n; i++) {
            var e = root.getElement(i);
            for (var j = 0; j < e.getElementCount(); j++) {
                var icon = (ImageIcon) StyleConstants.getIcon(e.getElement(j).getAttributes());
                if (icon != null) {
                    images.add(icon);
                }
            }
        }
    }

    public String getMessage() {

        parse();
        var msg = new StringBuilder();
        var k = 0;
        for (var i = 0; i < getStyledDocument().getLength(); i++) {
            var ele = getStyledDocument().getCharacterElement(i);
            if (ele.getName().equals("icon")) {
                msg.append("<").append(images.get(k++)).append(">");
            } else {
                try {
                    var ch = getStyledDocument().getText(i, 1);
                    // 转义
                    if (ch.equals("<") || ch.equals(">") || ch.equals("\\")) {
                        msg.append("\\");
                    }
                    msg.append(ch);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return msg.toString();
    }
}
