package com.example.baitaplon_nhom7_kytucxa.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_nhom7_kytucxa.Adapter.DienNuocAdapter;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.DienNuoc;

import java.util.ArrayList;

public class DienNuocActivity extends AppCompatActivity {

    ListView lvDienNuoc;
    ImageButton btnBack;
    EditText editDienTT, editDienTN, editNuocTT, editNuocTN;
    Spinner spPhong, spMonth;
    Button btnAddDN, btnEditDN, btnExitDN;
    Database db;
    ArrayList<Integer> arrRoom= null;
    ArrayList<DienNuoc> arrayDienNuoc = new ArrayList<>();
    DienNuocAdapter dienNuocAdapter;
    int temp_id = -1, dienTT, dienTN, nuocTT, nuocTN, soDien = 0, soNuoc = 0, tongTien = 0;
    String temp_room, temp_month;

    String arrMonth[] = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10","Tháng 11", "Tháng 12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dien_nuoc_main);
        this.getSupportActionBar().hide();
        getWidget();
        listControl();
        addEvents();

        db = new Database(this);
        arrRoom = new ArrayList<>(db.getRoom());

        //spinner
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrMonth);
        ArrayAdapter<Integer> adapterRoom = new ArrayAdapter<Integer> (this, android.R.layout.simple_spinner_item, arrRoom);

        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spMonth.setAdapter(adapterMonth);
        spPhong.setAdapter(adapterRoom);

        spPhong.setOnItemSelectedListener(new MyProcessEventRoom());
        spMonth.setOnItemSelectedListener(new MyProcessEventMonth());

        getDataDienNuoc();

    }

    private void listControl() {
        /* Xử lý listview */

        lvDienNuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    view.setSelected(true);
                    for (int i=0 ; i<arrRoom.size() ; i++) {
                        if (arrayDienNuoc.get(position).getPhong().equals(arrRoom.get(i).toString()))
                            spPhong.setSelection(i);
                    }

                    for (int i=0 ; i<arrMonth.length ; i++) {
                        if (arrayDienNuoc.get(position).getThang().equals(arrMonth[i]+""))
                            spMonth.setSelection(i);
                    }
                    temp_id = arrayDienNuoc.get(position).getId();
                }
                catch (Exception e){
                    Log.e("PhongTran", "onItemClick: ",e );
                }
            }
        });

        /* Xoa thiet bi */
        lvDienNuoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XoaDienNuoc(position);
                return false;
            }
        });
    }

    /* Lấy dữ liệu khi chọn ở spinner*/
    private class MyProcessEventRoom implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            temp_room = arrRoom.get(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            temp_room = "";
        }
    }

    private class MyProcessEventMonth implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            temp_month = arrMonth[position].toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            temp_month = "";
        }
    }

    //CRUD
    private void getDataDienNuoc() {
        arrayDienNuoc = db.getDienNuoc();
        dienNuocAdapter = new DienNuocAdapter(this,R.layout.diennuoc_dong_dien_nuoc, arrayDienNuoc);
        lvDienNuoc.setAdapter(dienNuocAdapter);
        dienNuocAdapter.notifyDataSetChanged();
    }

    private void ThemDienNuoc() {
        String dienTT = editDienTT.getText().toString();
        String dienTN = editDienTN.getText().toString();
        String nuocTT = editNuocTT.getText().toString();
        String nuocTN = editNuocTN.getText().toString();

        if (editDienTT.getText().toString().matches("") ||
                editDienTN.getText().toString().matches("") ||
                editNuocTT.getText().toString().matches("") ||
                editNuocTN.getText().toString().matches("")) {
            Toast.makeText(this, "Hãy nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(dienTT) > Integer.parseInt(dienTN) || Integer.parseInt(nuocTT) > Integer.parseInt(nuocTN)) {
            Toast.makeText(this, "Tháng trước phải nhỏ hơn tháng hiện tại!", Toast.LENGTH_SHORT).show();
        } else {
            int soDien = Integer.parseInt(dienTN) - Integer.parseInt(dienTT);
            int soNuoc = Integer.parseInt(nuocTN) - Integer.parseInt(nuocTT);

            int tongTien = soDien * 2000 + soNuoc * 10000;
            db.themDienNuoc(temp_room, temp_month, soDien, soNuoc, tongTien);
            Toast.makeText(this, "Đã thêm", Toast.LENGTH_SHORT).show();
            getDataDienNuoc();
        }
    }

    private void EditDienNuoc() {
        String dienTT = editDienTT.getText().toString();
        String dienTN = editDienTN.getText().toString();
        String nuocTT = editNuocTT.getText().toString();
        String nuocTN = editNuocTN.getText().toString();
        if (editDienTT.getText().toString().matches("") ||
                editDienTN.getText().toString().matches("") ||
                editNuocTT.getText().toString().matches("") ||
                editNuocTN.getText().toString().matches("") || temp_id == -1) {
            Toast.makeText(this, "Chọn mục cần cập nhật và nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(dienTT) > Integer.parseInt(dienTN) || Integer.parseInt(nuocTT) > Integer.parseInt(nuocTN)) {
            Toast.makeText(this, "Tháng trước phải nhỏ hơn tháng hiện tại!", Toast.LENGTH_SHORT).show();
        } else {
            int soDien = Integer.parseInt(dienTN) - Integer.parseInt(dienTT);
            int soNuoc = Integer.parseInt(nuocTN) - Integer.parseInt(nuocTT);

            int tongTien = soDien * 2000 + soNuoc * 10000;
            db.updateDienNuoc(temp_room, temp_month, soDien, soNuoc, tongTien, temp_id);
            Toast.makeText(this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
            getDataDienNuoc();
        }
    }

    private void XoaDienNuoc(int position) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có chắc muốn xoá mục này?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.xoaDienNuoc(arrayDienNuoc.get(position).getId());
                Toast.makeText(DienNuocActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                getDataDienNuoc();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    private void pressExit() {
        finish();
    }

    private void getWidget() {
        lvDienNuoc = (ListView) findViewById(R.id.lvDienNuoc);
        editDienTT = (EditText) findViewById(R.id.editDienThangTruoc);
        editDienTN = (EditText) findViewById(R.id.editDienThangNay);
        editNuocTT = (EditText) findViewById(R.id.editNuocThangTruoc);
        editNuocTN = (EditText) findViewById(R.id.editNuocThangNay);
        spPhong = (Spinner) findViewById(R.id.spinnerPhongDN);
        spMonth = (Spinner) findViewById(R.id.spinnerMonthDN);
        btnAddDN = (Button) findViewById(R.id.btnAddDN);
        btnEditDN = (Button) findViewById(R.id.btnEditDN);
        btnExitDN = (Button) findViewById(R.id.btnExitDN);

        btnBack = findViewById(R.id.imageButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addEvents() {
        btnAddDN.setOnClickListener(new ProcessMyEvent());
        btnEditDN.setOnClickListener(new ProcessMyEvent());
        btnExitDN.setOnClickListener(new ProcessMyEvent());
    }

    private class ProcessMyEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAddDN:ThemDienNuoc(); break;
                case R.id.btnEditDN:EditDienNuoc(); break;
                case R.id.btnExitDN:pressExit(); break;
            }
        }
    }
}