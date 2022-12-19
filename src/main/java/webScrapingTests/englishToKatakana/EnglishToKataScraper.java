package webScrapingTests.englishToKatakana;
// package webScrapingTest.englishToKatakana;

// import java.util.ArrayList;
// import java.util.List;
// import java.io.PrintWriter;

// import com.gargoylesoftware.htmlunit.html.*;
// import com.gargoylesoftware.htmlunit.WebClient;
// import com.google.gson.GsonBuilder;

// public class EnglishToKataScraper {
//     public static void main(String[] args) throws Exception {
//         String url = "https://www.sljfaq.org/cgi/e2k.cgi";
//         String[] englishWords = { "address", "interface", "internet" };
//         List<EnglishToKataItem> kataWords = new ArrayList<EnglishToKataItem>();

//         try (WebClient client = new WebClient()) {
//             client.getOptions().setCssEnabled(false);
//             client.getOptions().setJavaScriptEnabled(false);

//             HtmlPage page = client.getPage(url);
//             HtmlInput inputWord = page.getFirstByXPath("//form//input[@id='word-input']");
//             for (String word : englishWords) {
//                 inputWord.setValueAttribute(word);
//                 HtmlForm submitForm = inputWord.getEnclosingForm();
//                 HtmlPage resultPage = client.getPage(submitForm.getWebRequest(null));
//                 String kataWord = resultPage.getFirstByXPath("//*[@id='katakana-string']/text()").toString();
//                 kataWords.add(new EnglishToKataItem(word, kataWord));
//             }
//         }
//         String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(kataWords);
//         try (PrintWriter out = new PrintWriter("filename.json")) {
//             out.println(jsonString);
//         }
//     }
// }
