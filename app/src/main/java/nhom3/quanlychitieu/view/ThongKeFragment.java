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


    private LinearLayout ln;
    private RadioGroup rg;
    private RadioButton rb1,rb2;
    private Button btStart,btEnd;
    private TextView tv1, tv2;

    ThongKeControl thongKeControl;

    public ThongKeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        context = root.getContext();

        ln = root.findViewById(R.id.bg_linear);
        rg = root.findViewById(R.id.rg);
        rb1 = root.findViewById(R.id.rb_all);
        rb2 = root.findViewById(R.id.rb_time);
        btStart = root.findViewById(R.id.btnStart);
        btEnd = root.findViewById(R.id.btnEnd);
        tv1 = root.findViewById(R.id.tvStart);
        tv2 = root.findViewById(R.id.tvEnd);

        thongKeControl = new ThongKeControl(rg,ln,btStart,btEnd,tv1,tv2,context);
        rg.setOnCheckedChangeListener(thongKeControl);
        btStart.setOnClickListener(thongKeControl);
        btEnd.setOnClickListener(thongKeControl);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        rb1.setChecked(true);
    }
}
