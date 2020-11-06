package util;
import javax.swing.ImageIcon;
import java.io.Serializable;

//图片地址
public class PictureUtil  implements Serializable {

    public static ImageIcon getPicture(String name) {
        ImageIcon icon = new ImageIcon(PictureUtil.class.getClassLoader()
                .getResource("util/resource/image/" + name));
        return icon;
    }

}
