import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class StudentHandler extends DefaultHandler {
    private ArrayList<Student> studentList = new ArrayList<>();
    private Student student;
    private StringBuilder data;

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();
        if (qName.equalsIgnoreCase("student")) {
            student = new Student();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "name":
                student.setName(data.toString());
                break;
            case "classroom":
                student.setClassroom(data.toString());
                break;
            case "score":
                student.setScore(Integer.parseInt(data.toString()));
                break;
            case "phone":
                student.setPhone(data.toString());
                break;
            case "student":
                studentList.add(student);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            StudentHandler handler = new StudentHandler();
            saxParser.parse(new File("/home/justa/IdeaProjects/XMLProcess/src/Student.xml"), handler);
            for (Student student : handler.getStudentList()) {
                System.out.println(student);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
