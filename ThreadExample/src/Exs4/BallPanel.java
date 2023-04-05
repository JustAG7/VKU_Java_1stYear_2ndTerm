package Exs4;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BallPanel extends JPanel implements Runnable {

    private ArrayList<Ball> balls;
    private Thread animator;

    public BallPanel() {
        setBackground(Color.WHITE);
        balls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 400);
            int y = (int) (Math.random() * 400);
            int diameter = 30;
            Color color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
            Ball ball = new Ball(x, y, diameter, color);
            balls.add(ball);
            JButton startButton = new JButton("Start");
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ball.setMoving(true);
                }
            });
            startButton.setBackground(color);
            add(startButton);
            startButton.setBounds(450, ball.getY() + ball.getDiameter() * i, 65, 25);
            JButton stopButton = new JButton("Stop");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ball.setMoving(false);
                }
            });
            stopButton.setBackground(color);
            add(stopButton);
            stopButton.setBounds(520, ball.getY() + ball.getDiameter() * i, 65, 25);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 500);
    }

    @Override
    public void run() {
        while (true) {
            for (Ball ball : balls) {
                ball.move();
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
