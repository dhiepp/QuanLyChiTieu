package nhom3.quanlychitieu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import nhom3.quanlychitieu.model.KhoanChi;

public class KhoanChiData {

    private static String DATABASE_NAME = "data.db";
    SQLiteDatabase sqLiteDatabase = null;
    private SimpleDateFormat dateFormat;


    public KhoanChiData(Context context){
        sqLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }

    public ArrayList<KhoanChi> getAllKhoanChi() {
        Cursor cursor = sqLiteDatabase.query("KHOANCHI",null,null,null,null,null,"ngay desc");
        ArrayList<KhoanChi> arrayList= new ArrayList<KhoanChi>();
        arrayList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String hangMuc = cursor.getString(1);
            String ghiChu = cursor.getString(2);
            String dateString = cursor.getString(3);
            Date ngay = new Date();
            try {ngay = dateFormat.parse(dateString);} catch (ParseException e) {}
            int soTien = cursor.getInt(4);
            int ntID = cursor.getInt(5);

            KhoanChi khoanChi = new KhoanChi();
            khoanChi.setId(id);
            khoanChi.setNgay(ngay);
            khoanChi.setHangMuc(hangMuc);
            khoanChi.setNtID(ntID);
            khoanChi.setSoTien(soTien);
            khoanChi.setGhiChu(ghiChu);
            arrayList.add(khoanChi);
        }
        cursor.close();
        return arrayList;
    }

    public int getTongAllKhoanChi() {
        String[] columns = new String[] {"so_tien"};
        int tong = 0;
        Cursor cursor = sqLiteDatabase.query("KHOANCHI", columns, null, null,null,null,null);

        while (cursor.moveToNext()){
            tong += cursor.getInt(0);
        }
        cursor.close();
        return tong;
    }

    public int getTongKhoanChiByDate(Date startDate, Date endDate) {
        String[] columns = new String[] {"so_tien"};
        String selection = "ngay >= ? AND ngay <= ?";
        String[] selectionArgs = new String[] {dateFormat.format(startDate), dateFormat.format(endDate)};
        int tong = 0;
        Cursor cursor = sqLiteDatabase.query("KHOANCHI", columns, selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()){
            tong += cursor.getInt(0);
        }
        cursor.close();
        return tong;
    }

    public boolean xoaKhoanChi(String id) {
        try {
            sqLiteDatabase.delete("KHOANCHI","id=?",new String[]{id});
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean suaKhoanChi(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hang_muc",khoanChi.getHangMuc());
        contentValues.put("ghi_chu",khoanChi.getGhiChu());
        String ngay = dateFormat.format(khoanChi.getNgay());
        contentValues.put("ngay",ngay);
        contentValues.put("so_tien",khoanChi.getSoTien());
        contentValues.put("ntID",khoanChi.getNtID());
        long kq= sqLiteDatabase.update("KHOANCHI",contentValues,"id = ?",new String[]{khoanChi.getId()+""});
        if(kq >0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean themKhoanChi(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hang_muc",khoanChi.getHangMuc());
        contentValues.put("ghi_chu",khoanChi.getGhiChu());
        String ngay = dateFormat.format(khoanChi.getNgay());
        contentValues.put("ngay",ngay);
        contentValues.put("so_tien",khoanChi.getSoTien());
        contentValues.put("ntID",khoanChi.getNtID());
        long kq=  sqLiteDatabase.insert("KHOANCHI",null,contentValues);
        if(kq >0){
            return true;
        }
        else {
            return false;
        }
    }
}
