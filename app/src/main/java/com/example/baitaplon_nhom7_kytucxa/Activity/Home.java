package com.example.baitaplon_nhom7_kytucxa.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.Adapter.ListviewSinVienAdapter;
import com.example.baitaplon_nhom7_kytucxa.MainActivity;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;
import com.example.baitaplon_nhom7_kytucxa.Utiti.TaiKhoan;

public class Home extends AppCompatActivity {
    TextView logOutbtn;
    RelativeLayout qlPhong, qlThietBi, qlDuyTriThietBi, qlDienNuoc,thanhToan, nhom7;
    Button qlDangKy,btnTaikhoan;
    Database db;
    String taikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhxa();
        db = new Database(this);
        db.addFloor();
        db.addPhong();
        db.addThietBi();

        Intent intentaa =getIntent();
        Bundle bundle = intentaa.getBundleExtra("dulieu");
        if(bundle !=null){
            taikhoan = bundle.getString("taikhoan");
        }
        btnTaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,TaiKhoanactivity.class);
                Bundle bundlele=new Bundle();
                bundlele.putString("taikhoan",taikhoan);
                intent.putExtra("dulieu",bundlele);
                startActivity(intent);

            }
        });


        qlPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,QuanliPhong.class);
                startActivity(intent);
            }
        });
        qlDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Dangky.class);
                startActivity(intent);
            }
        });

        logOutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Xác nhận đăng xuất");
                builder.setMessage("Bạn chắc chắn muốn đăng xuất không?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Home.this, MainActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });



        qlThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ThietBiActivity.class);
                startActivity(i);
            }
        });

        qlDuyTriThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DuyTriThietBiActivity.class);
                startActivity(i);
            }
        });



        qlDienNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DienNuocActivity.class);
                startActivity(i);
            }
        });

        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ThanhToanActivity.class);
                startActivity(i);
            }
        });

        nhom7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Nhom7Activity.class);
                startActivity(i);
            }
        });
    }

    public void onBackPressed() {
        // Here you want to show the user a dialog box
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // The user wants to leave - so dismiss the dialog and exit
                        finish();
                        dialog.dismiss();
                    }
                }).setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // The user is not sure, so you can exit or just stay
                dialog.dismiss();
            }
        }).show();

    }


    private void anhxa(){

       qlPhong= (RelativeLayout) findViewById(R.id.btnQuanliphong);
        qlDangKy = (Button) findViewById(R.id.btnQuanliDangky);
        logOutbtn = (TextView) findViewById(R.id.logout);
        qlThietBi = (RelativeLayout) findViewById(R.id.qlThietBi);
        qlDuyTriThietBi = (RelativeLayout) findViewById(R.id.qlDuyTriThietBi);
        qlDienNuoc = (RelativeLayout) findViewById(R.id.qlDienNuoc);
        thanhToan = (RelativeLayout) findViewById(R.id.qlThanhToan);
        nhom7 = (RelativeLayout) findViewById(R.id.nhom7);
        btnTaikhoan = (Button) findViewById(R.id.btnTaikhoan);
    }
}