import java.applet.*;
import java.awt.*;

public class Exs2 extends Applet implements Runnable {
    String message = "MultiThread programming";
    Thread thread;
    int x, direction;

    public void init() {
        x = getWidth();
        direction = -1;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            x += direction;
            if (x < -message.length() ) {
                direction = 1;
            } else if (x > getWidth()) {
                direction = -1;
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        g.drawString(message, x, 50);
    }

    public static void main(String[] args) {
        Exs2 applet = new Exs2();
        Frame frame = new Frame();
        frame.add(applet);
        applet.init();
        applet.start();
        frame.setSize(300, 300);
        frame.setVisible(true);

    }
}
