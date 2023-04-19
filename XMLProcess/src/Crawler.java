import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

    public static void main(String[] args) {
        try {
            String domain = "https://vnexpress.net/";
            Set<String> crawledUrls = new HashSet<>();
            Queue<String> urlsToCrawl = new LinkedList<>();
            urlsToCrawl.add(domain);
            while (!urlsToCrawl.isEmpty()) {
                String url = urlsToCrawl.poll();
                if (url.startsWith(domain) && !crawledUrls.contains(url)) {
                    crawledUrls.add(url);
                    System.out.println("Crawling: " + url);
                    Document document = Jsoup.connect(url).get();
                    Elements links = document.select("a[href^=" + domain + "]");
                    for (Element link : links) {
                        String linkHref = link.attr("href");
                        if (!linkHref.endsWith("#box_comment_vne") && !crawledUrls.contains(linkHref)) {
                            urlsToCrawl.add(linkHref);
                        }
                    }
                    String fileName = getFileName(url);
                    String fileContent = document.text().replaceAll("(?<!\\r)\\n", "\r\n");
                    writeToFile(fileName, fileContent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(String linkHref) {
        String[] pathParts = linkHref.split("/");
        String pageName = pathParts[pathParts.length - 1];
        int queryIndex = pageName.indexOf("?");
        if (queryIndex != -1) {
            pageName = pageName.substring(0, queryIndex);
        }
        pageName = pageName.replaceAll("[^a-zA-Z0-9.-]", "_");
        return pageName + ".txt";
    }

    private static void writeToFile(String fileName, String fileContent) throws IOException {
        Path directoryPath = Paths.get("/home/justa/IdeaProjects/XMLProcess/VNExpress");
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        Path filePath = directoryPath.resolve(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
        writer.write(fileContent);
        writer.close();
    }
}
