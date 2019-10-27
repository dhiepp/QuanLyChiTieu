package nhom3.quanlychitieu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nhom3.quanlychitieu.model.KhoanThu;

public class KhoanThuData {

    private static String DATABASE_NAME = "data.db";
    SQLiteDatabase sqLiteDatabase = null;

    public KhoanThuData(Context context){
        sqLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
    }


    public ArrayList<KhoanThu> getAllKhoanThu() {
        Cursor cursor = sqLiteDatabase.query("KHOANTHU",null,null,null,null,null,null);
        ArrayList<KhoanThu> arrayList= new ArrayList<KhoanThu>();
        arrayList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String hangMuc = cursor.getString(1);
            String ghiChu = cursor.getString(2);
            Date ngay = new Date(cursor.getLong(3)*1000);
            int soTien = cursor.getInt(4);
            int ntID = cursor.getInt(5);
            KhoanThu khoanThu = new KhoanThu();
            khoanThu.setId(id);
            khoanThu.setNgay(ngay);
            khoanThu.setHangMuc(hangMuc);
            khoanThu.setNtID(ntID);
            khoanThu.setSoTien(soTien);
            khoanThu.setGhiChu(ghiChu);
            arrayList.add(khoanThu);
        }
        cursor.close();
        return  arrayList;
    }

    public boolean xoaKhoanThu(String id) {
        try {
            sqLiteDatabase.delete("KHOANTHU","id=?",new String[]{id});
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean suaKhoanThu(KhoanThu khoanThu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hang_muc",khoanThu.getHangMuc());
        contentValues.put("ghi_chu",khoanThu.getGhiChu());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngay = sdf.format(khoanThu.getNgay());
        contentValues.put("ngay",ngay);
        contentValues.put("so_tien",khoanThu.getSoTien());
        contentValues.put("ntID",khoanThu.getNtID());
        long kq= sqLiteDatabase.update("KHOANTHU",contentValues,"id = ?",new String[]{khoanThu.getId()+""});
        if(kq >0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean themKhoanThu(KhoanThu khoanThu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",khoanThu.getId());
        contentValues.put("hang_muc",khoanThu.getHangMuc());
        contentValues.put("ghi_chu",khoanThu.getGhiChu());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngay = sdf.format(khoanThu.getNgay());
        contentValues.put("ngay",ngay);
        contentValues.put("so_tien",khoanThu.getSoTien());
        contentValues.put("ntID",khoanThu.getNtID());
        long kq=  sqLiteDatabase.insert("KHOANTHU",null,contentValues);
        if(kq >0){
            return true;
        }
        else {
            return false;
        }
    }
}
