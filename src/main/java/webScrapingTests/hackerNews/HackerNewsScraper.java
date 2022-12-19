package webScrapingTests.hackerNews;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

public class HackerNewsScraper {
    public static void main(String[] args) {

        String baseUrl = "https://news.ycombinator.com/";
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        try {
            HtmlPage page = client.getPage(baseUrl);
            List<HtmlElement> itemList = page.getByXPath("//tr[@class='athing']");
            if (itemList.isEmpty()) {
                System.out.println("No item found");
            } else {
                for (HtmlElement htmlItem : itemList) {
                    int position = Integer.parseInt(
                            ((HtmlElement) htmlItem.getFirstByXPath("./td/span"))
                                    .asNormalizedText()
                                    .replace(".", ""));
                    int id = Integer.parseInt(htmlItem.getAttribute("id"));
                    String title = ((HtmlElement) htmlItem
                            .getFirstByXPath("./td[not(@valign='top')][@class='title']"))
                            .asNormalizedText();
                    // String url = ((HtmlAnchor) htmlItem
                    // .getFirstByXPath("./td[not(@valign='top')][@class='title']/a"))
                    // .getHrefAttribute();
                    // String author = ((HtmlElement) htmlItem
                    // .getFirstByXPath("./following-sibling::tr/td[@class='subtext']/a[@class='hnuser']"))
                    // .asNormalizedText();
                    // int score = Integer.parseInt(
                    // ((HtmlElement) htmlItem
                    // .getFirstByXPath(
                    // "./following-sibling::tr/td[@class='subtext']/span[@class='score']"))
                    // .asText().replace(" points", ""));
                    // HackerNewsItem hnItem = new HackerNewsItem(title, "1", "1", 1, position, id);
                    // ObjectMapper mapper = new ObjectMapper();
                    // String jsonString = mapper.writeValueAsString(hnItem);
                    // System.out.println(jsonString);
                    System.out.println(position + " " + id + " " + title);
                    HackerNewsItem hnItem = new HackerNewsItem(title, position, id);
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(hnItem);
                    // print or save to a file
                    System.out.println(jsonString);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }
}
