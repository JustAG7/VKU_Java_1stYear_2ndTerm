import javax.swing.*;
import java.awt.*;

public class ThreadSeven extends JApplet implements Runnable {
    String light = "green";
    public void init(){
        Thread t1 = new Thread(this);
        Thread t2 = new Thread(this);
        Thread t3 = new Thread(this);
        t1.setName("red");
        t2.setName("yellow");
        t3.setName("green");
        t1.start();
        t2.start();
        t3.start();

    }
    public void paint(Graphics g){
        if(light.equals("green")) g.setColor(Color.green);
        if(light.equals("yellow")) g.setColor(Color.yellow);
        if(light.equals("red")) g.setColor(Color.red);
        g.fillOval(100,100,50,50);
    }
    public void showLight(){
        light = Thread.currentThread().getName();
        repaint();
    }
    @Override
    public void run() {
        while(true){
            showLight();
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSeven applet = new ThreadSeven();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("ThreadSeven");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
