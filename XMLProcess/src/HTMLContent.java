import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URL;

public class HTMLContent {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://genk.vn/tren-tay-leica-m11-monochrom-dat-hon-31-trieu-dong-nhung-mat-mau-sac-20230418204314094.chn");
        Document doc = Jsoup.parse(url,50000);
        Elements pElements = doc.select("title");
        for (Element p : pElements) {
            System.out.println(p.text());
        }
        pElements = doc.select("h1");
        for (Element p : pElements) {
            System.out.println(p.text());
        }
        pElements = doc.select("p");
        for (Element p : pElements) {
            System.out.println(p.text());
        }
    }
}
