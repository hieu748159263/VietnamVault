package historyobject.festival;

import com.google.gson.annotations.SerializedName;

public class Festival {

    @SerializedName("Lễ hội truyền thống")
    private String festivalName;

    @SerializedName("Ngày âm lịch")
    private String lunarCalendarDate;

    @SerializedName("Ghi chú")
    private String note;

    @SerializedName("Lần đầu tổ chức năm")
    private String theFirstTime_Year;

    @SerializedName("Vị trí")
    private String location;

    public String getFestivalName() {
        return festivalName;
    }

    public String getLunarCalendarDate() {
        return lunarCalendarDate;
    }

    public String getNote() {
        return note;
    }

    public String getTheFirstTime_Year() {
        return theFirstTime_Year;
    }

    public String getLocation() {
        return location;
    }
}
