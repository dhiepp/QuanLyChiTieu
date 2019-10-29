package nhom3.quanlychitieu.control;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.database.NguonTienData;
import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienControl {
    private NguonTienData nguonTienData;

    public NguonTienControl(Context context) {
        nguonTienData = new NguonTienData(context);
    }

    public ArrayList<NguonTien> getListNguonTien() {
        return nguonTienData.getAllNguonTien();
    }

    public void themNguonTien(final View content, final AlertDialog dialog) {
        final EditText ten = content.findViewById(R.id.TNT_ten);
        final EditText mieuTa = content.findViewById(R.id.TNT_mieu_ta);
        final EditText soDu = content.findViewById(R.id.TNT_so_du);
        Button luuBtn = content.findViewById(R.id.TNT_luu);
        Button huyBtn = content.findViewById(R.id.TNT_huy);

        luuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguonTien nguonTien = new NguonTien();
                nguonTien.setSoDu(Integer.parseInt(soDu.getText().toString()));
                nguonTien.setTen(ten.getText().toString());
                nguonTien.setMieuTa(mieuTa.getText().toString());

                nguonTienData.themNguonTien(nguonTien);
                dialog.dismiss();
            }
        });

        huyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void xemNguonTien(final View content, final AlertDialog dialog, final NguonTien nguonTien) {
        final EditText ten = content.findViewById(R.id.XNT_ten);
        final EditText mieuTa = content.findViewById(R.id.XNT_mieu_ta);
        final EditText soDu = content.findViewById(R.id.XNT_so_du);
        Button luuBtn = content.findViewById(R.id.XNT_luu);
        Button xoaBtn = content.findViewById(R.id.XNT_xoa);
        Button huyBtn = content.findViewById(R.id.XNT_huy);

        ten.setText(nguonTien.getTen());
        mieuTa.setText(nguonTien.getMieuTa());
        soDu.setText(String.valueOf(nguonTien.getSoDu()));

        luuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguonTien.setSoDu(Integer.parseInt(soDu.getText().toString()));
                nguonTien.setTen(ten.getText().toString());
                nguonTien.setMieuTa(mieuTa.getText().toString());

                nguonTienData.suaNguonTien(nguonTien);
                dialog.dismiss();
            }
        });

        xoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguonTienData.xoaNguonTien(String.valueOf(nguonTien.getId()));
                dialog.dismiss();
            }
        });

        huyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
