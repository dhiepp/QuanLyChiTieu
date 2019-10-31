package nhom3.quanlychitieu.control;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.database.KhoanChiData;
import nhom3.quanlychitieu.database.KhoanThuData;

public class ThongKeControl implements View.OnClickListener {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private Context context;
    private KhoanChiData khoanChiData;
    private KhoanThuData khoanThuData;

    private Button btnStart,btnEnd;
    private TextView tgStart, tgEnd, tongThu, tongChi, canDoi;

    private Calendar calendarStart, calendarEnd;

    public ThongKeControl(final Context context, View root){
        this.context = context;
        khoanChiData = new KhoanChiData(context);
        khoanThuData = new KhoanThuData(context);

        btnStart = root.findViewById(R.id.btnStart);
        btnEnd = root.findViewById(R.id.btnEnd);
        tgStart = root.findViewById(R.id.tgStart);
        tgEnd = root.findViewById(R.id.tgEnd);
        tongThu = root.findViewById(R.id.TKE_thu);
        tongChi = root.findViewById(R.id.TKE_chi);
        canDoi = root.findViewById(R.id.TKE_can_doi);

        //Mặc định chọn ngày hôm nay
        calendarStart = Calendar.getInstance();
        calendarEnd = Calendar.getInstance();
        tgStart.setText(dateFormat.format(calendarStart.getTime()));
        tgEnd.setText(dateFormat.format(calendarEnd.getTime()));

        //Xử lý sự kiện click chọn ngày
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
    }

    public void updateData() {
        int thu = khoanThuData.getTongAllKhoanThu();
        int chi = khoanChiData.getTongAllKhoanChi();
        tongThu.setText(NumberFormat.getInstance().format(thu) + " đ");
        tongChi.setText(NumberFormat.getInstance().format(chi) + " đ");
        canDoi.setText(NumberFormat.getInstance().format(thu-chi) + " đ");
    }

    public void updateDataByDate() {
        Date dateStart = calendarStart.getTime();
        Date dateEnd = calendarEnd.getTime();
        int thu = khoanThuData.getTongKhoanThuByDate(dateStart, dateEnd);
        int chi = khoanChiData.getTongKhoanChiByDate(dateStart, dateEnd);

        tongThu.setText(NumberFormat.getInstance().format(thu) + " đ");
        tongChi.setText(NumberFormat.getInstance().format(chi) + " đ");
        canDoi.setText(NumberFormat.getInstance().format(thu-chi) + " đ");
    }

    @Override
    public void onClick(View view) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        switch (view.getId()){
           case R.id.btnStart :
               new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                       calendarStart.set(year, month, day);
                       tgStart.setText(dateFormat.format(calendarStart.getTime()));

                       updateDataByDate();
                   }
               },year,month,dayOfMonth).show();
               break;
           case R.id.btnEnd :
               new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                       calendarEnd.set(year, month, day);
                       tgEnd.setText(dateFormat.format(calendarEnd.getTime()));

                       updateDataByDate();
                   }
               },year,month,dayOfMonth).show();
               break;
        }
    }
}
