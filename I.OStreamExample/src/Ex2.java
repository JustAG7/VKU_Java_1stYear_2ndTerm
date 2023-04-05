import java.io.*;

public class Ex2 {
    public static int count(String path) {
        int c = 0;
        File f = new File(path);
        String[] filenames = f.list();
        if (filenames != null) { // add null check
            for (String filename : filenames) {
                File fi = new File(path + "/" + filename);
                if (fi.isFile()) {
                    c++;
                } else {
                    c += count(fi.getAbsolutePath());
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(count("/home/justa/HTML-CSS-VKU#1"));
    }
}
