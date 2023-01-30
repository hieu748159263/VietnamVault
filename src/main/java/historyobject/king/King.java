package historyobject.king;

import com.google.gson.annotations.SerializedName;

import historyobject.HistoryObject;

public class King extends HistoryObject {

    // multiple deserialize name per field
<<<<<<< HEAD
    @SerializedName(value = "Vua", alternate = { "Thủ lĩnh", "Tước hiệu", "Chúa", "Hoàng đế" })
=======
    @SerializedName(value = "Vua", alternate = { "Thủ lĩnh", "Tiết độ sứ","Tước hiệu", "Chúa", "Hoàng đế" })
>>>>>>> 575796d8bb40ba394a28fc6852842bd7fb243e0c
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
<<<<<<< HEAD
        return ten;
=======
        return lazyLoading(ten);
>>>>>>> 575796d8bb40ba394a28fc6852842bd7fb243e0c
    }

    public String getMieuHieu() {
        return lazyLoading(mieuHieu);
    }

    public String getThuyHieu() {
<<<<<<< HEAD
        return thuyHieu;
    }

    public String getNienHieu() {
        return nienHieu;
    }

    public String getTenHuy() {
        return tenHuy;
=======
        return lazyLoading(thuyHieu);
    }

    public String getNienHieu() {
        return lazyLoading(nienHieu);
    }

    public String getTenHuy() {
        return lazyLoading(tenHuy);
>>>>>>> 575796d8bb40ba394a28fc6852842bd7fb243e0c
    }

    public String getTheThu() {
        return lazyLoading(theThu);
    }

    public String getTriVi() {
<<<<<<< HEAD
        return triVi;
    }

    // Test method, delete after finish
    @Override
    public String toString() {
        return "King [mieuHieu=" + mieuHieu + ", nienHieu=" + nienHieu + ", ten=" + ten + ", tenHuy=" + tenHuy
                + ", theThu="
                + theThu + ", thuyHieu=" + thuyHieu + ", triVi=" + triVi + "]";
=======
        return lazyLoading(triVi);
>>>>>>> 575796d8bb40ba394a28fc6852842bd7fb243e0c
    }
}
