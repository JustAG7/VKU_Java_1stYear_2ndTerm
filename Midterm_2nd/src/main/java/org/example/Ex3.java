package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ex3 {
    private JFrame frame;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public Ex3() {
        frame = new JFrame("XML Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loadButton = new JButton("Load XML");
        JButton saveButton = new JButton("Save XML");

        tableModel = new DefaultTableModel(new Object[]{"Index", "ID", "Name", "Date of Birth", "Email", "Tel"}, 0);
        dataTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(dataTable);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    loadXML(file);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    saveXML(file);
                }
            }
        });

        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);


        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private void loadXML(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            tableModel.setRowCount(0);

            NodeList resultNodes = document.getElementsByTagName("result");
            for (int i = 0; i < resultNodes.getLength(); i++) {
                Node resultNode = resultNodes.item(i);
                if (resultNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element resultElement = (Element) resultNode;
                    int index = i + 1;
                    String id = resultElement.getElementsByTagName("ID").item(0).getTextContent();
                    String name = resultElement.getElementsByTagName("Name").item(0).getTextContent();
                    String dateOfBirth = resultElement.getElementsByTagName("DoB").item(0).getTextContent();
                    String email = resultElement.getElementsByTagName("Email").item(0).getTextContent();
                    String tel = resultElement.getElementsByTagName("Tel").item(0).getTextContent();

                    tableModel.addRow(new Object[]{index, id, name, dateOfBirth, email,tel});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Đã xảy ra lỗi khi đọc tệp XML: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveXML(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();


            Element resultsElement = document.createElement("results");
            document.appendChild(resultsElement);


            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                Element resultElement = document.createElement("result");
                resultsElement.appendChild(resultElement);

                Element indexElement = document.createElement("Index");
                indexElement.setTextContent(tableModel.getValueAt(i, 0).toString());
                resultElement.appendChild(indexElement);

                Element idElement = document.createElement("ID");
                idElement.setTextContent(tableModel.getValueAt(i, 1).toString());
                resultElement.appendChild(idElement);

                Element nameElement = document.createElement("Name");
                nameElement.setTextContent(tableModel.getValueAt(i, 2).toString());
                resultElement.appendChild(nameElement);

                Element dobElement = document.createElement("DoB");
                dobElement.setTextContent(tableModel.getValueAt(i, 3).toString());
                resultElement.appendChild(dobElement);

                Element emailElement = document.createElement("Email");
                emailElement.setTextContent(tableModel.getValueAt(i, 4).toString());
                resultElement.appendChild(emailElement);

                Element telElement = document.createElement("Tel");
                telElement.setTextContent(tableModel.getValueAt(i, 5).toString());
                resultElement.appendChild(telElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

            JOptionPane.showMessageDialog(frame, "Lưu XML thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Đã xảy ra lỗi khi lưu tệp XML: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ex3();
            }
        });
    }
}

