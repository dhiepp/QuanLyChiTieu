package nhom3.quanlychitieu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienData {
    private static String DATABASE_NAME = "data.db";
    SQLiteDatabase sqLiteDatabase = null;

    public NguonTienData(Context context){
        sqLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        //Bật hỗ trợ Khóa Ngoại cho SQLite
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
    }

    public ArrayList<NguonTien> getAllNguonTien() {
        Cursor cursor = sqLiteDatabase.query("NGUONTIEN",null,null,null,null,null,null);
        ArrayList<NguonTien> arrayList= new ArrayList<NguonTien>();
        arrayList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String mieuTa = cursor.getString(2);
            int soDu = cursor.getInt(3);

            NguonTien nguonTien = new NguonTien();
            nguonTien.setId(id);
            nguonTien.setTen(ten);
            nguonTien.setMieuTa(mieuTa);
            nguonTien.setSoDu(soDu);
            arrayList.add(nguonTien);
        }
        cursor.close();
        return  arrayList;
    }

    public NguonTien getNguonTienByID(int id) {
        Cursor cursor = sqLiteDatabase.query("NGUONTIEN",null,"id=" + id,
                null,null,null,null);
        NguonTien nguonTien = null;
        while (cursor.moveToNext()){
            String ten = cursor.getString(1);
            String mieuTa = cursor.getString(2);
            int soDu = cursor.getInt(3);

            nguonTien = new NguonTien();
            nguonTien.setId(id);
            nguonTien.setTen(ten);
            nguonTien.setMieuTa(mieuTa);
            nguonTien.setSoDu(soDu);
        }
        cursor.close();
        return nguonTien;
    }

    public boolean xoaNguonTien(String id) {
        try {
            sqLiteDatabase.delete("NGUONTIEN","id=?",new String[]{id});
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean suaNguonTien(NguonTien nguonTien) {
        String id = String.valueOf(nguonTien.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten",nguonTien.getTen());
        contentValues.put("mieu_ta",nguonTien.getMieuTa());
        contentValues.put("so_du",nguonTien.getSoDu());
        long kq= sqLiteDatabase.update("NGUONTIEN",contentValues,"id = ?",new String[]{id});
        if(kq >0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean themNguonTien(NguonTien nguonTien) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten",nguonTien.getTen());
        contentValues.put("mieu_ta",nguonTien.getMieuTa());
        contentValues.put("so_du",nguonTien.getSoDu());
        long kq=  sqLiteDatabase.insert("NGUONTIEN",null,contentValues);
        if(kq >0){
            return true;
        }
        else {
            return false;
        }
    }
}
