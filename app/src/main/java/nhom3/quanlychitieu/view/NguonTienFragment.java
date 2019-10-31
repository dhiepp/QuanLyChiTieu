package nhom3.quanlychitieu.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.control.NguonTienControl;
import nhom3.quanlychitieu.model.NguonTien;

public class NguonTienFragment extends Fragment {
    private NguonTienControl nguonTienControl;
    private NguonTienRecyclerAdapter nguonTienRecyclerAdapter;

    private Context context;
    private TextView tong;
    private FloatingActionButton addBtn;

    public NguonTienFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nguon_tien, container, false);
        context = root.getContext();
        nguonTienControl = new NguonTienControl(context);
        nguonTienRecyclerAdapter = new NguonTienRecyclerAdapter(this);

        tong = root.findViewById(R.id.NT_total);
        addBtn = root.findViewById(R.id.NT_add);

        //Xử lý hiển thị danh sách nguồn tiền
        RecyclerView listData = root.findViewById(R.id.NT_list);
        listData.setAdapter(nguonTienRecyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listData.setLayoutManager(layoutManager);
        listData.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themNguonTienDialog();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListData();
    }

    public void updateListData() {
        ArrayList<NguonTien> listNguonTien = nguonTienControl.getListNguonTien();
        nguonTienRecyclerAdapter.update(listNguonTien);

        //Tính và hiển thị tổng số dư
        int tsd=0;
        for (NguonTien nt: listNguonTien) tsd += nt.getSoDu();
        tong.setText("Tổng số dư: " + NumberFormat.getInstance().format(tsd) + " đ");
    }

    public void themNguonTienDialog() {
        View content = getLayoutInflater().inflate(R.layout.dialog_them_nguon_tien, null, false);
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Thêm nguồn tiền").setView(content).create();

        nguonTienControl.themNguonTien(content, dialog);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateListData();
            }
        });

        dialog.show();
    }

    public void xemNguonTienDialog(NguonTien nguonTien) {
        View content = getLayoutInflater().inflate(R.layout.dialog_xem_nguon_tien, null, false);
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Thông tin nguồn tiền").setView(content).create();

        nguonTienControl.xemNguonTien(content, dialog, nguonTien);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateListData();
            }
        });

        dialog.show();
    }
}
