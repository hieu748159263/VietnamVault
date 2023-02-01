package historyobject.dynasty;

import com.google.gson.annotations.SerializedName;

import historyobject.HistoryObject;

public class Dynasty extends HistoryObject{

    @SerializedName("Tuổi thọ")
    private String age;

    @SerializedName("Thời kỳ")
    private String period;

    @SerializedName("Các vị vua")
    private String kings;

    @SerializedName("Tên triều đại")
    private String dynastyName;

    @SerializedName("Năm trị vì")
    private String reignTime;

    public String getAge() {
        return lazyLoading(age);
    }

    public String getPeriod() {
        return lazyLoading(period);
    }

    public String getKings() {
        return lazyLoading(kings);
    }

    public String getDynastyName() {
        return lazyLoading(dynastyName);
    }

    public String getReignTime() {
        return lazyLoading(reignTime);
    }
}
