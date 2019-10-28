package nhom3.quanlychitieu.view;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;

import nhom3.quanlychitieu.R;
import nhom3.quanlychitieu.ThongKeAdapter;

public class ThongKeFragment extends Fragment {
    private Context context;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ThongKeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        context = root.getContext();

        tabLayout = root.findViewById(R.id.tab_layout_thongKe);
        viewPager = root.findViewById(R.id.view_pager_thongKe);
        ThongKeAdapter adapter = new ThongKeAdapter(getFragmentManager());
        adapter.addFragment(new ThongKeAllFragment(), "Tất cả");
        adapter.addFragment(new ThongKeThangFragment(),"Tháng");
        adapter.addFragment(new ThongKeNamFragment(),"Năm");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }


}
