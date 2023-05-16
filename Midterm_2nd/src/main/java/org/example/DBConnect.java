package org.example;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class DBConnect {
    private Connection conn = null; // connect to database
    private Statement stmt = null; // execute sql
    private ResultSet rs = null; // get result
    private ResultSetMetaData rsmd = null; // get result meta data
    private DefaultTableModel model; // table model

    public DBConnect() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MidTerm2?useSSL=false&serverTimezone=UTC", "sa", "123");
        stmt = conn.createStatement();
        System.out.println("Database connected!");
    }

    public DBConnect addname(String name) throws Exception{
        String sql = "INSERT INTO `test` (`name`) VALUES ('"+name+"');";
        stmt.executeUpdate(sql);
        return null;
    }
}
