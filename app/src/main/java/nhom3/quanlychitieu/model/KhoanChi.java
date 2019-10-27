package nhom3.quanlychitieu.model;

import java.io.Serializable;
import java.util.Date;

public class KhoanChi implements Serializable {

    private int id;
    private String hangMuc;
    private String ghiChu;
    private Date ngay;
    private int soTien;
    private int ntID;

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

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

    public int getNtID() {
        return ntID;
    }

    public void setNtID(int ntID) {
        this.ntID = ntID;
    }
}
