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
import nhom3.quanlychitieu.view.ThongKeFragment;

public class ThongKeControl implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private KhoanChiData khoanChiData;
    private KhoanThuData khoanThuData;

    private RadioGroup radioGroup;
    private RadioButton rb_all, rb_time;
    private LinearLayout thoiGian;
    private Button btnStart,btnEnd;
    private TextView tgStart, tgEnd, tongThu, tongChi, canDoi;

    private Context context;
    Calendar calendarStart, calendarEnd;

    public ThongKeControl(RadioGroup radioGroup, LinearLayout thoiGian, Button btnStart, Button btnEnd,
            TextView tgStart, TextView tgEnd, TextView tongThu, TextView tongChi, TextView canDoi ,Context context){
        khoanChiData = new KhoanChiData(context);
        khoanThuData = new KhoanThuData(context);

        this.radioGroup = radioGroup;
        this.thoiGian = thoiGian;
        this.btnStart = btnStart;
        this.btnEnd = btnEnd;
        this.tgStart = tgStart;
        this.tgEnd = tgEnd;
        this.context = context;
        this.tongThu = tongThu;
        this.tongChi = tongChi;
        this.canDoi = canDoi;

        calendarStart = Calendar.getInstance();
        calendarEnd = Calendar.getInstance();
        updateData();
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

                       int thu = khoanThuData.getTongAllKhoanThu();
                       int chi = khoanChiData.getTongAllKhoanChi();
                       updateDataByDate();
                   }
               },year,month,dayOfMonth).show();
               break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i==R.id.rb_all){
            thoiGian.setVisibility(View.GONE);
            updateData();
        }else{
            thoiGian.setVisibility(View.VISIBLE);

        }
    }

    private void updateData() {
        int thu = khoanThuData.getTongAllKhoanThu();
        int chi = khoanChiData.getTongAllKhoanChi();
        tongThu.setText(NumberFormat.getInstance().format(thu) + " đ");
        tongChi.setText(NumberFormat.getInstance().format(chi) + " đ");
        canDoi.setText(NumberFormat.getInstance().format(thu-chi) + " đ");
    }

    private void updateDataByDate() {
        Date dateStart = calendarStart.getTime();
        Date dateEnd = calendarEnd.getTime();
        int thu = khoanThuData.getTongKhoanThuByDate(dateStart, dateEnd);
        int chi = khoanChiData.getTongKhoanChiByDate(dateStart, dateEnd);

        tongThu.setText(NumberFormat.getInstance().format(thu) + " đ");
        tongChi.setText(NumberFormat.getInstance().format(chi) + " đ");
        canDoi.setText(NumberFormat.getInstance().format(thu-chi) + " đ");
    }


//    public void radioGroupChange(){
//
//    }
}
