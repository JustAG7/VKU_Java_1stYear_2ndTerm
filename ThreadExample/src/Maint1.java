public class Maint1 {
    public static void main(String[] args) {
        ThreadTwo t1 = new ThreadTwo(1);
        ThreadTwo t2 = new ThreadTwo(2);
        t1.start();
        t2.start();
    }
}