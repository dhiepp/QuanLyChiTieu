package nhom3.quanlychitieu.view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.database.NguonTienData;
import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienFragment extends Fragment {
    private Context context;

    public NguonTienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nguon_tien, container, false);
        context = root.getContext();
        return root;
    }

    private void themNguonTienDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Thêm nguồn tiền");
        dialog.setContentView(R.layout.dialog_them_nguon_tien);

        final EditText soDu = dialog.findViewById(R.id.TNT_so_du);
        final EditText ten = dialog.findViewById(R.id.TNT_ten);
        final EditText mieuTa = dialog.findViewById(R.id.TNT_mieu_ta);
        Button luuBtn = dialog.findViewById(R.id.TNT_luu);
        Button huyBtn = dialog.findViewById(R.id.TNT_huy);

        luuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguonTien nguonTien = new NguonTien();
                nguonTien.setSoDu(Integer.parseInt(soDu.getText().toString()));
                nguonTien.setTen(ten.getText().toString());
                nguonTien.setMieuTa(mieuTa.getText().toString());

                NguonTienData nguonTienData = new NguonTienData(context);
                nguonTienData.themNguonTien(nguonTien);
                dialog.dismiss();
            }
        });

        huyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void xemNguonTienDiaglog(final NguonTien nguonTien) {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Chi tiết nguồn tiền");
        dialog.setContentView(R.layout.dialog_xem_nguon_tien);

        final EditText soDu = dialog.findViewById(R.id.XNT_so_du);
        final EditText ten = dialog.findViewById(R.id.XNT_ten);
        final EditText mieuTa = dialog.findViewById(R.id.XNT_mieu_ta);
        Button luuBtn = dialog.findViewById(R.id.XNT_luu);
        Button xoaBtn = dialog.findViewById(R.id.XNT_xoa);
        Button huyBtn = dialog.findViewById(R.id.XNT_huy);

        soDu.setText(nguonTien.getSoDu());
        ten.setText(nguonTien.getTen());
        mieuTa.setText(nguonTien.getMieuTa());

        luuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguonTien.setSoDu(Integer.parseInt(soDu.getText().toString()));
                nguonTien.setTen(ten.getText().toString());
                nguonTien.setMieuTa(mieuTa.getText().toString());

                NguonTienData nguonTienData = new NguonTienData(context);
                nguonTienData.suaNguonTien(nguonTien);
                dialog.dismiss();
            }
        });

        xoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguonTienData nguonTienData = new NguonTienData(context);
                nguonTienData.xoaNguonTien(String.valueOf(nguonTien.getId()));
                dialog.dismiss();
            }
        });

        huyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
