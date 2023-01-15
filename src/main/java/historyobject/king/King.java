package historyobject.king;

import com.google.gson.annotations.SerializedName;

import historyobject.HistoryObject;

public class King extends HistoryObject {

    // multiple deserialize name per field
    @SerializedName(value = "Vua", alternate = { "Thủ lĩnh", "Tước hiệu", "Chúa", "Hoàng đế" })
    private String ten;
    @SerializedName("Miếu hiệu")
    private String mieuHieu;
    @SerializedName(value = "Thụy hiệu", alternate = { "Tôn hiệu", "Tôn hiệu hoặc Thụy hiệu" })
    private String thuyHieu;
    @SerializedName("Niên hiệu")
    private String nienHieu;
    @SerializedName("Tên húy")
    private String tenHuy;
    @SerializedName("Thế thứ")
    private String theThu;
    @SerializedName("Trị vì")
    private String triVi;

    public String getTen() {
        return ten;
    }

    public String getMieuHieu() {
        return lazyLoading(mieuHieu);
    }

    public String getThuyHieu() {
        return thuyHieu;
    }

    public String getNienHieu() {
        return nienHieu;
    }

    public String getTenHuy() {
        return tenHuy;
    }

    public String getTheThu() {
        return lazyLoading(theThu);
    }

    public String getTriVi() {
        return triVi;
    }
}
