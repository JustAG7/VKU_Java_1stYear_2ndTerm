import java.io.FileWriter;

public class Ex6 {
    public static void main(String[] args) throws Exception{
        String s = "lmao crying";
        FileWriter writer = new FileWriter("/home/justa/link GG Drive.txt");
        writer.write(s);
        writer.flush();
        writer.close();

    }
}
