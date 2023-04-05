import java.awt.*;
import java.io.*;
import javax.swing.*;
public class BT2 {
    private Label nameLabel;
    private TextField nameField;
    private Label dateofbirthLabel;
    private TextField dateofbirthField;
    private Label mailLabel;
    private TextField mailField;
    private Label phoneLabel;
    private TextField phoneField;
    private Button saveButton;
    private Button cancelButton;

    public BT2(){
        JFrame frame = new JFrame("Bai tap 2");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));
        nameLabel = new Label("Name: ");
        nameField = new TextField();
        dateofbirthLabel = new Label("Date of birth: ");
        dateofbirthField = new TextField();
        mailLabel = new Label("Mail: ");
        mailField = new TextField();
        phoneLabel = new Label("Phone: ");
        phoneField = new TextField();
        saveButton = new Button("Save");
        File f = new File("/home/justa/data.txt");
        if (f.exists()) {
            try {
                FileReader fr = new FileReader("/home/justa/data.txt");
                BufferedReader br = new BufferedReader(fr);
                String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
                    if (i == 0) {
                        nameField.setText(line);
                    } else if (i == 1) {
                        dateofbirthField.setText(line);
                    } else if (i == 2) {
                        mailField.setText(line);
                    } else if (i == 3) {
                        phoneField.setText(line);
                    }
                    i++;
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    FileWriter fw = new FileWriter("/home/justa/data.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.println(nameField.getText());
                    pw.println(dateofbirthField.getText());
                    pw.println(mailField.getText());
                    pw.println(phoneField.getText());
                    pw.close();
                    bw.close();
                    fw.close();
                    JOptionPane.showMessageDialog(null, "Saved!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(dateofbirthLabel);
        frame.add(dateofbirthField);
        frame.add(mailLabel);
        frame.add(mailField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(saveButton);
        frame.add(cancelButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BT2();
    }
}
