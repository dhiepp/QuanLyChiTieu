package nhom3.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Date;

import nhom3.quanlychitieu.DAO.KhoanThuDAO;
import nhom3.quanlychitieu.DAO.NguonTienDAO;
import nhom3.quanlychitieu.Model.KhoanThu;
import nhom3.quanlychitieu.Model.NguonTien;
import nhom3.quanlychitieu.db.DBConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBConnection.xuLiSaoChepSQL(MainActivity.this);
        KhoanThu khoanThu =new KhoanThu();
        khoanThu.setId(1);
        khoanThu.setGhiChu("dfs");
        khoanThu.setHangMuc("adsd");
        khoanThu.setNgay(new Date());
        khoanThu.setNtID(1);
        khoanThu.setSoTien(1234);
        KhoanThuDAO khoanThuDAO = new KhoanThuDAO(MainActivity.this);
        boolean b =khoanThuDAO.themKhoanThu(khoanThu);
        Toast.makeText(MainActivity.this,b+"",Toast.LENGTH_SHORT).show();
    }
}
