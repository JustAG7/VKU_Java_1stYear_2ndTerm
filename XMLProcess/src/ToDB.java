import java.sql.SQLException;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ToDB {
    private Connect con;

    public ToDB() throws SQLException {
        con = new Connect();
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
                    con.addStudent(i+1,name, classroom, score, phone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
        new ToDB();
    }
}
