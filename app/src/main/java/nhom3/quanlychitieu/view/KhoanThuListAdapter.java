package nhom3.quanlychitieu.view;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.model.KhoanThu;

public class KhoanThuListAdapter extends RecyclerView.Adapter<KhoanThuListAdapter.KhoanThuListViewHolder> {
    private KhoanThuFragment khoanThuFragment;
    private ArrayList<KhoanThu> listKhoanThu;

    public KhoanThuListAdapter(KhoanThuFragment khoanThuFragment) {
        this.khoanThuFragment = khoanThuFragment;
    }

    public void update(ArrayList<KhoanThu> listKhoanThu) {
        this.listKhoanThu = listKhoanThu;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KhoanThuListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_khoan, parent, false);
        return new KhoanThuListViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuListViewHolder holder, int position) {
        final KhoanThu khoanThu = listKhoanThu.get(position);

        holder.hangMuc.setText(khoanThu.getHangMuc());
        holder.soTien.setText(khoanThu.getSoTienString());
        holder.ngay.setText(DateFormat.getDateInstance().format(khoanThu.getNgay()));
        holder.ghiChu.setText(khoanThu.getGhiChu());

        //Event click item
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThuFragment.xemKhoanThuDialog(khoanThu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKhoanThu.size();
    }

    public static class KhoanThuListViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView hangMuc;
        public TextView soTien;
        public TextView ngay;
        public TextView ghiChu;

        public KhoanThuListViewHolder(View v) {
            super(v);

            view = v;
            hangMuc = v.findViewById(R.id.LKH_hang_muc);
            soTien = v.findViewById(R.id.LKH_so_tien);
            ngay = v.findViewById(R.id.LKH_ngay);
            ghiChu = v.findViewById(R.id.LKH_ghi_chu);

            //Mau sac icon ngau nhien
            TextView icon = v.findViewById(R.id.LKH_icon);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                icon.getBackground().setTint(color);
            }
        }
    }
}
