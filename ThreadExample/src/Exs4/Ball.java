package Exs4;
import java.awt.*;

public class Ball {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    private int x;
    private int y;
    private int diameter;
    private int xVelocity;
    private int yVelocity;
    private Color color;
    private boolean moving;

    public Ball(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
        this.xVelocity = (int) (Math.random() * 5) + 1;
        this.yVelocity = (int) (Math.random() * 5) + 1;
        this.moving = true;
    }

    public void move() {
        if (moving) {
            x += xVelocity;
            y += yVelocity;

            if (x <= 0 || x >= (500 - diameter)) {
                xVelocity *= -1;
            }

            if (y <= 0 || y >= (500 - diameter)) {
                yVelocity *= -1;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public void toggleMoving() {
        moving = !moving;
    }
}
