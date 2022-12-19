package webScrapingTests.englishToKatakana;

public class EnglishToKataItem {

    private String english;
    private String katakana;

    public EnglishToKataItem(String english, String katakana) {
        this.english = english;
        this.katakana = katakana;
    }

    public String getEnglish() {
        return english;
    }

    public String getKatakana() {
        return katakana;
    }
}
