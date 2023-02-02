package scraper.paragraphScraper;

import java.io.File;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import scraper.Scraper;

@JsonPropertyOrder({ "link", "type" })
public class ParagraphScraper extends Scraper {

    public static List<ParagraphScraper> readCSV(String csvPath) throws Exception {
        File csvFile = new File(csvPath);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(ParagraphScraper.class)
                .withHeader()
                .withColumnSeparator(',');

        MappingIterator<ParagraphScraper> scraperIter = csvMapper
                .readerWithTypedSchemaFor(ParagraphScraper.class)
                .with(csvSchema)
                .readValues(csvFile);
        List<ParagraphScraper> scrapers = scraperIter.readAll();

        return scrapers;
    }

    public JSONArray scrape() throws Exception {
        JSONArray jsonObjects = new JSONArray();

        Connection conn = Jsoup.connect(link);
        Document doc = conn.get();
        Elements nodes = doc.select("p, dl");
        for (int i = 7; i < nodes.size() - 1; i++) {
            JSONObject jsonObject = new JSONObject();

            if (nodes.get(i + 1).tagName() == "dl" && i + 1 != (nodes.size() - 1)) {
                int k = i + 1;
                String year = nodes.get(i).select("b").text();
                Elements events = nodes.get(k).children().select("dd");
                for (Element e : events) {
                    String x = e.text();
                    String monthAndDay = e.children().select("b").text();
                    String eventTime = monthAndDay + " năm " + year;
                    String eventName = x.replace(monthAndDay, "");
                    try {
                        jsonObject.put("Thời gian", eventTime)
                                .put("Sự kiện", eventName);
                    } catch (Exception NullPointerException) {
                        jsonObject.put("Thời gian", "")
                                .put("Sự kiện", "");
                    }

                    i = k;
                }
            } else {
                String event = nodes.get(i).text();
                String eventTime = nodes.get(i).children().select("b").text();
                String eventName = event.replace(eventTime, "");
                try {
                    jsonObject.put("Thời gian", eventTime)
                            .put("Sự kiện", eventName);
                } catch (Exception NullPointerException) {
                    jsonObject.put("Thời gian", "")
                            .put("Sự kiện", "");
                }
            }
            jsonObjects.put(jsonObject);
        }
        return jsonObjects;
    }
}
