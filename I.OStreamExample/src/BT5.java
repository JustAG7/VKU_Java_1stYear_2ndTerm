import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.*;

public class BT5 {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenu editMenu;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private text textArea;
    private boolean ok = false;
    JFileChooser fileChooser = new JFileChooser();
    String path = "";
    public BT5(){
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save As");
        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        textArea = new text();
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        frame.setJMenuBar(menuBar);
        frame.add(textArea, BorderLayout.CENTER);
        openItem.addActionListener(e -> {
            ok = true;
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        saveItem.addActionListener(e -> {

            if(!ok) JOptionPane.showMessageDialog(frame, "Please choose a file to save");
            else{
                try{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        saveAsItem.addActionListener(e -> {
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                path = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        newItem.addActionListener(e -> {
            ok = true;
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                path = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        cutItem.addActionListener(e -> {
            textArea.cut();
        });
        copyItem.addActionListener(e -> {
            textArea.copy();
        });
        pasteItem.addActionListener(e -> {
            textArea.paste();
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BT5();
    }
}
class text extends TextArea{
    String text = "";
    StringSelection stringSelection = new StringSelection(text);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public void cut(){
        text = this.getSelectedText();
        stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);
        this.replaceRange("", this.getSelectionStart(), this.getSelectionEnd());
    }
    public void copy(){
        text = this.getSelectedText();
        stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);
    }
    public void paste(){
        try{
            text = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception e){
            e.printStackTrace();
        }
        this.insert(text, this.getCaretPosition());
    }
}