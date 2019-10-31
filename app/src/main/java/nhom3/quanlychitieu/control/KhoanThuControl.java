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
import nhom3.quanlychitieu.database.KhoanThuData;
import nhom3.quanlychitieu.database.NguonTienData;
import nhom3.quanlychitieu.model.KhoanThu;
import nhom3.quanlychitieu.model.NguonTien;

public class KhoanThuControl {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private Context context;
    private KhoanThuData khoanThuData;
    private NguonTienData nguonTienData;

    public KhoanThuControl(Context context) {
        this.context = context;
        khoanThuData = new KhoanThuData(context);
        nguonTienData = new NguonTienData(context);
    }

    public ArrayList<KhoanThu> getListKhoanThu() {
        return khoanThuData.getAllKhoanThu();
    }

    public void themKhoanThu(final View content, final AlertDialog dialog) {
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
                NguonTien nguonTien = nguonTienArrayAdapter.getNguonTien(nguonTienSpinner.getSelectedItemPosition());
                int tienThu = Integer.parseInt(soTien.getText().toString());

                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setNtID(nguonTien.getId());
                khoanThu.setNgay(selectedDate.getTime());
                khoanThu.setHangMuc(hangMuc.getText().toString());
                khoanThu.setGhiChu(ghiChu.getText().toString());
                khoanThu.setSoTien(tienThu);

                //Cộng thêm số dư từ khoản thu
                nguonTien.setSoDu(nguonTien.getSoDu() + tienThu);

                //Xử lý ngoại lệ: thêm không thành công
                if (!khoanThuData.themKhoanThu(khoanThu) || !nguonTienData.suaNguonTien(nguonTien)) {
                    Toast.makeText(context, "Thêm khoản thu không thành công!", Toast.LENGTH_SHORT).show();
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

    public void xemKhoanThu(final View content, final AlertDialog dialog, final KhoanThu khoanThu) {
        final EditText nguonTienTxt = content.findViewById(R.id.XKH_nguon_tien);
        final Button ngayBtn = content.findViewById(R.id.XKH_ngay);
        final EditText hangMuc = content.findViewById(R.id.XKH_hang_muc);
        final EditText ghiChu = content.findViewById(R.id.XKH_ghi_chu);
        final EditText soTien = content.findViewById(R.id.XKH_so_tien);
        Button luuBtn = content.findViewById(R.id.XKH_luu);
        Button xoaBtn = content.findViewById(R.id.XKH_xoa);
        Button huyBtn = content.findViewById(R.id.XKH_huy);

        //Đặt nguồn tiền theo ID (Không cho thay đổi)
        final NguonTien nguonTien = nguonTienData.getNguonTienByID(khoanThu.getNtID());
        nguonTienTxt.setText(nguonTien.getTen());
        nguonTienTxt.setEnabled(false);

        //Đặt các thông tin đã có
        ngayBtn.setText(khoanThu.getNgayString());
        hangMuc.setText(khoanThu.getHangMuc());
        ghiChu.setText(khoanThu.getGhiChu());
        soTien.setText(String.valueOf(khoanThu.getSoTien()));

        //Xử lý chọn ngày
        final Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(khoanThu.getNgay());
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
                int tienThu = Integer.parseInt(soTien.getText().toString());
                int tienChinh = tienThu - khoanThu.getSoTien();

                khoanThu.setNgay(selectedDate.getTime());
                khoanThu.setHangMuc(hangMuc.getText().toString());
                khoanThu.setGhiChu(ghiChu.getText().toString());
                khoanThu.setSoTien(tienThu);

                //Điều chỉnh số tiền của nguồn tiền
                nguonTien.setSoDu(nguonTien.getSoDu() + tienChinh);

                //Xử lý ngoại lệ: sửa không thành công
                if (!khoanThuData.suaKhoanThu(khoanThu) || !nguonTienData.suaNguonTien(nguonTien)) {
                    Toast.makeText(dialog.getContext(), "Sửa khoản thu không thành công!", Toast.LENGTH_SHORT).show();
                };
                dialog.dismiss();
            }
        });

        xoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vì xóa nên phải trừ đi số tiền của khoản thu
                int tienChinh = Integer.parseInt(soTien.getText().toString());
                nguonTien.setSoDu(nguonTien.getSoDu() - tienChinh);

                //Xử lý ngoại lệ: xóa không thành công
                if (!khoanThuData.xoaKhoanThu(String.valueOf(khoanThu.getId())) || !nguonTienData.suaNguonTien(nguonTien)) {
                    Toast.makeText(dialog.getContext(), "Xóa khoản thu không thành công!", Toast.LENGTH_SHORT).show();
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
