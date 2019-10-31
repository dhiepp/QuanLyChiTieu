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

import nhom3.quanlychitieu.model.KhoanThu;

public class KhoanThuData {

    private static String DATABASE_NAME = "data.db";
    private SQLiteDatabase sqLiteDatabase;
    private SimpleDateFormat dateFormat;

    public KhoanThuData(Context context){
        sqLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }

    public ArrayList<KhoanThu> getAllKhoanThu() {
        Cursor cursor = sqLiteDatabase.query("KHOANTHU",null,null,null,null,null,null);
        ArrayList<KhoanThu> arrayList= new ArrayList<KhoanThu>();
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
        return arrayList;
    }

    public int getTongAllKhoanThu() {
        String[] columns = new String[] {"so_tien"};
        int tong = 0;
        Cursor cursor = sqLiteDatabase.query("KHOANTHU", columns, null, null,null,null,null);

        while (cursor.moveToNext()){
            tong += cursor.getInt(0);
        }
        cursor.close();
        return tong;
    }

    public int getTongKhoanThuByDate(Date startDate, Date endDate) {
        String[] columns = new String[] {"so_tien"};
        String selection = "ngay >= ? AND ngay <= ?";
        String[] selectionArgs = new String[] {dateFormat.format(startDate), dateFormat.format(endDate)};
        int tong = 0;
        Cursor cursor = sqLiteDatabase.query("KHOANTHU", columns, selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()){
            tong += cursor.getInt(0);
        }
        cursor.close();
        return tong;
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
        String ngay = dateFormat.format(khoanThu.getNgay());
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
        contentValues.put("hang_muc",khoanThu.getHangMuc());
        contentValues.put("ghi_chu",khoanThu.getGhiChu());
        String ngay = dateFormat.format(khoanThu.getNgay());
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
