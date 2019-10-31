package nhom3.quanlychitieu.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.control.ThongKeControl;

public class ThongKeFragment extends Fragment {
    private ThongKeControl thongKeControl;

    private Context context;
    RadioGroup radioGroup;
    private LinearLayout thoiGianLayout;

    public ThongKeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        context = root.getContext();
        thongKeControl = new ThongKeControl(context, root);

        radioGroup = root.findViewById(R.id.TKE_rg);
        thoiGianLayout = root.findViewById(R.id.TKE_thoi_gian);

        //Xử lý chọn tiêu chí thống kê khi click
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_all) {
                    thoiGianLayout.setVisibility(View.GONE);
                    thongKeControl.updateData();
                }
                if (checkedId == R.id.rb_time) {
                    thoiGianLayout.setVisibility(View.VISIBLE);
                    thongKeControl.updateDataByDate();
                }
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Khi resume kiểm tra xem đang chọn tiêu chí nào và update data tương ứng
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.rb_all) {
            thoiGianLayout.setVisibility(View.GONE);
            thongKeControl.updateData();
        }
        if (checkedId == R.id.rb_time) {
            thoiGianLayout.setVisibility(View.VISIBLE);
            thongKeControl.updateDataByDate();
        }

    }
}
