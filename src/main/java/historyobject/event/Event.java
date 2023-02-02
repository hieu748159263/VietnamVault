package historyobject.event;

import com.google.gson.annotations.SerializedName;

import historyobject.HistoryObject;

public class Event extends HistoryObject {

    @SerializedName("Sự kiện")
    private String name;
    @SerializedName("Thời gian")
    private String time;

    public String getName() {
        return lazyLoading(name);
    }

    public String getTime() {
        return lazyLoading(time);
    }
}
