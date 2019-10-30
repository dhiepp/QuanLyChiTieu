package nhom3.quanlychitieu.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.control.KhoanThuControl;
import nhom3.quanlychitieu.control.KhoanThuControl;
import nhom3.quanlychitieu.model.KhoanThu;

public class KhoanThuFragment extends Fragment {
    private KhoanThuControl khoanThuControl;
    private KhoanThuListAdapter khoanThuListAdapter;

    private Context context;
    private FloatingActionButton addBtn;

    public KhoanThuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
        context = root.getContext();
        khoanThuControl = new KhoanThuControl(context);
        khoanThuListAdapter = new KhoanThuListAdapter(this);

        addBtn = root.findViewById(R.id.KT_add);

        //Xu ly danh sach khoan thu
        RecyclerView listData = root.findViewById(R.id.KT_list);
        listData.setAdapter(khoanThuListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listData.setLayoutManager(layoutManager);
        listData.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        updateListData();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themKhoanThuDialog();
            }
        });

        return root;
    }

    public void updateListData() {
        ArrayList<KhoanThu> listKhoanThu = khoanThuControl.getListKhoanThu();
        khoanThuListAdapter.update(listKhoanThu);
    }

    public void themKhoanThuDialog() {
        View content = getLayoutInflater().inflate(R.layout.dialog_them_khoan, null, false);
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Thêm khoản thu").setView(content).create();

        khoanThuControl.themKhoanThu(content, dialog);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateListData();
            }
        });

        dialog.show();
    }

    public void xemKhoanThuDialog(KhoanThu khoanThu) {
        View content = getLayoutInflater().inflate(R.layout.dialog_xem_khoan, null, false);
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Thông tin khoản thu").setView(content).create();

        khoanThuControl.xemKhoanThu(content, dialog, khoanThu);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateListData();
            }
        });

        dialog.show();
    }
}
