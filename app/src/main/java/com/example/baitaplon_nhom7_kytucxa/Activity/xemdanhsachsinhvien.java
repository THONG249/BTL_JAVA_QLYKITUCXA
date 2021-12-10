package com.example.baitaplon_nhom7_kytucxa.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_nhom7_kytucxa.Adapter.ListviewSinVienAdapter;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Nguoi;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class xemdanhsachsinhvien extends AppCompatActivity {
    ListView lvsinhvien;
    TextView tenphong;
    ListviewSinVienAdapter adapter;
    ArrayList<Nguoi> listsv;
    Database database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_sinhvien);
        database = new Database(this);
        lvsinhvien =(ListView) findViewById(R.id.lvsv) ;
        tenphong = (TextView) findViewById(R.id.textdanhsachsv) ;
        Intent intent =getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        if(bundle !=null){
            Phong phong = (Phong) bundle.getSerializable("sinhvien");
            listsv = database.getListSvTrongphong(phong.getMaphong());
            adapter = new ListviewSinVienAdapter(xemdanhsachsinhvien.this,R.layout.custom_listview_sinhvien,listsv);
            lvsinhvien.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            tenphong.setText("Danh sách sinh viên phòng: "+phong.getMaphong()+"");
        }


}}
