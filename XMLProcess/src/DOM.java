import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOM {

    public static void main(String[] args) {

        try {
            File inputFile = new File("/home/justa/IdeaProjects/XMLProcess/src/Student.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("student");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
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
