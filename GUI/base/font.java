package GUI.base;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class font {
    public static Font font = new Font("微软雅黑", Font.PLAIN, 13);
    public static HashMap<TextAttribute,Object> hm = new HashMap();
    public static Font createFont(int size) {
        return new Font("微软雅黑", Font.PLAIN, size);
    }
    public static Font createunderFont(int size)
    {
        hm.put(TextAttribute.FAMILY,"微软雅黑");//字体名称
        hm.put(TextAttribute.SIZE, size);//字体大小
        hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        return new Font(hm);
    }
}

