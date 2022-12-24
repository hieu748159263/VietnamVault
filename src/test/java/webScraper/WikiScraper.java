package webScraper;

import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.WebClient;

import java.util.List;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.*;

public class WikiScraper {
    public static void main(String[] args) throws Exception {

        final String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";
        final String xpathExpr = "//table[@class='wikitable sortable']";
        final String bracketRegex = "\\[\\w+\\]";
        JSONArray kings = new JSONArray();

        try (WebClient client = new WebClient()) {
            // get HtmlPage object from url
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage page = client.getPage(url);

            for (Object o : page.getByXPath(xpathExpr)) {
                // get table by Xpath expression
                HtmlTable table = (HtmlTable) o;

                // get header row of table, then transfer to list of attribute name
                List<HtmlTableCell> header = table.getByXPath(".//th");
                List<String> attrName = header.stream()
                        .map(cell -> cell.asNormalizedText().replaceAll(bracketRegex, ""))
                        .toList();

                // get footer rows of table
                List<HtmlTableRow> rows = table.getRows();
                // create JSONObject for every row
                for (int i = 1; i < rows.size(); i++) {
                    List<HtmlTableCell> cells = rows.get(i).getCells();
                    JSONObject king = new JSONObject();
                    for (int j = 0; j < Math.min(attrName.size(), cells.size()); j++)
                        king.put(attrName.get(j),
                                cells.get(j).asNormalizedText()
                                        .replaceAll(bracketRegex, "")
                                        .replaceAll("\n", ", "));
                    kings.put(king);
                }
            }
        }

        // pretty print json string to file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(JsonParser.parseString(kings.toString()));
        try (PrintWriter output = new PrintWriter("src/test/resources/kings.json")) {
            output.println(jsonString);
        }
    }
}