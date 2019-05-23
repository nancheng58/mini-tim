package bean;

/**
 * @author Administrator
 *
 */
import net.Client.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * @author Administrator
 * 2048小游戏
 *
 */
public class Game2048 extends JApplet{
    private static final int UP=0,DOWN=1,LEFT=2,RIGHT=3;
    private static final int WID=150,SP=10;
    private int scores=0;
    private static User mainStatus;
    private static JLabel scoreLabel;
    private static JFrame frame;
    private boolean change,CheckMode=false,gameOverFlag=false,successFlag=false;
    private int[] label={2,4,8,16,32,64,128};
    private Color[] clo={
            new Color(200,200,200),new Color(228,228,160),new Color(214,163,92),
            new Color(234,124,72),new Color(240,84,77),new Color(255,68,53),new Color(200,200,64)
    };
    private static JLabel jl1;
    private Map cmap=new HashMap();
    private static int best;
    public static RectObject[][] rset=new RectObject[4][4];
    public RectObject[][] list=new RectObject[4][4];
    private My2048Panel myp;
    private LinkedList saveList=new LinkedList();
    private JButton goBackButton;

    KeyListener kl=new KeyListener(){
        public void keyPressed(KeyEvent e){
            saveTheStep();
            goBackButton.setVisible(true);
            if(gameOverFlag==true){
                return;
            }
            if(!aDirAble()){
                gameOver();
            }
            int key=e.getKeyCode();
            switch(key){
                case KeyEvent.VK_UP:
                    change=false;
                    moveUp(true);
                    if(change==true){
                        getARandomRect();
                        //saveTheStep();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    change=false;
                    moveDown(true);
                    if(change==true){
                        getARandomRect();
                        //saveTheStep();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    change=false;
                    moveLeft(true);
                    if(change==true){
                        getARandomRect();
                        //saveTheStep();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    change=false;
                    moveRight(true);
                    if(change==true){
                        getARandomRect();
                        //saveTheStep();
                    }
                    break;
            }
            //saveTheStep();
        }
        public void keyTyped(KeyEvent e){}
        public void keyReleased(KeyEvent e){}
    };

    class RectObject{
        private int value;
        public RectObject(){
            value=0;
        }
        public RectObject(RectObject obj){
            value=obj.value;
        }
        public boolean equals(Object inobj){
            RectObject obj=(RectObject)inobj;
            if(obj.value==value){
                return true;
            }
            return false;
        }
    }
    class Point{
        int x;
        int y;
        public Point(int i,int j){
            x=i;
            y=j;
        }
    }

    class My2048Panel extends JPanel{
        private int[] xindex={SP,2*SP+WID,3*SP+2*WID,4*SP+3*WID};
        private int[] yindex={SP,2*SP+WID,3*SP+2*WID,4*SP+3*WID};

        public void paintComponent(Graphics g){
            //background
            super.paintComponent(g);
            for(int i=0;i<xindex.length;i++){
                for(int j=0;j<yindex.length;j++){
                    g.setColor(Color.WHITE);
                    g.drawRoundRect(xindex[i], yindex[j], WID, WID, WID/5, WID/5);
                    g.setColor(new Color(197,183,129));
                    g.fillRoundRect(xindex[i], yindex[j], WID, WID, WID/5, WID/5);
                }
            }
            //paint rectangle
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(rset[i][j]!=null){
                        g.setColor(Color.WHITE);
                        g.drawRoundRect(yindex[j], xindex[i], WID, WID, WID/5, WID/5);
                        if(rset[i][j].value<128){
                            g.setColor((Color)cmap.get(rset[i][j].value));
                        }else{
                            g.setColor((Color)cmap.get(128));
                        }
                        g.fillRoundRect(yindex[j], xindex[i], WID, WID, WID/5, WID/5);
                        g.setColor(Color.BLACK);

                        Font font=new Font("TimesRoman",Font.BOLD,50);
                        g.setFont(font);
                        FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(font);
                        int len=fm.stringWidth(""+rset[i][j].value);
                        int hg=fm.getHeight();
                        g.drawString(""+rset[i][j].value, yindex[j]+WID/2-len/2, xindex[i]+WID/2+hg/4);

                        if(rset[i][j].value==2048 && successFlag==false){
                            successFlag=true;
                            gameSuccess();
                        }
                    }
                }
            }
        }
    }

    class GameOverPane extends JPanel{
        public GameOverPane(int w,int h){
            setSize(w,h);
            //setOpaque(false);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Font font=new Font("TimesRoman",Font.BOLD,80);
            g.setFont(font);
            FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(font);
            int width=fm.stringWidth("Game2048 Over");
            int height=fm.getHeight();
            g.setColor(new Color(255,0,0));
            g.drawString("Game2048 Over!", getWidth()/2-width/2, getHeight()/2-height/2);
        }
    }

    class SuccessPane extends JPanel{
        public SuccessPane(int w,int h){
            setSize(w,h);
            //setOpaque(false);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Font font=new Font("TimesRoman",Font.BOLD,80);
            g.setFont(font);
            FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(font);
            int width=fm.stringWidth("Success!");
            int height=fm.getHeight();
            g.setColor(new Color(255,0,0));
            g.drawString("Success!", getWidth()/2-width/2, getHeight()/2-height/2);
        }
    }

    class LOGO extends JPanel{
        public LOGO(int w ,int h){
            setSize(w,h);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Font font=new Font("TimesRoman",Font.BOLD,60);
            g.setFont(font);
            FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(font);
            int width=fm.stringWidth("2048");
            int height=fm.getHeight();
            g.setColor(new Color(255,0,0));
            g.drawString("2048", 20, getHeight()/2+20);
        }
    }

    public class goBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(saveList.size()==0){
                goBackButton.setVisible(false);
                return;
            }
            ArrayList arr=(ArrayList)saveList.getLast();
            scoreLabel.setText(""+arr.get(0));
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    int num=(int)arr.get(4*i+j+1);
                    if(num!=0){
                        rset[i][j]=new RectObject();
                        rset[i][j].value=num;
                    }else{
                        rset[i][j]=null;
                    }
                }
            }
            saveList.removeLast();
            repaint();
        }
    }

    public class resetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            refreshBest();
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    rset[i][j]=null;
                }
            }
            scoreLabel.setText("0");
            repaint();
            getARandomRect();
            getARandomRect();
        }
    }
    //the applet init
    public void init(){
        Container cp=getContentPane();
        cp.setLayout(null);
        cp.setFocusable(true);
        cp.addKeyListener(kl);
        cp.setSize(120,120);

        Font font=new Font("TimesNewman",Font.BOLD,30);
        JLabel sl=new JLabel();
        sl.setLayout(new GridLayout(2,1));
        JLabel sllb=new JLabel("Scores");
        sllb.setFont(font);
        scoreLabel=new JLabel("0");
        scoreLabel.setFont(font);
        sl.add(sllb);
        sl.add(scoreLabel);

        int best=0;
        var user= Post.getInstance().getUserInfo(mainStatus.getUserId());
        best=user.gamescore;

        JLabel bsl=new JLabel();
        bsl.setLayout(new GridLayout(2,1));
        JLabel jl=new JLabel("Best");
        jl.setFont(font);
        jl1=new JLabel(""+best);
        jl1.setFont(font);
        bsl.add(jl);
        bsl.add(jl1);

        myp=new My2048Panel();
        LOGO logo=new LOGO(0,0);

        goBackButton=new JButton("UNDO");
        goBackButton.setFont(font);
        goBackButton.addActionListener(new goBackListener());
        goBackButton.addKeyListener(kl);

        JButton jb=new JButton("RESET");
        jb.setFont(font);
        jb.addActionListener(new resetListener());
        jb.addKeyListener(kl);

        sl.setBounds(500,20,200,80);
        bsl.setBounds(300,20,200,80);
        logo.setBounds(0, 0, 600, 100);
        myp.setBounds(0,90,700,700);
        goBackButton.setBounds(700,250,150,60);
        jb.setBounds(700,450,150,60);

        cp.add(sl);
        cp.add(bsl);
        cp.add(logo);
        cp.add(myp);
        cp.add(goBackButton);
        cp.add(jb);

    }

    public Game2048(){
        //saveTheStep();
        for(int i=0;i<7;i++){
            cmap.put(label[i], clo[i]);
        }
    }
    //moveLeft
    public void moveLeft(boolean flag){
        clearList(list);
        for(int i=0;i<4;i++){
            int k=0;
            for(int j=0;j<4;j++){
                if(rset[i][j]!=null){
                    list[i][k++]=new RectObject(rset[i][j]);
                }
            }
        }
        for(int i=0;i<4 && flag;i++){
            for(int j=0;j<3;j++){
                if(list[i][j]!=null && list[i][j+1]!=null && list[i][j].value==list[i][j+1].value){
                    list[i][j].value*=2;
                    if(CheckMode==false){
                        int sum=Integer.parseInt(scoreLabel.getText());
                        sum+=list[i][j].value;
                        scoreLabel.setText(""+sum);
                    }
                    list[i][j+1]=null;
                    j++;
                }
            }
        }

        if(isChange()){
            if(CheckMode==false){
                copySet(rset,list);
                repaint();
                moveLeft(false);
            }
            change=true;
        }else{
            repaint();
        }
    }
    //moveRight
    public void moveRight(boolean flag){
        clearList(list);
        for(int i=0;i<4;i++){
            int k=3;
            for(int j=3;j>-1;j--){
                if(rset[i][j]!=null){
                    list[i][k--]=new RectObject(rset[i][j]);
                }
            }
        }
        for(int i=0;i<4 && flag;i++){
            for(int j=3;j>0;j--){
                if(list[i][j]!=null && list[i][j-1]!=null && list[i][j].value==list[i][j-1].value){
                    list[i][j].value*=2;
                    if(CheckMode==false){
                        int sum=Integer.parseInt(scoreLabel.getText());
                        sum+=list[i][j].value;
                        scoreLabel.setText(""+sum);
                    }
                    list[i][j-1]=null;
                    j--;
                }
            }
        }

        if(isChange()){
            if(CheckMode==false){
                copySet(rset,list);
                repaint();
                moveRight(false);
            }
            change=true;
        }else{
            repaint();
        }
    }
    //moveup
    public void moveUp(boolean flag){
        clearList(list);
        for(int j=0;j<4;j++){
            int k=0;
            for(int i=0;i<4;i++){
                if(rset[i][j]!=null){
                    list[k++][j]=new RectObject(rset[i][j]);
                }
            }
        }
        for(int j=0;j<4 && flag;j++){
            for(int i=0;i<3;i++){
                if(list[i][j]!=null && list[i+1][j]!=null && list[i][j].value==list[i+1][j].value){
                    list[i][j].value*=2;
                    if(CheckMode==false){
                        int sum=Integer.parseInt(scoreLabel.getText());
                        sum+=list[i][j].value;
                        scoreLabel.setText(""+sum);
                    }
                    list[i+1][j]=null;
                    i++;
                }
            }
        }

        if(isChange()){
            if(CheckMode==false){
                copySet(rset,list);
                repaint();
                moveUp(false);
            }
            change=true;
        }else{
            repaint();
        }
    }
    //movedown
    public void moveDown(boolean flag){
        clearList(list);
        for(int j=0;j<4;j++){
            int k=3;
            for(int i=3;i>-1;i--){
                if(rset[i][j]!=null){
                    list[k--][j]=new RectObject(rset[i][j]);
                }
            }
        }
        for(int j=0;j<4 && flag;j++){
            for(int i=3;i>0;i--){
                if(list[i][j]!=null && list[i-1][j]!=null && list[i][j].value==list[i-1][j].value){
                    list[i][j].value*=2;
                    if(CheckMode==false){
                        int sum=Integer.parseInt(scoreLabel.getText());
                        sum+=list[i][j].value;
                        scoreLabel.setText(""+sum);
                    }
                    list[i-1][j]=null;
                    i--;
                }
            }
        }

        if(isChange()){
            if(CheckMode==false){
                copySet(rset,list);
                repaint();
                moveDown(false);
            }
            change=true;
        }else{
            repaint();
        }
    }
    //other functions
    private void copySet(RectObject[][] dst, RectObject[][] src){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                dst[i][j]=src[i][j];
            }
        }
    }
    //detect whether rset is different from list or not
    private boolean isChange(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(rset[i][j]!=null && list[i][j]!=null && !rset[i][j].equals(list[i][j])){
                    return true;
                }
                if(rset[i][j]!=null && list[i][j]==null){
                    return true;
                }
                if(rset[i][j]==null && list[i][j]!=null){
                    return true;
                }
            }
        }
        return false;
    }
    private void clearList(RectObject[][] s){
        for(int i=0;i<s.length;i++){
            for(int j=0;j<s[i].length;j++){
                s[i][j]=null;
            }
        }
    }
    //get a random rectangle
    public void getARandomRect(){
        ArrayList list=new ArrayList();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(rset[i][j]==null){
                    list.add(new Point(i,j));
                }
            }
        }
        if(list.size()==0 && !aDirAble()){
            gameOver();
            return;
        }
        Random rand=new Random();
        int index=rand.nextInt(list.size());
        Point loc=(Point)list.get(index);
        index=rand.nextInt(2);
        rset[loc.x][loc.y]=new RectObject();
        if(index==1){
            rset[loc.x][loc.y].value=4;
        }else{
            rset[loc.x][loc.y].value=2;
        }
    }
    //detect whether there are other steps or not
    public boolean aDirAble(){
        CheckMode=true;
        change=false;
        moveLeft(true);
        moveRight(true);
        moveDown(true);
        moveUp(true);
        CheckMode=false;
        if(change==true){
            return true;
        }else{
            return false;
        }
    }
    public void gameOver(){
        gameOverFlag=true;
        JPanel jl=new GameOverPane(myp.getWidth(),myp.getHeight());
        jl.setBounds(0, 0, 700, 700);
        JButton jb1=new JButton("Again");
        Font font=new Font("TimesRoman",Font.BOLD,30);
        jb1.setOpaque(false);
        jb1.setFont(font);
        JButton jb2=new JButton("Close");
        jb2.setSize(jb1.getSize());
        jb2.setOpaque(false);
        jb2.setFont(font);
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        rset[i][j]=null;
                    }
                }
                clearList(rset);
                myp.validate();
                repaint();
                getARandomRect();
                getARandomRect();
                gameOverFlag=false;
                refreshBest();
                scoreLabel.setText("0");
                myp.remove(jl);
                myp.repaint();
                myp.revalidate();
            }
        });
        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                refreshBest();
                frame.setVisible(false);
            }
        });
        jl.add(jb1);
        jl.add(jb2);
        myp.add(jl);
        jl.validate();

    }
    public void gameSuccess(){
        JPanel jl=new SuccessPane(myp.getWidth(),myp.getHeight());
        jl.setOpaque(false);
        jl.setBounds(0, 0, 700, 700);
        JButton jb1=new JButton("Continue");
        Font font=new Font("TimesRoman",Font.BOLD,30);
        jb1.setOpaque(false);
        jb1.setFont(font);
        JButton jb2=new JButton("Close");
        jb2.setSize(jb1.getSize());
        jb2.setOpaque(false);
        jb2.setFont(font);
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //myp.remove(jl);
                myp.validate();
                repaint();
            }
        });
        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                refreshBest();
                System.exit(0);
            }
        });
        jl.add(jb1);
        jl.add(jb2);
        myp.add(jl);
        jl.validate();
    }
    public void saveTheStep(){
        if(saveList.size()<20){
            ArrayList arr=new ArrayList();
            int score=Integer.parseInt(scoreLabel.getText());
            arr.add(score);
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(rset[i][j]!=null){
                        arr.add(rset[i][j].value);
                    }else{
                        arr.add(0);
                    }
                }
            }
            saveList.addLast(arr);
        }else{
            saveList.removeFirst();
            saveTheStep();
        }
    }

    public static String title(Object o){
        String t=o.getClass().toString();
        if(t.indexOf("class")!=-1){
            t=t.substring(6);
        }
        return t;
    }
    public static void refreshBest(){
         {

            var user= Post.getInstance().getUserInfo(mainStatus.getUserId());
            //System.out.println("The Best score is "+best);
            int cur=Integer.parseInt(scoreLabel.getText());
            if(cur>user.gamescore){
                user.gamescore=cur;best=cur;
                jl1.setText(""+best);
                var result=Post.getInstance().refreshRecord(user);
                if(result.code>=0) System.out.println("更新记录成功");
                else System.out.println("更新记录失败");
            }

        }
    }
    public static void saveRecord(){
        try{
            RandomAccessFile file=new RandomAccessFile(new File("LASTRECORD"),"rw");
            int score=Integer.parseInt(scoreLabel.getText());
            file.writeInt(score);
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(rset[i][j]!=null){
                        file.writeInt(rset[i][j].value);
                    }else{
                        file.writeInt(0);
                    }
                }
            }
        }catch(FileNotFoundException e){
            //e.printStackTrace();
            System.out.println("游戏结束");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void run(JApplet applet, int width, int height, User mainStatu){
        mainStatus=mainStatu;
        frame=new JFrame(title(applet));
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                refreshBest();
                saveRecord();
                //System.out.println("The score is "+scoreLabel.getText());
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(width,height);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
//    public static void main(String[] args){
//        run(new Game2048(), 900, 800);
//    }
}

