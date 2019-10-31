package nhom3.quanlychitieu.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import nhom3.quanlychitieu.model.KhoanChi;

public class KhoanChiRecyclerAdapter extends RecyclerView.Adapter<KhoanChiRecyclerAdapter.KhoanChiListViewHolder> {
    private KhoanChiFragment khoanChiFragment;
    private ArrayList<KhoanChi> listKhoanChi;

    public KhoanChiRecyclerAdapter(KhoanChiFragment khoanChiFragment) {
        this.khoanChiFragment = khoanChiFragment;
    }

    public void update(ArrayList<KhoanChi> listKhoanChi) {
        this.listKhoanChi = listKhoanChi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KhoanChiListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_khoan, parent, false);
        return new KhoanChiListViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final KhoanChiListViewHolder holder, int position) {
        final KhoanChi khoanChi = listKhoanChi.get(position);

        holder.hangMuc.setText(khoanChi.getHangMuc());
        holder.soTien.setText(khoanChi.getSoTienString());
        holder.ngay.setText(khoanChi.getNgayString());
        holder.ghiChu.setText(khoanChi.getGhiChu());

        //Sự kiện click vào item
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanChiFragment.xemKhoanChiDialog(khoanChi);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKhoanChi.size();
    }

    public static class KhoanChiListViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView hangMuc;
        public TextView soTien;
        public TextView ngay;
        public TextView ghiChu;

        public KhoanChiListViewHolder(View v) {
            super(v);

            view = v;
            hangMuc = v.findViewById(R.id.LKH_hang_muc);
            soTien = v.findViewById(R.id.LKH_so_tien);
            ngay = v.findViewById(R.id.LKH_ngay);
            ghiChu = v.findViewById(R.id.LKH_ghi_chu);

            //Màu sắc icon ngẫu nhiên
            TextView icon = v.findViewById(R.id.LKH_icon);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                Drawable drawable = v.getContext().getDrawable(R.drawable.ic_monetization_on_black_24dp).mutate();
                drawable.setTint(color);
                icon.setBackground(drawable);
            }
        }
    }
}
