package khaind.cntt61.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ListData;
    ListView listView;
    ArrayAdapter adapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tao moi/mo CSDL
        db = openOrCreateDatabase("book.db", MODE_PRIVATE, null);

        TaoCSDL();
        NapSachVaoListView();
    }

    public void TaoCSDL()
    {
        //tao moi/mo CSDL

        //Tao bang
        String SQLtaobang = "CREATE TABLE BOOKS(BookID integer PRIMARY KEY," +
                " Bookname text," +
                " Page integer," +
                " Price Float," +
                " Description text);\n";
        db.execSQL(SQLtaobang);

        //Xoa bang
        String SQLxoabang = "DROP TABLE IF EXISTS BOOKS;\n";
        db.execSQL(SQLxoabang);

        //Them du lieu
        String SQLtext1 = "INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'Sach ve Java');\n";
        db.execSQL(SQLtext1);
        String SQLtext2 = "INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.99, 'Sach ve Android');\n";
        db.execSQL(SQLtext2);
        String SQLtext3 = "INSERT INTO BOOKS VALUES(3, 'Hoc lam giau', 120, 4.99, 'Sach doc cho vui');\n";
        db.execSQL(SQLtext3);
        String SQLtext4 = "INSERT INTO BOOKS VALUES(4, 'Tu dien Anh-Viet', 1000, 29.99, 'Tu dien 100.000 tu');\n";
        db.execSQL(SQLtext4);
        String SQLtext5 = "INSERT INTO BOOKS VALUES(5, 'CNXH', 300, 3.99, 'Sach CNXH');\n";
        db.execSQL(SQLtext5);
    }

    public void NapSachVaoListView()
    {
        listView = (ListView) findViewById(R.id.lv);
        ListData = new ArrayList<String>();

        //mo db, select du lieu r dua vao ListData
        Cursor cs = db.rawQuery("SELECT * FROM BOOKS", null);
        cs.moveToFirst();
        while(true)
        {
            if(cs.isLast()==false)
            {
                cs.moveToNext();
                break;
            }

            //lay ma sach
            Integer masach = cs.getInt(0);
            String tensach = cs.getString(1);
            //Integer sotrang = cs.getInt(2);
            Float gia = cs.getFloat(3);
            //String gthieu = cs.getString(4);

            String ms = String.valueOf(masach);
            //String page = String.valueOf(sotrang);
            String price = String.valueOf(gia);

            String kq = ms + "---" + tensach + "---" + price;
            ListData.add(kq);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListData);
        listView.setAdapter(adapter);
    }

    public void ThemMoi(int ms, String ten, int sotrang, float gia, String gthieu)
    {
        String SQLthem = "INSERT INTO BOOKS VALUES(" + ms + ", "
                                                    + ten + ", "
                                                    + sotrang + ", "
                                                    + gia + ", "
                                                    + gthieu +";\n";
        db.execSQL(SQLthem);
    }

    public void XoaSach(int ms)
    {

    }

    public void CapNhat(int ms, String tenmoi, int sotrangmoi, float giamoi, String gthieumoi)
    {

    }
}