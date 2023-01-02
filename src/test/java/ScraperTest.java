import scrape.Scraper;

public class ScraperTest {
    public static void main(String[] args) throws Exception {
        Scraper scraper = new Scraper("src/test/resources/scrapeObject.csv");
        scraper.getJsonFromCSV();
    }
}
