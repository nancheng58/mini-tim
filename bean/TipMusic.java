package bean;
import javax.media.Manager;
import javax.media.Player;
import java.io.File;

public class TipMusic {

    public static void play() {
        File file = new File("E:\\keshe\\src\\util\\resource\\music\\msg.wav");
        try {
            Player player = Manager.createPlayer(file.toURL());
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}