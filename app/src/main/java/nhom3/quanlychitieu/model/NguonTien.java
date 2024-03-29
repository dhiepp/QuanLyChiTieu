package nhom3.quanlychitieu.model;

import java.io.Serializable;
import java.text.NumberFormat;

public class NguonTien implements Serializable {

    private int id;
    private String ten;
    private String mieuTa;
    private int soDu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public String getSoDuString() {
        return NumberFormat.getInstance().format(soDu) + " đ";
    }
}
