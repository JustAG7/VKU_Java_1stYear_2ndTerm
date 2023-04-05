public class ThreadFive {
    class FacThread extends Thread{
        long gt = 1;
        int n;
        public FacThread(int n){
            this.n = n;
        }
        public void run(){
            for(int i=1;i<=n;i++){
                gt *= i;
            }
            System.out.println("Giai thua cua " + n + " la: " + gt);
        }
        public long getFac(){
            return gt;
        }
    }
    class SumThread extends Thread{
        long sum = 0;
        int n;
        public SumThread(int n){
            this.n = n;
        }
        public void run(){
            for(int i=1;i<=n;i++){
                sum += i;
            }
            System.out.println("Tong cua " + n + " la: " + sum);
        }
        public long getSum(){
            return sum;
        }
    }
    class SumPowThread extends Thread{
        long sum = 0;
        int x,n;
        public SumPowThread(int x, int n){
            this.x = x;
            this.n = n;
        }
        public void run(){
            for(int i=1;i<=n;i++){
                sum += Math.pow(x,i);
            }
            System.out.println("Tong la: " + sum);
        }
        public long getSumPow(){
            return sum;
        }

    }
    FacThread t1 = new FacThread(2);
    SumThread t2 = new SumThread(3);
    SumPowThread t3 = new SumPowThread(2,1);
    public ThreadFive(){
        t1.start();
        t2.start();
        t3.start();
        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long ans = t1.getFac() + t2.getSum() + t3.getSumPow();
        System.out.println("Ket qua la: " + ans);

    }

    public static void main(String[] args) {
        new ThreadFive();
    }
}
