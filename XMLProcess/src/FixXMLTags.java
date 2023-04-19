import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FixXMLTags {

    public static void main(String[] args) {
        try {
            String filePath = "/home/justa/IdeaProjects/XMLProcess/src/Student.xml";
            String xmlString = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the XML file as HTML using JSoup
            Document document = Jsoup.parse(xmlString, "", Parser.xmlParser());

            // Find all end tags in the document
            Pattern pattern = Pattern.compile("</([^>]+)>");
            Matcher matcher = pattern.matcher(document.html());
            while (matcher.find()) {
                String endTag = matcher.group(1);
                // Check if there's an open tag with this name before the end tag
                if (!document.select(endTag).isEmpty()) {
                    continue;
                }
                // Find the index of the end tag in the document
                int endIndex = matcher.start();
                // Find the index of the previous '>' character
                int startIndex = document.html().lastIndexOf(">", endIndex);
                // Insert the missing start tag before the end tag
                String startTag = "<" + endTag + ">";
                document.html(document.html().substring(0, startIndex + 1) + startTag + document.html().substring(startIndex + 1));
            }

            // Save the fixed XML file
            Files.write(Paths.get(filePath), document.html().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
