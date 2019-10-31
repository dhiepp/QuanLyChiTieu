package nhom3.quanlychitieu.view;

import android.content.ClipboardManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienListAdapter extends RecyclerView.Adapter<NguonTienListAdapter.NguonTienListViewHolder> {
    private ArrayList<NguonTien> listNguonTien = new ArrayList<>();
    private String dataType;

    public NguonTienListAdapter(ArrayList<NguonTien> ntl) {
        listNguonTien = ntl;
//        NguonTien nguonTien = new NguonTien();
//        nguonTien.setTen("Hello");
//        nguonTien.setSoDu(990000);
//        nguonTien.setMieuTa("Cuong 2 an dau buoi");
//        NguonTien nguonTien2 = new NguonTien();
//        nguonTien2.setTen("Bitches");
//        nguonTien2.setSoDu(880000);
//        nguonTien2.setMieuTa("Cuong 2 an dau cut");
//        NguonTien nguonTien3 = new NguonTien();
//        nguonTien3.setTen("Bitches");
//        nguonTien3.setSoDu(880000);
//        nguonTien3.setMieuTa("Cuong 2 an dau cut");
//        listNguonTien.add(nguonTien);
//        listNguonTien.add(nguonTien2);
//        listNguonTien.add(nguonTien3);
    }

    @NonNull
    @Override
    public NguonTienListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_nguon_tien, parent, false);


        dataRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new NguonTienListViewHolder(dataRow);
    }

    @Override
    public void onBindViewHolder(@NonNull NguonTienListViewHolder holder, int position) {
        NguonTien nguonTien = listNguonTien.get(position);
        holder.ten.setText(nguonTien.getTen());
        holder.soDu.setText(String.valueOf(nguonTien.getSoDuString()));
        holder.mieuTa.setText(nguonTien.getMieuTa());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            holder.icon.getBackground().setTint(color);
        }

    }

    @Override
    public int getItemCount() {
        return listNguonTien.size();
    }

    public static class NguonTienListViewHolder extends RecyclerView.ViewHolder {
        public TextView icon;
        public TextView ten;
        public TextView soDu;
        public TextView mieuTa;

        public NguonTienListViewHolder(View v) {
            super(v);

            icon = v.findViewById(R.id.LNT_icon);
            ten = v.findViewById(R.id.LNT_ten);
            soDu = v.findViewById(R.id.LNT_so_du);
            mieuTa = v.findViewById(R.id.LNT_mieu_ta);
        }
    }
}
