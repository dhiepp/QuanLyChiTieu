package nhom3.quanlychitieu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Date;

import nhom3.quanlychitieu.database.KhoanThuData;
import nhom3.quanlychitieu.model.KhoanThu;
import nhom3.quanlychitieu.database.DBConnection;

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
        KhoanThuData khoanThuDAO = new KhoanThuData(MainActivity.this);
        boolean b =khoanThuDAO.themKhoanThu(khoanThu);
        Toast.makeText(MainActivity.this,b+"",Toast.LENGTH_SHORT).show();


        new AlertDialog.Builder(this).setTitle("Thông tin nguồn tiền")
                .setView(getLayoutInflater().inflate(R.layout.dialog_xem_nguon_tien, null, false)).show();
    }
}
