package scraper;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class Scraper {

    private String csvpath;
    private String outputPath;

    public Scraper(String csvpath, String outputPath) {
        this.csvpath = csvpath;
        this.outputPath = outputPath;
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

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (ScrapeObject scrapeObject : scrapeObjects) {
            try (PrintWriter output = new PrintWriter(outputPath + "\\" + scrapeObject.getType() + ".json")) {
                String jsonString = gson.toJson(JsonParser.parseString(scrapeObject.scrape().toString()));
                output.println(jsonString);
            }
        }
    }
}

@JsonPropertyOrder({ "link", "xpath", "type" })
class ScrapeObject {

    private static final Pattern vietnamese = Pattern.compile(
                "([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựýỳỵỷỹ(](.|\n)*[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựýỳỵỷỹ)])");
    private static final Pattern bracket = Pattern.compile("\\[\\w+\\]|\\[?\\w+\\]|\\[\\w+\\]?|^[0-9]+$");

    private String link;
    private String xpath;
    private String type;

    public String getLink() {
        return link;
    }

    public String getXpath() {
        return xpath;
    }

    public String getType() {
        return type;
    }

    public static String matchVietnameseChar(String str) {
        Matcher vnMatcher = vietnamese.matcher(str);
        if (vnMatcher.find()){
            String vnString = vnMatcher.group(1);
            Matcher bracketMatcher = bracket.matcher(vnString);
            return bracketMatcher.replaceAll("");
        } else {
            return "";
        }
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
                        .map(cell -> matchVietnameseChar(cell.asNormalizedText()))
                        .collect(Collectors.toList());

                for (int i = 1; i < table.getRows().size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    for (int j = 0; j < attrName.size(); j++)
                        try {
                            jsonObject.put(attrName.get(j),
                                    matchVietnameseChar(table.getCellAt(i, j).asNormalizedText())
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
