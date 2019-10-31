package nhom3.quanlychitieu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Date;

import nhom3.quanlychitieu.database.KhoanThuData;
import nhom3.quanlychitieu.model.KhoanThu;
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

        // add Tab layout main
        tabLayout = findViewById(R.id.tab_layout);
    //    tabLayout.setupWithViewPager(viewPager);
        viewPager = findViewById(R.id.view_pager);
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        // add fragment
        adapter.addFragment(new NguonTienFragment(),"Nguồn Tiền");
        adapter.addFragment(new KhoanThuFragment(),"Khoản Thu");
        adapter.addFragment(new KhoanChiFragment(),"Khoản Chi");
        adapter.addFragment(new ThongKeFragment(),"Thống Kê");
        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_balance_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_trending_up_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_trending_down_white_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_insert_chart_white_24dp);



    }
}
