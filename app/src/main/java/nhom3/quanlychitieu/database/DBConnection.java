package nhom3.quanlychitieu.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DBConnection {
    private static String DATABASE_NAME = "data.db";
    private static final String DB_PATH_SUFFIX = "/databases/";

    public static void xuLiSaoChepSQL(Context context) {
        File file = context.getDatabasePath(DATABASE_NAME);
        if(!file.exists()){
            try {
                copyData(context);
                Toast.makeText(context,"Sucess",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void copyData(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outputFile = layDuongDan(context);
            File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX );
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (Exception e){
            Log.e("LOI_SAO_CHEP",e.toString());
        }
    }

    public static String layDuongDan(Context context){
        return  context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }


}
