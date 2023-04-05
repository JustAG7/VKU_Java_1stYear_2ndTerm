public class Exs3 {
    class ThreadOne extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    //copy ThreadOne but change One to Two to Seven
    class ThreadTwo extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    class ThreadThree extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    class ThreadFour extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    class ThreadFive extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    class ThreadSix extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    class ThreadSeven extends Thread {
        int num;
        public void run() {
            //random from 1 to 30
            num = (int) (Math.random() * 30 + 1);
            System.out.println("Num: " + num);
        }
        public int getNum() {
            return num;
        }
    }
    public Exs3(){
        ThreadOne t1 = new ThreadOne();
        ThreadTwo t2 = new ThreadTwo();
        ThreadThree t3 = new ThreadThree();
        ThreadFour t4 = new ThreadFour();
        ThreadFive t5 = new ThreadFive();
        ThreadSix t6 = new ThreadSix();
        ThreadSeven t7 = new ThreadSeven();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = t1.getNum() + t2.getNum() + t3.getNum() + t4.getNum() + t5.getNum() + t6.getNum() + t7.getNum();
        System.out.println("Sum: " + sum);
    }

    public static void main(String[] args) {
        new Exs3();
    }
}
