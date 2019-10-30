package nhom3.quanlychitieu.control;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienArrayAdapter extends ArrayAdapter<String> {
    private ArrayList<NguonTien> nguonTienList;

    public NguonTienArrayAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_dropdown_item);

        NguonTienControl nguonTienControl = new NguonTienControl(context);
        nguonTienList = nguonTienControl.getListNguonTien();
        for (NguonTien nguonTien : nguonTienList) {
            super.add(nguonTien.getTen());
        }
    }

    public NguonTien getNguonTien(int position) {
        return nguonTienList.get(position);
    }
}
