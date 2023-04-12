import java.sql.*;
public class Connect {
    private Connection conn= null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;


    public Connect() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/XMLtest?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","sa","123");
            stmt = conn.createStatement();
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addStudent(int id, String name, String classroom, float score, String phone) throws SQLException {
        String sql = "insert into Student values ("+id+",'"+name+"','"+classroom+"',"+score+",'"+phone+"')";
        stmt.executeUpdate(sql);
    }

}
