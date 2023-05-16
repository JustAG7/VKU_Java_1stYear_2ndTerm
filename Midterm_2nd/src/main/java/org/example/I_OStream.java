package org.example;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Field;

public class I_OStream {
    String path = "src/main/java/org/example";
    File file = new File(path); // create file object
    FileReader fr = new FileReader(path); // read file
    BufferedReader br = new BufferedReader(fr); // read each line of the file
    FileWriter fw = new FileWriter(path); // write file


    FileInputStream fis = new FileInputStream(path); // read file as bytes
    FileOutputStream fos = new FileOutputStream(path); // write file as bytes

    JFileChooser jfc = new JFileChooser(); // open file chooser


    public I_OStream() throws IOException {

    }
}
