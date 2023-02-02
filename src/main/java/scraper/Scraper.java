package scraper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;

public abstract class Scraper {

    private static final Pattern TEXTPATTERN = Pattern.compile(
            "([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựýỳỵỷỹ(](.|\n)*[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựýỳỵỷỹ)])");
    private static final String BRACKETREGEX = "\\[\\w+\\]|\\[?\\w+\\]|\\[\\w+\\]?|^[0-9]+$";

    protected String link;
    protected String type;

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    public static String stripInfo(String rawInfo) {
        Matcher textMatcher = TEXTPATTERN.matcher(rawInfo);
        if (textMatcher.find())
            return textMatcher.group(1).replaceAll(BRACKETREGEX, "");
        else
            return null;
    }

    public abstract JSONArray scrape() throws Exception;
}
