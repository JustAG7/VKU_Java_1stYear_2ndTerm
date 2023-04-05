import java.applet.Applet;
import java.awt.*;
import java.util.Date;

public class ThreadSix extends Applet implements Runnable {

    private Date toDay = new Date();

    private Thread th = null;

    public void start() {
        if (th == null) {
            th = new Thread(this);

            th.start();
        }
    }

    public void run() {

        while (true) {
            toDay = new Date();
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paint(Graphics g) {
        g.drawString("Tel Aviv: " + toDay.toString(), 50, 100);
        toDay.setTime(toDay.getTime() - 7 * 3600000);
        g.drawString("New York: " + toDay, 50, 150);
        toDay.setTime(toDay.getTime() + 7 * 3600000);
        g.drawString("Hanoi: " + toDay, 50, 200);
    }
    public static void main(String[] args) {
        ThreadSix m = new ThreadSix();
        Frame f = new Frame("Time");
        f.add(m);
        f.setVisible(true);

        f.setSize(300, 300);
        m.init();
        m.start();
    }
}