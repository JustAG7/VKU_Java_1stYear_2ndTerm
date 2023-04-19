import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import java.io.StringReader;
import org.apache.commons.io.FileUtils; // import the Apache Commons IO library


public class DOM {

    public static void main(String[] args) {

        try {
            File inputFile = new File("/home/justa/IdeaProjects/XMLProcess/src/Student.xml");
            org.jsoup.nodes.Document olddoc = Jsoup.parse(inputFile, "UTF-8", "", Parser.xmlParser());

            String fixedXml = olddoc.outerHtml();

            org.apache.commons.io.FileUtils.writeStringToFile(inputFile, fixedXml, "UTF-8");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(new org.xml.sax.InputSource(new java.io.StringReader(fixedXml)));

            doc.getDocumentElement().normalize();

            org.w3c.dom.NodeList nList = doc.getElementsByTagName("student");

            for (int i = 0; i < nList.getLength(); i++) {
                org.w3c.dom.Node nNode = nList.item(i);

                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String classroom = eElement.getElementsByTagName("classroom").item(0).getTextContent();
                    float score = Float.parseFloat(eElement.getElementsByTagName("score").item(0).getTextContent());
                    String phone = eElement.getElementsByTagName("phone").item(0).getTextContent();

                    Student student = new Student(name, classroom, score, phone);
                    System.out.println(student);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

