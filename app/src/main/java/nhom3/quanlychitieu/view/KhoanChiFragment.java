package nhom3.quanlychitieu.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.control.KhoanChiControl;
import nhom3.quanlychitieu.model.KhoanChi;

public class KhoanChiFragment extends Fragment {
    private KhoanChiControl khoanChiControl;
    private KhoanChiRecyclerAdapter khoanChiRecyclerAdapter;

    private Context context;
    private FloatingActionButton addBtn;

    public KhoanChiFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
        context = root.getContext();
        khoanChiControl = new KhoanChiControl(context);
        khoanChiRecyclerAdapter = new KhoanChiRecyclerAdapter(this);

        addBtn = root.findViewById(R.id.KC_add);

        //Xử lý hiển thị danh sách khoản chi
        RecyclerView listData = root.findViewById(R.id.KC_list);
        listData.setAdapter(khoanChiRecyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listData.setLayoutManager(layoutManager);
        listData.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        updateListData();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themKhoanChiDialog();
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
        ArrayList<KhoanChi> listKhoanChi = khoanChiControl.getListKhoanChi();
        khoanChiRecyclerAdapter.update(listKhoanChi);
    }

    public void themKhoanChiDialog() {
        View content = getLayoutInflater().inflate(R.layout.dialog_them_khoan, null, false);
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Thêm khoản chi").setView(content).create();

        khoanChiControl.themKhoanChi(content, dialog);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateListData();
            }
        });

        dialog.show();
    }

    public void xemKhoanChiDialog(KhoanChi khoanChi) {
        View content = getLayoutInflater().inflate(R.layout.dialog_xem_khoan, null, false);
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Thông tin khoản chi").setView(content).create();

        khoanChiControl.xemKhoanChi(content, dialog, khoanChi);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateListData();
            }
        });

        dialog.show();
    }
}
