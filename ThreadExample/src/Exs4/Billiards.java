package Exs4;
import javax.swing.*;

public class Billiards {
    public static void main(String[] args) {
        BallPanel ballPanel = new BallPanel();
        JFrame frame = new JFrame("Billiards");
        frame.setContentPane(ballPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ballPanel.addNotify();

    }
}