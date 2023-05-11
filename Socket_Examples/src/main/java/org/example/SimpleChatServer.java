import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SimpleChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(7000);
        System.out.println("Server is started");
        Socket socket = server.accept();
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream din = new DataInputStream(socket.getInputStream());

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javaTerm2", "sa", "123");

        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO calculations (firstNumber, secondNumber, operation, result) VALUES (?, ?, ?, ?)");

        while (true) {

            double num1 = din.readDouble();
            double num2 = din.readDouble();
            String op = din.readUTF();

            double result = 0.0;
            switch (op) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }

            // luu ket qua vao CSDL
            stmt.setDouble(1, num1);
            stmt.setDouble(2, num2);
            stmt.setString(3, op);
            stmt.setDouble(4, result);
            stmt.executeUpdate();

            // gui ket qua ve cho client
            dos.writeDouble(result);
            dos.flush();
        }
    }
}
