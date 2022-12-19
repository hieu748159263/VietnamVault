package webScrapingTests.hackerNews;

public class HackerNewsItem {

    public String title;
    public String url;
    public String author;
    public int score;
    public int position;
    public int id;

    public HackerNewsItem(String title, int position, int id) {
        this.title = title;
        this.position = position;
        this.id = id;
    }
}
