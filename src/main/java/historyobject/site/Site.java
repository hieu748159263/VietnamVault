package historyobject.site;

import com.google.gson.annotations.SerializedName;

import historyobject.HistoryObject;

public class Site extends HistoryObject{

    @SerializedName(value = "Loại di tích", alternate={"Hạng"})
    private String typeOfSite;

    @SerializedName("Di tích")
    private String name;

    @SerializedName(value = "Ghi chú", alternate = {"Giá trị nổi bật"})
    private String note;

    @SerializedName(value = "Vị trí",  alternate = {"Địa điểm"} )
    private String location;

    @SerializedName("Năm CN")
    private String recognizedYear;

    public String getTypeOfSite() {
        return lazyLoading(typeOfSite);
    }

    public String getName() {
        return lazyLoading(name);
    }

    public String getNote() {
        return lazyLoading(note);
    }

    public String getLocation() {
        return lazyLoading(location);
    }

    public String getRecognizedYear() {
        return lazyLoading(recognizedYear);
    }
}
