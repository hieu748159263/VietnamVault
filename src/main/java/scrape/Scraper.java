package scrape;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Scraper {

    private String csvpath;

    public Scraper(String csvpath) {
        this.csvpath = csvpath;
    }

    public void getJsonFromCSV() throws Exception {
        File csvFile = new File(csvpath);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(ScrapeObject.class)
                .withHeader()
                .withColumnSeparator(',');

        MappingIterator<ScrapeObject> scrapeObjectIter = csvMapper
                .readerWithTypedSchemaFor(ScrapeObject.class)
                .with(csvSchema)
                .readValues(csvFile);
        List<ScrapeObject> scrapeObjects = scrapeObjectIter.readAll();

        JSONObject data = new JSONObject();
        for (ScrapeObject scrapeObject : scrapeObjects) {
            data.put(scrapeObject.getOutputAttributeName(), scrapeObject.scrape());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(data.toString());
        String jsonString = gson.toJson(jsonElement);
        try (PrintWriter output = new PrintWriter("src/test/resources/data/data.json")) {
            output.println(jsonString);
        }
    }
}
