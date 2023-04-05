import java.io.*;
public class Ex3 {
    public static int count(String path, char ch){
        int c=0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(path);
            int data;
            data = fis.read();
            while(data != -1){
                if((char)data == ch){
                    c++;
                }
                data = fis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(count("/home/justa/HTML-CSS-VKU#1/Lab3/Table.html", 'i'));
    }
}
