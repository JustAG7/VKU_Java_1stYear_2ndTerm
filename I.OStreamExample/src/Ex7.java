import java.io.*;
public class Ex7 {
    public static void writeFile(String path){
        DataOutputStream bf = null;
        String msg = "Hello world";
        try{
            bf = new DataOutputStream(new FileOutputStream(path));
            bf.writeUTF(msg);
            bf.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                bf.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        writeFile("/home/justa/link GG Drive.txt");
    }
}
