package scraper;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.WebClient;

import java.util.List;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.*;

@JsonPropertyOrder({ "link", "xpath", "regex", "output_filename" })
public class ScrapeObject {

    private String link;
    private String xpath;
    private String regex;
    private String output_filename;

    public ScrapeObject() {
    }

    public String getLink() {
        return link;
    }

    public String getXpath() {
        return xpath;
    }

    public String getRegex() {
        return regex;
    }

    public String getOutput_filename() {
        return output_filename;
    }

    public void scrape() throws Exception {

        JSONArray jsonObjects = new JSONArray();

        try (WebClient client = new WebClient()) {
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage page = client.getPage(link);

            for (Object o : page.getByXPath(xpath)) {
                HtmlTable table = (HtmlTable) o;

                List<HtmlTableCell> header = table.getByXPath(".//th");
                List<String> attrName = header.stream()
                        .map(cell -> cell.asNormalizedText().replaceAll(regex, ""))
                        .toList();

                List<HtmlTableRow> rows = table.getRows();
                for (int i = 1; i < rows.size(); i++) {
                    List<HtmlTableCell> cells = rows.get(i).getCells();
                    JSONObject jsonObject = new JSONObject();

                    for (int j = 0; j < Math.min(attrName.size(), cells.size()); j++)
                        jsonObject.put(attrName.get(j),
                                cells.get(j).asNormalizedText()
                                        .replaceAll(regex, "")
                                        .replaceAll("\n", ", "));
                    jsonObjects.put(jsonObject);
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(JsonParser.parseString(jsonObjects.toString()));
        try (PrintWriter output = new PrintWriter("src/test/resources/data/" + output_filename)) {
            output.println(jsonString);
        }
    }
}
