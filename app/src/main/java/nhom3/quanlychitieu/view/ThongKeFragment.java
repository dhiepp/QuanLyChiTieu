package nhom3.quanlychitieu.view;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.control.ThongKeControl;

public class ThongKeFragment extends Fragment {
    private Context context;

    private RadioGroup radioGroup;
    private RadioButton rb_all, rb_time;
    private LinearLayout thoiGian;
    private Button btnStart,btnEnd;
    private TextView tgStart, tgEnd;

    ThongKeControl thongKeControl;

    public ThongKeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        context = root.getContext();

        radioGroup = root.findViewById(R.id.TK_rg);
        rb_all = root.findViewById(R.id.rb_all);
        rb_time = root.findViewById(R.id.rb_time);
        thoiGian = root.findViewById(R.id.TK_thoi_gian);
        btnStart = root.findViewById(R.id.btnStart);
        btnEnd = root.findViewById(R.id.btnEnd);
        tgStart = root.findViewById(R.id.tgStart);
        tgEnd = root.findViewById(R.id.tgEnd);
        TextView tongThu = root.findViewById(R.id.TK_thu);
        TextView tongChi = root.findViewById(R.id.TK_chi);
        TextView canDoi = root.findViewById(R.id.TK_can_doi);
        thongKeControl = new ThongKeControl(radioGroup,thoiGian,btnStart,btnEnd,tgStart,tgEnd, tongThu, tongChi, canDoi, context);
        radioGroup.setOnCheckedChangeListener(thongKeControl);
        btnStart.setOnClickListener(thongKeControl);
        btnEnd.setOnClickListener(thongKeControl);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
//        rb1.setChecked(true);
    }
}
