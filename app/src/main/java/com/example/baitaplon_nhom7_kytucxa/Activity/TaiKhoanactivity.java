package com.example.baitaplon_nhom7_kytucxa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.TaiKhoan;

import java.util.ArrayList;

public class TaiKhoanactivity extends AppCompatActivity {
    TextView txtHovaten, txtChucvu, txtTaikhoan;
    EditText edtoldpass, edtnewpass, edtcfpass;
    Button btnDoi;
    Database database;
    ArrayList<TaiKhoan> listTK;
    String taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        database = new Database(this);
        AnhXa();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        if (bundle != null) {
            taikhoan = bundle.getString("taikhoan");
        }
        listTK = database.getListTaiKhoan();

        for (TaiKhoan tk : listTK) {
            if (tk.getTaiKhoan().equals(taikhoan) == true) {
                txtTaikhoan.setText(tk.getTaiKhoan());
                txtChucvu.setText(tk.getChucVu());
                txtHovaten.setText(tk.getHoVaTen());
            }
        }
        doimatkhau();


    }

    private void AnhXa() {
        txtHovaten = (TextView) findViewById(R.id.txtHovaten);
        txtChucvu = (TextView) findViewById(R.id.txtChucVu);
        txtTaikhoan = (TextView) findViewById(R.id.txtTentk);
        edtoldpass = (EditText) findViewById(R.id.edtMatKhauCu);
        edtnewpass = (EditText) findViewById(R.id.edtMatKhauMoi);
        edtcfpass = (EditText) findViewById(R.id.edtMatKhauNhapLai);
        btnDoi = (Button) findViewById(R.id.btnDoimatkhau);
    }

    private void doimatkhau() {
        TaiKhoan tk = database.getTaikhoan(taikhoan);
        if (edtoldpass.getText().equals("") || edtnewpass.getText().equals("") || edtcfpass.getText().equals("")) {
            Toast.makeText(TaiKhoanactivity.this, "B???n ph???i nh???p ????? th??ng tin ", Toast.LENGTH_SHORT).show();
        } else {
            btnDoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tk.getMatKhau().equals(edtoldpass.getText() + "") == false) {
                        Toast.makeText(TaiKhoanactivity.this, "B???n ph???i nh???p sai m???t kh???u c?? ", Toast.LENGTH_SHORT).show();
                    } else if (edtnewpass.getText().toString().equalsIgnoreCase(edtcfpass.getText().toString()) == false) {
                        Toast.makeText(TaiKhoanactivity.this, "B???n nh???p l???i m???t kh???u m???i ", Toast.LENGTH_SHORT).show();
                    } else {
                        String matkhaumoi = edtnewpass.getText() + "";
                        TaiKhoan taiKhoan = new TaiKhoan(tk.getTaiKhoan(), matkhaumoi);
                        database.updateTaiKhoan(taiKhoan);
                        Toast.makeText(TaiKhoanactivity.this, "?????i th??nh c??ng ", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        }
    }
}

