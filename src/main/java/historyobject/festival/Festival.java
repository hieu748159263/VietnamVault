package historyobject.festival;

import com.google.gson.annotations.SerializedName;

import historyobject.HistoryObject;

public class Festival extends HistoryObject{

    @SerializedName("Lễ hội truyền thống")
    private String festivalName;

    @SerializedName(value = "Ngày âm lịch", alternate = {"Ngày bắt đầu (âm lịch)"})
    private String lunarCalendarDate;

    @SerializedName("Ghi chú")
    private String note;

    @SerializedName("Lần đầu tổ chức năm")
    private String theFirstTime_Year;

    @SerializedName("Vị trí")
    private String location;
    @SerializedName("Nhân vật liên quan")
    private String relatedCharacter;

    public String getFestivalName() {
        return lazyLoading(festivalName);
    }

    public String getLunarCalendarDate() {
        return lazyLoading(lunarCalendarDate);
    }

    public String getNote() {
        return lazyLoading(note);
    }

    public String getTheFirstTime_Year() {
        return lazyLoading(theFirstTime_Year);
    }

    public String getLocation() {
        return lazyLoading(location);
    }
    public String getRelatedCharacter() {
        return lazyLoading(relatedCharacter);
    }
}
