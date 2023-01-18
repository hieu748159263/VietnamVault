package historyobject;

public class HistoryObject {

    private static final String DEFAULT_STRING_VALUE = "không rõ";

    // Lazy loading: return default value if field is null
    public static String lazyLoading(String value) {
        return (value == null) ? DEFAULT_STRING_VALUE : value;
    }
}
