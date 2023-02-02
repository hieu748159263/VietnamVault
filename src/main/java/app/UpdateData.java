package app;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import scraper.Scraper;
import scraper.paragraphScraper.ParagraphScraper;
import scraper.tableScraper.TableScraper;

public class UpdateData {
    public static void main(String[] args) {
        String csvPathTable = "src\\main\\resources\\csv\\tableScraper.csv";
        String csvPathParagraph = "src\\main\\resources\\csv\\paragraphScraper.csv";
        String outputPath = "src\\main\\resources\\data";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Scraper> scrapers = new ArrayList<>();
        try {
            scrapers.addAll(TableScraper.readCSV(csvPathTable));
            scrapers.addAll(ParagraphScraper.readCSV(csvPathParagraph));

            for (Scraper scraper : scrapers) {
                try (PrintWriter output = new PrintWriter(outputPath + "\\" + scraper.getType() + ".json")) {
                    String jsonString = gson.toJson(JsonParser.parseString(scraper.scrape().toString()));
                    output.println(jsonString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
