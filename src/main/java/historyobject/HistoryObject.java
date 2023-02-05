package historyobject;

public abstract class HistoryObject {

    private static final String DEFAULT_STRING_VALUE = "";

    // Lazy loading: return default value if field is null
    protected static String lazyLoading(String value){
        return (value == null)? DEFAULT_STRING_VALUE : value;
    }
}
