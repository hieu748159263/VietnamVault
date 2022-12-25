package app.update;

import scraper.ScrapeObject;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;

public class Update {

    public static void update(String csvFilepath) throws Exception {

        File csvFile = new File(csvFilepath);

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

        for (ScrapeObject scrapeObject : scrapeObjects) {
            System.out.println(scrapeObject.getLink());
            scrapeObject.scrape();
        }
    }
}

