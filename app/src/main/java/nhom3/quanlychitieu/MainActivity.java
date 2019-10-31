package nhom3.quanlychitieu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import nhom3.quanlychitieu.database.DBConnection;
import nhom3.quanlychitieu.view.KhoanChiFragment;
import nhom3.quanlychitieu.view.KhoanThuFragment;
import nhom3.quanlychitieu.view.NguonTienFragment;
import nhom3.quanlychitieu.view.ThongKeFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBConnection.xuLiSaoChepSQL(MainActivity.this);
        initView();

    }

    private void initView(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        //Tạo Adapter và thêm Fragment
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NguonTienFragment(),"Nguồn Tiền");
        adapter.addFragment(new KhoanThuFragment(),"Khoản Thu");
        adapter.addFragment(new KhoanChiFragment(),"Khoản Chi");
        adapter.addFragment(new ThongKeFragment(),"Thống Kê");

        //Setup TabLayout với Adapter
        viewPager.setAdapter(adapter);
        //Giữ cho các Fragment không bị Destroy
        viewPager.setOffscreenPageLimit(adapter.getCount());

        //Set icon cho tab
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_balance_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_trending_up_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_trending_down_white_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_insert_chart_white_24dp);
    }
}
