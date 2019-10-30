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
import java.util.Calendar;

import nhom3.quanlychitieu.R;

public class ThongKeControl implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup rb;
    RadioButton rb1,rb2;
    Button b1,b2;
    LinearLayout ln;
    TextView tv1,tv2;
    Context context;

    int year, month, dayOfMonth;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    public ThongKeControl(RadioGroup rb,LinearLayout ln,Button b1,Button b2,TextView tv1, TextView tv2,Context context){
        this.rb = rb;
        this.ln = ln;
        this.b1 = b1;
        this.b2=b2;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        calendar = Calendar.getInstance();
        year= calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
       switch (view.getId()){
           case R.id.btnStart :
               datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tv1.setText(day+"-"+(month+1)+"-"+year);
                   }
               },year,month,dayOfMonth);
               datePickerDialog.show();
               break;
           case R.id.btnEnd :
               datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                       tv2.setText(day+"-"+(month+1)+"-"+year);
                   }
               },year,month,dayOfMonth);
               datePickerDialog.show();
               break;
       }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i==R.id.rb_all){
            ln.setVisibility(View.GONE);

        }else{
            ln.setVisibility(View.VISIBLE);
        }
    }


//    public void radioGroupChange(){
//
//    }
}
