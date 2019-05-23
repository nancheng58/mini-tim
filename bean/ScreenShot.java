package bean;

import GUI.MyFrame;
import GUI.base.CLabel;
import GUI.base.CPanel;
import GUI.base.font;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class ScreenShot extends JFrame{

    private static final long serialVersionUID = 1L;
    int orgx,orgy,endx,endy;
    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
    BufferedImage image;
    BufferedImage tempImage;
    BufferedImage saveImage;
    CLabel tip=new CLabel();
    CPanel panel=new CPanel();
    ImageIcon imageIcon;
    MyFrame chatPane;
    Graphics g;


    public void paint(Graphics g) {
        //缩放因子和偏移量
        RescaleOp ro=new RescaleOp(0.8f, 0, null);
        tempImage=ro.filter(image, null);
        g.drawImage(tempImage, 0, 0,this);
    }

    public ScreenShot(MyFrame chatPane1){
        this.chatPane=chatPane1;


        setUndecorated(true);
        this.snapshot();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tip.setText("按Enter键以截图");
        tip.setFont(GUI.base.font.createFont(18));
        panel.add(tip);
        this.add(panel, BorderLayout.SOUTH);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                orgx=e.getX();
                orgy=e.getY();
            }
        });
        //鼠标运动监听器
        this.addMouseMotionListener(new MouseMotionAdapter() {
            //鼠标拖拽事件
            public void mouseDragged(MouseEvent e) {
                endx=e.getX();
                endy=e.getY();
                g=getGraphics();
                g.drawImage(tempImage, 0, 0, ScreenShot.this);
                int x=Math.min(orgx, endx);
                int y=Math.min(orgy,endy);
                //加上1，防止width,height为0
                int width=Math.abs(endx-orgx)+1;
                int height=Math.abs(endy-orgy)+1;
                g.setColor(Color.BLUE);
                g.drawRect(x-1, y-1, width+1, height+1);
                //减1，加1都是为了防止图片将矩形框覆盖掉
                saveImage=image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y, ScreenShot.this);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            //按键释放
            public void keyReleased(KeyEvent e){
                //按Enter键截屏
                if(e.getKeyCode()==10){
                    saveToFile();
                    imageIcon.setImage(imageIcon.getImage().
                            getScaledInstance(imageIcon.getIconWidth()/4,imageIcon.getIconHeight()/4,Image.SCALE_FAST));
                    chatPane.insertIcon(imageIcon);
                    dispose();
                }
                //按Esc键退出
                if(e.getKeyCode()==27) dispose();
            }
        });
    }
    public void saveToFile(){//保存图片
        SimpleDateFormat sdf=new SimpleDateFormat("yyyymmddHHmmss");
        String name=sdf.format(new Date());
        String format="jpg";
        String path=Constant.getScreenShotPath()+"\\"+name+"."+format;
        System.out.println(path+name+"."+format);
        File f=new File(path);
        try {
            ImageIO.write(saveImage, format, f);
            imageIcon=new ImageIcon(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void snapshot(){//调用系统截屏
        try {
            Robot robot= new Robot();
            Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
            image=robot.createScreenCapture(new Rectangle(0,0,d.width,d.height));

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public ImageIcon getImageIcon()
    {
        return imageIcon;
    }
}