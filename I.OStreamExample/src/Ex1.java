import java.io.*;
public class Ex1 {
    public Ex1(String path){
        File f = new File(path);
        String[] filenames = f.list();
        for (String filename : filenames) {
            System.out.println(filename);
        }
    }

    public static void main(String[] args) {
        new Ex1("/home/justa");
    }
}
