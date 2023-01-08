import scraper.Scraper;

public class ScraperTest {
    public static void main(String[] args) throws Exception {
        Scraper scraper = new Scraper("src\\main\\resources\\csv\\scrapeObject.csv", "src\\main\\resources\\data");
        scraper.getJsonFromCSV();
    }
}
