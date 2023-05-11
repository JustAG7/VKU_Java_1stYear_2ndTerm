import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7000);
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


        Scanner kb = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap so thu nhat: ");
            double num1 = kb.nextDouble();
            System.out.print("Nhap so thu hai: ");
            double num2 = kb.nextDouble();
            System.out.print("Nhap phep toan (+, -, *, /): ");
            String op = kb.next();

            dos.writeDouble(num1);
            dos.writeDouble(num2);
            dos.writeUTF(op);
            dos.flush();


            double result = din.readDouble();
            System.out.println("Ket qua: " + result);

            kb.nextLine();

            Thread.sleep(1000);
        }
    }
}
