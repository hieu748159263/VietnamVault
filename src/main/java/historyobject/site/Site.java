package historyobject.site;

import com.google.gson.annotations.SerializedName;

public class Site {

    @SerializedName("Loại di tích")
    private String typeOfSite;

    @SerializedName("Di tích")
    private String name;

    @SerializedName("Ghi chú")
    private String note;

    @SerializedName("Vị trí")
    private String location;

    @SerializedName("Năm CN")
    private String recognizedYear;

    public String getTypeOfSite() {
        return typeOfSite;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getLocation() {
        return location;
    }

    public String getRecognizedYear() {
        return recognizedYear;
    }
}
