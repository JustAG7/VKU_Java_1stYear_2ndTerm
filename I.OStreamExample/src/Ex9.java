import java.io.*;
public class Ex9 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream("/home/justa/Link GG Drive.txt");
            fos = new FileOutputStream("/home/justa/lmao.txt");
            int data;
            data = fis.read();
            while(data != -1) {
                fos.write(data);
                data = fis.read();
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
