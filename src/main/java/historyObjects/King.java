package historyObjects;

import com.google.gson.annotations.SerializedName;

public class King {

    @SerializedName("Vua")
    private String name;
    @SerializedName("Trị vì")
    private String reign;
    @SerializedName("Thụy hiệu")
    private String reignName;

    public String getName() {
        return name;
    }

    public String getReign() {
        return reign;
    }

    public String getReignName() {
        return reignName;
    }

    @Override
    public String toString() {
        return "KingObject{" +
                "name='" + name + '\'' +
                ", reign='" + reign + '\'' +
                ", reignName='" + reignName + '\'' +
                '}';
    }
}
