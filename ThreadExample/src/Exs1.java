import java.util.Scanner;
public class Exs1 {
    double width;
    double height;
    double perimeter;
    double area;

    class ThreadOne extends Thread{
        public void run(){
            System.out.println("Nhap chieu dai: ");
            width = new Scanner(System.in).nextDouble();
            System.out.println("Nhap chieu rong: ");
            height = new Scanner(System.in).nextDouble();
        }
    }
    class PerimeterCalc extends Thread{
        public void run(){
            perimeter = (width + height) * 2;
        }
    }
    class AreaCalc extends Thread{
        public void run(){
            area = width * height;
        }
    }
    public Exs1(){
        Thread t1 = new ThreadOne();
        Thread t2 = new PerimeterCalc();
        Thread t3 = new AreaCalc();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t3.start();
        try {
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }
    public static void main(String[] args) {
        new Exs1();
    }
}
