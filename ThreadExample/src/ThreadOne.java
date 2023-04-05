public class ThreadOne extends Thread {
    int start;
    public ThreadOne(int s){
        start = s;
    }
    public void go(int s){
        for(int i=s;i<10;i+=2){
            System.out.print(i + " ");
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        go(start);
    }
}

