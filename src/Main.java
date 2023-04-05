import sun.plugin.net.protocol.jar.CachedJarURLConnection;

import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        String url = "https://dantri.com.vn/the-gioi.htm";
        try {
            URLConnection conn = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            Pattern pattern = Pattern.compile("<a\\s+href=\"([^\"]*)\"[^>]*>(.*?)</a>", Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            Matcher matcher = pattern.matcher(content.toString());
            while (matcher.find()) {
                String link = matcher.group(1);
                String title = matcher.group(2);
                if (title != null && !title.isEmpty()) {
                    System.out.println(title.trim() + ": " + link);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
