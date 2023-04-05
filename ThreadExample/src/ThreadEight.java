import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class ThreadEight extends Applet implements Runnable{
    Thread t;
    int x=34,y=14;
    int dx=5,dy=5;
    public void init(){
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run(){
        while(true){
            if(x+dx>this.getWidth() || x+dx<0) dx=-dx;
            if(y+dy>this.getHeight() || y+dy<0) dy=-dy;
            x+=dx;
            y+=dy;
            repaint();
            try{
                Thread.sleep(10);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }
    public void paint(java.awt.Graphics g){
        g.fillOval(x, y, 40,40);
    }

    public static void main(String[] args) {
        ThreadEight applet = new ThreadEight();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("ThreadEight");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
