package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;

public class Ex2 {
    private JFrame frame;
    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public Ex2() {
        frame = new JFrame("Search lord");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel searchLabel = new JLabel("Tìm kiếm:");
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                if (!searchQuery.isEmpty()) {

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MidTerm2?useSSL=false&serverTimezone=UTC", "sa", "123");
                        PreparedStatement statement = connection.prepareStatement("SELECT * FROM gk WHERE name LIKE ?");
                        statement.setString(1, "%" + searchQuery + "%");
                        ResultSet resultSet = statement.executeQuery();

                        tableModel.setRowCount(0);

                        while (resultSet.next()) {
                            int index = resultSet.getInt("ind");
                            String id = resultSet.getString("id");
                            String name = resultSet.getString("name");
                            Date dateOfBirth = resultSet.getDate("bod");
                            String email = resultSet.getString("email");
                            String tel = resultSet.getString("tel");
                            tableModel.addRow(new Object[]{index,id, name, dateOfBirth, email,tel});
                        }
                        resultSet.close();
                        statement.close();
                        connection.close();
                        saveResultsToXML(tableModel);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        tableModel = new DefaultTableModel(new Object[]{"Index", "ID", "Name", "DoB", "Email","Tel"}, 0);
        resultTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(resultTable);

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
    private void saveResultsToXML(DefaultTableModel tableModel) {
        try {
            FileWriter writer = new FileWriter("/home/justa/IdeaProjects/Midterm_2nd/src/main/java/org/example/ex2.xml");

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<results>\n");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write("  <result>\n");
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    String columnName = tableModel.getColumnName(j);
                    Object value = tableModel.getValueAt(i, j);
                    writer.write("    <" + columnName + ">" + value + "</" + columnName + ">\n");
                }
                writer.write("  </result>\n");
            }
            writer.write("</results>\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ex2();
            }
        });
    }
}

