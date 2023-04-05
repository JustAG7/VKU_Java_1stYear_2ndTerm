import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadNine extends Applet implements Runnable,ActionListener{
    Thread t;
    int x=34,y=14;
    int dx=5,dy=5;
    Button start,stop;
    String status = "Go";
    public void init(){
        start = new Button("Start");
        stop = new Button("Stop");
        add(start);
        add(stop);
        start.addActionListener(this);
        stop.addActionListener(this);
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run(){
        while(true){
            if(status.equals("Stop")){
                synchronized (t){
                    try{
                        t.wait();
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
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

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==start){
            status = "Go";
            synchronized (t){
                t.notify();
            }
        }
        if(e.getSource()==stop){
            status = "Stop";
        }
    }

    public static void main(String[] args) {
        ThreadNine applet = new ThreadNine();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("ThreadNine");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

}
