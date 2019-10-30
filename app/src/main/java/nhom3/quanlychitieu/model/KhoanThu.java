package nhom3.quanlychitieu.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class KhoanThu implements Serializable {

    private int id;
    private String hangMuc;
    private String ghiChu;
    private Date ngay;
    private int soTien;
    private int ntID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getNgayString() { return new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(ngay); }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getSoTienString() {
        return NumberFormat.getInstance().format(soTien) + " đ";
    }

    public int getNtID() {
        return ntID;
    }

    public void setNtID(int ntID) {
        this.ntID = ntID;
    }
}
