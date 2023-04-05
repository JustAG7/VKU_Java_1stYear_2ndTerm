import java.io.*;
public class Ex4 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr = new FileReader("src/Main.java");
        BufferedReader br = new BufferedReader(fr);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
