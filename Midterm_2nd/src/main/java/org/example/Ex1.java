package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;

public class Ex1 {
    private JFrame frame;
    private JTextField sourceField;
    private JTextField destinationField;

    public Ex1() {
        frame = new JFrame("Folder Copy Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        JLabel sourceLabel = new JLabel("Thư mục nguồn:");
        sourceField = new JTextField(30);
        JButton sourceBrowseButton = new JButton("Browse...");
        JLabel destinationLabel = new JLabel("Thư mục đích:");
        destinationField = new JTextField(30);
        JButton destinationBrowseButton = new JButton("Browse...");
        JButton copyButton = new JButton("Copy");

        sourceBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    sourceField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        destinationBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    destinationField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourcePath = sourceField.getText();
                String destinationPath = destinationField.getText();
                if (!sourcePath.isEmpty() && !destinationPath.isEmpty()) {
                    try {
                        copyFolder(new File(sourcePath), new File(destinationPath));
                        JOptionPane.showMessageDialog(frame, "Sao chép thành công!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập đường dẫn thư mục nguồn và đích.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel sourcePanel = new JPanel(new FlowLayout());
        sourcePanel.add(sourceLabel);
        sourcePanel.add(sourceField);
        sourcePanel.add(sourceBrowseButton);
        frame.add(sourcePanel);

        JPanel destinationPanel = new JPanel(new FlowLayout());
        destinationPanel.add(destinationLabel);
        destinationPanel.add(destinationField);
        destinationPanel.add(destinationBrowseButton);
        frame.add(destinationPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(copyButton);
        frame.add(buttonPanel);

        frame.pack();
        frame.setVisible(true);
    }

    private static void copyFolder(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdir();
            }

            String[] files = source.list();

            for (String file : files) {
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);
                copyFolder(srcFile, destFile);
            }
        } else {
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ex1();
            }
        });
    }
}

