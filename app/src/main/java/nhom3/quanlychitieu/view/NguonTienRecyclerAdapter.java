package nhom3.quanlychitieu.view;

import android.graphics.Color;
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

public class NguonTienRecyclerAdapter extends RecyclerView.Adapter<NguonTienRecyclerAdapter.NguonTienListViewHolder> {
    private NguonTienFragment nguonTienFragment;
    private ArrayList<NguonTien> listNguonTien;

    public NguonTienRecyclerAdapter(NguonTienFragment nguonTienFragment) {
        this.nguonTienFragment = nguonTienFragment;
    }

    public void update(ArrayList<NguonTien> listNguonTien) {
        this.listNguonTien = listNguonTien;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NguonTienListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_nguon_tien, parent, false);
        return new NguonTienListViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull NguonTienListViewHolder holder, int position) {
        final NguonTien nguonTien = listNguonTien.get(position);
        holder.ten.setText(nguonTien.getTen());
        holder.soDu.setText(nguonTien.getSoDuString());
        holder.mieuTa.setText(nguonTien.getMieuTa());

        //Event click item
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguonTienFragment.xemNguonTienDialog(nguonTien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNguonTien.size();
    }

    public static class NguonTienListViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView ten;
        public TextView soDu;
        public TextView mieuTa;

        public NguonTienListViewHolder(View v) {
            super(v);

            view = v;
            ten = v.findViewById(R.id.LNT_ten);
            soDu = v.findViewById(R.id.LNT_so_du);
            mieuTa = v.findViewById(R.id.LNT_mieu_ta);

            //Mau sac icon ngau nhien
            TextView icon = v.findViewById(R.id.LNT_icon);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                icon.getBackground().setTint(color);
            }
        }
    }
}
