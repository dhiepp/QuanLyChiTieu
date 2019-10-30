package nhom3.quanlychitieu.control;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienArrayAdapter extends ArrayAdapter<String> {
    private ArrayList<NguonTien> nguonTienList;

    public NguonTienArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);

        NguonTienControl nguonTienControl = new NguonTienControl(context);
        nguonTienList = nguonTienControl.getListNguonTien();
        for (NguonTien nguonTien : nguonTienList) {
            super.add(nguonTien.getTen());
        }
    }

    public NguonTien getNguonTien(int position) {
        return nguonTienList.get(position);
    }

    public int getPositionByID(int id) {
        for (int i=0; i<nguonTienList.size(); i++) {
            if (nguonTienList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
