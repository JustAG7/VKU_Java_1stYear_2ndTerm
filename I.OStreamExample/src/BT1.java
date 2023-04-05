import java.io.*;
public class BT1 {
    private int len(String path) {
        File f = new File(path);
        return f.list().length;
    }
    // this path has a .git folder which doesn't appear in my computer
    public static void main(String[] args) {
        System.out.println(new BT1().len("/home/justa/HTML-CSS-VKU#1"));
    }
}
