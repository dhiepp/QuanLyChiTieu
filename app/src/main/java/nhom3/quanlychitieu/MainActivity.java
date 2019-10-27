package nhom3.quanlychitieu;

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

        initView();

        DBConnection.xuLiSaoChepSQL(MainActivity.this);
        KhoanThu khoanThu =new KhoanThu();
        khoanThu.setId(1);
        khoanThu.setGhiChu("dfs");
        khoanThu.setHangMuc("adsd");
        khoanThu.setNgay(new Date());
        khoanThu.setNtID(1);
        khoanThu.setSoTien(1234);
        KhoanThuData khoanThuDAO = new KhoanThuData(MainActivity.this);
        boolean b =khoanThuDAO.themKhoanThu(khoanThu);
        Toast.makeText(MainActivity.this,b+"",Toast.LENGTH_SHORT).show();
    }

    private void initView(){

        // add Tab layout main
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager = findViewById(R.id.view_pager);
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        // add fragment
        adapter.addFragment(new NguonTienFragment(),"Nguon Tien");
        adapter.addFragment(new KhoanThuFragment(),"Khoan Thu");
        adapter.addFragment(new KhoanChiFragment(),"Khoan chi");
        adapter.addFragment(new ThongKeFragment(),"Thong Ke");
        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }
}
