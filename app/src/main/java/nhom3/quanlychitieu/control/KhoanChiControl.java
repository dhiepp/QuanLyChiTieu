package nhom3.quanlychitieu.control;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.database.KhoanChiData;
import nhom3.quanlychitieu.model.KhoanChi;
import nhom3.quanlychitieu.model.NguonTien;

public class KhoanChiControl {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private KhoanChiData khoanChiData;

    public KhoanChiControl(Context context) {
        khoanChiData = new KhoanChiData(context);
    }

    public ArrayList<KhoanChi> getListKhoanChi() {
        return khoanChiData.getAllKhoanChi();
    }
    public void themKhoanChi(final View content, final AlertDialog dialog) {
        final Context context = content.getContext();
        final Spinner nguonTienSpinner = content.findViewById(R.id.TKH_nguon_tien);
        final Button ngayBtn = content.findViewById(R.id.TKH_ngay);
        final EditText hangMuc = content.findViewById(R.id.TKH_hang_muc);
        final EditText ghiChu = content.findViewById(R.id.TKH_ghi_chu);
        final EditText soTien = content.findViewById(R.id.TKH_so_tien);
        Button luuBtn = content.findViewById(R.id.TKH_luu);
        Button huyBtn = content.findViewById(R.id.TKH_huy);

        final NguonTienArrayAdapter nguonTienArrayAdapter = new NguonTienArrayAdapter(context);
        nguonTienSpinner.setAdapter(nguonTienArrayAdapter);

        //Xử lý chọn ngày
        final Calendar selectedDate = Calendar.getInstance();
        ngayBtn.setText(dateFormat.format(selectedDate.getTime()));
        ngayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.set(year, month, dayOfMonth);
                        ngayBtn.setText(dateFormat.format(selectedDate.getTime()));
                    }
                }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        luuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý ngoại lệ: chưa nhập đủ
                if (hangMuc.length()==0 || soTien.length()==0) {
                    Toast.makeText(dialog.getContext(), "Bạn phải nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Lấy nguồn tiền đã chọn
                NguonTienControl nguonTienControl = new NguonTienControl(context);
                NguonTien nguonTien = nguonTienArrayAdapter.getNguonTien(nguonTienSpinner.getSelectedItemPosition());
                int tienChi = Integer.parseInt(soTien.getText().toString());

                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setNtID(nguonTien.getId());
                khoanChi.setNgay(selectedDate.getTime());
                khoanChi.setHangMuc(hangMuc.getText().toString());
                khoanChi.setGhiChu(ghiChu.getText().toString());
                khoanChi.setSoTien(tienChi);

                //Trừ đi số dư từ khoản chi
                nguonTien.setSoDu(nguonTien.getSoDu() - tienChi);

                //Xử lý ngoại lệ: thêm không thành công
                if (!khoanChiData.themKhoanChi(khoanChi) || !nguonTienControl.suaNguonTien(nguonTien)) {
                    Toast.makeText(context, "Thêm khoản chi không thành công!", Toast.LENGTH_SHORT).show();
                };
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

    public void xemKhoanChi(final View content, final AlertDialog dialog, final KhoanChi khoanChi) {
        final Context context = content.getContext();
        final EditText nguonTienTxt = content.findViewById(R.id.XKH_nguon_tien);
        final Button ngayBtn = content.findViewById(R.id.XKH_ngay);
        final EditText hangMuc = content.findViewById(R.id.XKH_hang_muc);
        final EditText ghiChu = content.findViewById(R.id.XKH_ghi_chu);
        final EditText soTien = content.findViewById(R.id.XKH_so_tien);
        Button luuBtn = content.findViewById(R.id.XKH_luu);
        Button xoaBtn = content.findViewById(R.id.XKH_xoa);
        Button huyBtn = content.findViewById(R.id.XKH_huy);

        //Đặt nguồn tiền theo ID (Không cho thay đổi)
        final NguonTienControl nguonTienControl = new NguonTienControl(context);
        final NguonTien nguonTien = nguonTienControl.getNguonTienByID(khoanChi.getNtID());
        nguonTienTxt.setText(nguonTien.getTen());
        nguonTienTxt.setEnabled(false);

        //Đặt các thông tin đã có
        ngayBtn.setText(khoanChi.getNgayString());
        hangMuc.setText(khoanChi.getHangMuc());
        ghiChu.setText(khoanChi.getGhiChu());
        soTien.setText(String.valueOf(khoanChi.getSoTien()));

        //Xử lý chọn ngày
        final Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(khoanChi.getNgay());
        ngayBtn.setText(dateFormat.format(selectedDate.getTime()));
        ngayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.set(year, month, dayOfMonth);
                        ngayBtn.setText(dateFormat.format(selectedDate.getTime()));
                    }
                }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        luuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý ngoại lệ: chưa nhập đủ
                if (hangMuc.length()==0 || soTien.length()==0) {
                    Toast.makeText(dialog.getContext(), "Bạn phải nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Số tiền điều chỉnh = số tiền mới nhập trừ đi số tiền cũ
                int tienChi = Integer.parseInt(soTien.getText().toString());
                int tienChinh = tienChi - khoanChi.getSoTien();

                khoanChi.setNgay(selectedDate.getTime());
                khoanChi.setHangMuc(hangMuc.getText().toString());
                khoanChi.setGhiChu(ghiChu.getText().toString());
                khoanChi.setSoTien(tienChi);

                //Điều chỉnh số tiền của nguồn tiền
                nguonTien.setSoDu(nguonTien.getSoDu() - tienChinh);

                //Xử lý ngoại lệ: sửa không thành công
                if (!khoanChiData.suaKhoanChi(khoanChi) || !nguonTienControl.suaNguonTien(nguonTien)) {
                    Toast.makeText(dialog.getContext(), "Sửa khoản chi không thành công!", Toast.LENGTH_SHORT).show();
                };
                dialog.dismiss();
            }
        });

        xoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vì xóa nên phải cộng vào số tiền của khoản chi
                int tienChinh = Integer.parseInt(soTien.getText().toString());
                nguonTien.setSoDu(nguonTien.getSoDu() + tienChinh);

                //Xử lý ngoại lệ: xóa không thành công
                if (!khoanChiData.xoaKhoanChi(String.valueOf(khoanChi.getId())) || !nguonTienControl.suaNguonTien(nguonTien)) {
                    Toast.makeText(dialog.getContext(), "Xóa khoản chi không thành công!", Toast.LENGTH_SHORT).show();
                };
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
