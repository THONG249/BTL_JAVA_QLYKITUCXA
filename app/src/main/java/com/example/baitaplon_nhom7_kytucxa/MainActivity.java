package com.example.baitaplon_nhom7_kytucxa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplon_nhom7_kytucxa.Activity.Home;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.TaiKhoan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button btnDangNhap,btnThoat;
EditText edtTaiKhoan,edtMatKhau;
ArrayList<TaiKhoan> listTaiKhoan;
Database database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);
       database.insertTaiKhoan(new TaiKhoan("2020506111","nhom7","Phạm Thanh Hiền","Giám đốc"));


        AnhXa();
        DangNhap();
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Xác nhận thoát");
                    builder.setMessage("Bạn chắc chắn muốn thoát không?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           finish();
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



    }
    private void AnhXa(){
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnThoat  =(Button) findViewById(R.id.btnThoat);

        edtTaiKhoan.setText("2020506111");
        edtMatKhau.setText("nhom7");
    }
    private void DangNhap(){
        listTaiKhoan = database.getListTaiKhoan();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTaiKhoan.getText().toString().equalsIgnoreCase("")||edtMatKhau.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(MainActivity.this,"Bạn chưa nhập đủ thông tin đăng nhập ! ",Toast.LENGTH_SHORT).show();

                }else
                {
                    String taikhoan = edtTaiKhoan.getText()+"";
                    String matkhau = edtMatKhau.getText()+"";
                    for (TaiKhoan tk: listTaiKhoan){
                        if(tk.getTaiKhoan().equalsIgnoreCase(taikhoan)==false || tk.getMatKhau().equalsIgnoreCase(matkhau)==false)
                        {
                            Toast.makeText(MainActivity.this,"Sai tên đăng nhập hoặc mật khẩu !",Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            Bundle b =new Bundle();
                            b.putString("taikhoan",taikhoan);
                            intent.putExtra("dulieu",b);
                            startActivity(intent);

                        }
                    }
                }
            }
        });
    }
}