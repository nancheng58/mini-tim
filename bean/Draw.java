package bean;
import GUI.MyFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Draw extends JPanel implements MouseMotionListener{
    int xVal=0,yVal=0;
    boolean firstTime=true;
    public static MyFrame myFrame;
    JFrame frame;
    public Draw(MyFrame myFrame)
    {
        this.myFrame=myFrame;
        goes();
    }
    public  void goes() {

        Draw demo = this;
        demo.go();
    }

    public void go(){
        frame=new JFrame();

        frame.setLocation(550,200);
        frame.setSize(500,500);
        frame.setTitle("绘图板");

        frame.setContentPane(this);
//        frame.setBackground(Color.white);


        addMouseMotionListener(this);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {

            //按键释放

            public void keyReleased(KeyEvent e) {

                //按Esc键退出
                if (e.getKeyCode()==KeyEvent.VK_ENTER ) {
                    myFrame.insertIcon(catchpicture());
                    frame.dispose();
                }
            }
        });
    }

    public void paintComponent(Graphics g){
        if(!firstTime)g.fillOval(xVal,yVal,4,4);
        else firstTime=false;
    }

    public void mouseDragged(MouseEvent e){
        xVal=e.getX();
        yVal=e.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent e){  }
    public ImageIcon catchpicture()
    {
        try {

            Robot robot=new Robot();

            //根据指定的区域抓取屏幕的指定区域，1300是长度，800是宽度。

            BufferedImage bi=robot.createScreenCapture(new Rectangle(frame.getX(),frame.getY()+20,450,450));

            //把抓取到的内容写到一个jpg文件中
            SimpleDateFormat sdf=new SimpleDateFormat("yyyymmddHHmmss");
            String name=sdf.format(new Date());
            String format="jpg";
            String path=Constant.getScreenShotPath()+"\\"+name+"."+format;
//            System.out.println(path+name+"."+format);
            ImageIO.write(bi, "jpg", new File(path));
            var imageIcon=new ImageIcon(path);
            imageIcon.setImage(imageIcon.getImage().
                    getScaledInstance(imageIcon.getIconWidth()/4,imageIcon.getIconHeight()/4,Image.SCALE_FAST));
            return imageIcon;
        } catch (AWTException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }
}
