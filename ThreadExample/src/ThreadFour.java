import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
public class ThreadFour extends Frame{

    class HighThread extends Thread{
        private TextArea display;
        public HighThread(TextArea display){
            this.display = display;
            setPriority(Thread.MAX_PRIORITY);
        }
        public void run(){
            for(int i=1;i<=5;i++){
                display.append("High Priority Thread!!!\n");
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class LowThread extends Thread{
        private TextArea display;
        public LowThread(TextArea display){
            this.display = display;
            setPriority(Thread.MIN_PRIORITY);
        }
        public void run(){
            for(int i=1;i<=5;i++){
                display.append("Low Priority Thread!!!\n");
            }
        }
    }
    private HighThread high;
    private LowThread low;
    private TextArea output;
    public ThreadFour(){
        super("Thread Priority");
        output = new TextArea(10,20);
        add(output);
        setSize(250,200);
        setVisible(true);
        high = new HighThread(output);
        high.start();
        low = new LowThread(output);
        low.start();

    }

    public static void main(String[] args) {
        ThreadFour app = new ThreadFour();
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
}
