package scraper.tableScraper;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

import scraper.Scraper;

@JsonPropertyOrder({ "link", "xpath", "type" })
public class TableScraper extends Scraper {

    private String xpath;

    public String getXpath() {
        return xpath;
    }

    public static List<TableScraper> readCSV(String csvPath) throws Exception {
        File csvFile = new File(csvPath);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(TableScraper.class)
                .withHeader()
                .withColumnSeparator(',');

        MappingIterator<TableScraper> scraperIter = csvMapper
                .readerWithTypedSchemaFor(TableScraper.class)
                .with(csvSchema)
                .readValues(csvFile);
        List<TableScraper> scrapers = scraperIter.readAll();

        return scrapers;
    }

    public JSONArray scrape() throws Exception {

        JSONArray jsonObjects = new JSONArray();

        try (WebClient client = new WebClient()) {
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage page = client.getPage(link);

            for (Object o : page.getByXPath(xpath)) {
                HtmlTable table = (HtmlTable) o;

                List<HtmlTableCell> header = table.getRow(0).getCells();
                List<String> attrName = header.stream()
                        .map(cell -> stripInfo(cell.asNormalizedText()))
                        .collect(Collectors.toList());

                for (int i = 1; i < table.getRows().size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    for (int j = 0; j < attrName.size(); j++)
                        try {
                            jsonObject.put(attrName.get(j),
                                    stripInfo(table.getCellAt(i, j).asNormalizedText())
                                            .replaceAll("\n", ", "));
                        } catch (Exception NullPointerException) {
                            jsonObject.put(attrName.get(j), "");
                        }
                    jsonObjects.put(jsonObject);
                }
            }
        }
        return jsonObjects;
    }
}
