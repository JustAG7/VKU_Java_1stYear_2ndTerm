import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ex5 extends JFrame implements ActionListener {
    JTextArea textArea = new JTextArea();
    JButton open = new JButton("Open");
    JFileChooser chooser;
    FileReader reader;
    BufferedReader bufferedReader;

    public Ex5(){
        Container container = this.getContentPane();
        open.addActionListener(this);
        container.add(textArea);
        container.add(open, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(500, 500);

    }
    public void actionPerformed(ActionEvent e){
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("/home/justa"));
        chooser.setDialogTitle("Select file");
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String filename = chooser.getSelectedFile().getAbsolutePath();
            readFile(filename);
        }
    }
    public void readFile(String filename){
        try{
            textArea.setText("");
            reader = new FileReader(filename);
            bufferedReader = new BufferedReader(reader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                textArea.append(line + "\n");

            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Ex5();
    }
}
