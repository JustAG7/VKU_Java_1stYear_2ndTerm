import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class Ex8 extends JFrame implements ActionListener {
    JTextArea ta = new JTextArea();
    JButton saveAs = new JButton("Save As");
    JFileChooser chooser;
    FileWriter f;
    public Ex8() {
        Container cont = this.getContentPane();
        saveAs.addActionListener(this);
        cont.add(ta);
        cont.add(saveAs, BorderLayout.NORTH);
        setSize(500,500);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("/home/justa"));
        chooser.setDialogTitle("Save As");
        if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                saveFile(filename);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void saveFile(String filename) {
        try{
            String content = ta.getText();

            f = new FileWriter(filename);
            f.write(content);
            f.flush();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Ex8();
    }
}
