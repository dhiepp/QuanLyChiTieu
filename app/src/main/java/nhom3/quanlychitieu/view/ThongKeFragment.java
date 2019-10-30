package nhom3.quanlychitieu.view;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.control.ThongKeControl;

public class ThongKeFragment extends Fragment {
    private Context context;

    private Button btStart, btEnd;
    private LinearLayout ln;
    private RadioGroup rg;
    private RadioButton rbAll, rbDate;
    private ThongKeControl thongKeControl;
    private TextView tv1, tv2;

    int year, month, dayOfMonth;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    public ThongKeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        context = root.getContext();

        ln= root.findViewById(R.id.bg_linear);
        btStart = root.findViewById(R.id.btnStart);
        btEnd = root.findViewById(R.id.btnEnd);
        rbAll = root.findViewById(R.id.rb_all);

        rbDate = root.findViewById(R.id.rb_time);
        rg = root.findViewById(R.id.rg);
        tv1 = root.findViewById(R.id.tvStart);
        tv2 = root.findViewById(R.id.tvEnd);

        thongKeControl = new ThongKeControl(rg,ln,btStart,btEnd,tv1,tv2,context);
        rg.setOnCheckedChangeListener(thongKeControl);
        btStart.setOnClickListener(thongKeControl);
        btEnd.setOnClickListener(thongKeControl);

        return root;
    }



//    @Override
//    public void onStop() {
//        super.onStop();
//        rbAll.setChecked(true);
//        //rbDate.setChecked(false);
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        rbAll.setChecked(true);
//    }


    @Override
    public void onResume() {
        super.onResume();
        rbAll.setChecked(true);
    }
}
