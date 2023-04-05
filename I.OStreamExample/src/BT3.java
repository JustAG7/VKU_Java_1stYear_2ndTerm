import java.awt.*;
import javax.swing.*;
import java.io.*;

public class BT3 {
    private Button openButton;
    private Button saveAsButton;
    private TextArea textArea;
    public BT3(){
        JFrame frame = new JFrame("Bai tap 3");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        openButton = new Button("Open");
        saveAsButton = new Button("Save as");
        textArea = new TextArea();
        frame.add(openButton, BorderLayout.NORTH);
        frame.add(saveAsButton, BorderLayout.SOUTH);
        frame.add(textArea, BorderLayout.CENTER);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileDialog fd = new FileDialog(frame, "Open", FileDialog.LOAD);
                fd.setVisible(true);
                String path = fd.getDirectory() + fd.getFile();
                File f = new File(path);
                if (f.exists()) {
                    try {
                        FileReader fr = new FileReader(path);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        while ((line = br.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                        br.close();
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileDialog fd = new FileDialog(frame, "Save as", FileDialog.SAVE);
                fd.setVisible(true);
                String path = fd.getDirectory() + fd.getFile();
                File f = new File(path);
                if (f.exists()) {
                    try {
                        FileWriter fw = new FileWriter(path);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println(textArea.getText());
                        pw.close();
                        bw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new BT3();
    }
}
