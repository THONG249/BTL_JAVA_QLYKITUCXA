package com.example.baitaplon_nhom7_kytucxa.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
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

import com.example.baitaplon_nhom7_kytucxa.Adapter.ThanhToanAdapter;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;
import com.example.baitaplon_nhom7_kytucxa.Utiti.ThanhToan;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {

    ImageButton btnBack;
    Spinner spMonth, spPhong;
    EditText editDienNuocTT, editDichVuTT;
    Button btnAddTT, btnEditTT, btnExitTT;
    ListView lvThanhToan;
    Database db;
    ArrayList<ThanhToan> arrayThanhToan = new ArrayList<>();
    ArrayList<Phong> arrayPhong = new ArrayList<>();
    ArrayList<Integer> arrRoomName = new ArrayList<>();

    ThanhToanAdapter thanhToanAdapter;
    int temp_id = -1;
    String temp_room, temp_month, temp_status;

    String arrMonth[] = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10","Tháng 11", "Tháng 12"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanh_toan_main);
        this.getSupportActionBar().hide();
        getWidget();
        listControl();
        addEvents();
        db = new Database(this);

        //spinner
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrMonth);

        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spMonth.setAdapter(adapterMonth);

        spMonth.setOnItemSelectedListener(new MyProcessEventMonth());


        getDataThanhToan();
    }

    private void listControl() {
        /* Xử lý listview */

        lvThanhToan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                for (int i=0 ; i<arrMonth.length ; i++) {
                    if (arrayThanhToan.get(position).getThang().equals(arrMonth[i].toString()))
                        spMonth.setSelection(i);
                }

                for (int i=0 ; i<arrayPhong.size() ; i++) {
                    if (arrayThanhToan.get(position).getPhong().equals(arrayPhong.get(i)))
                        spPhong.setSelection(i);
                }

                temp_id = arrayThanhToan.get(position).getId();
                temp_status = arrayThanhToan.get(position).getTinhTrang();
            }
        });

        /* Xoa thiet bi */
        lvThanhToan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XoaThanhToan(position);
                return false;
            }
        });
    }

    //CRUD
    private void getDataThanhToan() {
        arrayThanhToan = db.getThanhToan();
        thanhToanAdapter = new ThanhToanAdapter(this,R.layout.thanhtoan_dong_thanh_toan, arrayThanhToan);
        lvThanhToan.setAdapter(thanhToanAdapter);
        thanhToanAdapter.notifyDataSetChanged();
    }

    private void themThanhToan() {
        if (temp_room == "") {
            Toast.makeText(this, "Hãy chọn phòng!", Toast.LENGTH_SHORT).show();
        }else {
            String statusDefault = "Chưa thanh toán";
            int tienDienNuoc = Integer.parseInt(editDienNuocTT.getText().toString());
            int tienDichVu = Integer.parseInt(editDichVuTT.getText().toString());
            int tongTien = tienDienNuoc + tienDichVu;
            db.themThanhToan(temp_month,temp_room, tienDienNuoc, tienDichVu, tongTien, statusDefault);
            Toast.makeText(this, "Đã thêm", Toast.LENGTH_SHORT).show();
            getDataThanhToan();
        }
    }

    private void EditDienNuoc() {

        if (temp_id == -1) {
            Toast.makeText(this, "Hãy chọn mục cần thanh toán!", Toast.LENGTH_SHORT).show();
        }
        else if (temp_status.equals("Đã thanh toán")) {
            Toast.makeText(this, "Hãy chọn mục khác! Mục này đã thanh toán rồi.", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
            dialogXoa.setMessage("Xác nhận thanh toán?");
            dialogXoa.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.updateThanhToan(temp_id);
                    Toast.makeText(ThanhToanActivity.this, "Đã thanh toán", Toast.LENGTH_SHORT).show();
                    getDataThanhToan();
                }
            });
            dialogXoa.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialogXoa.show();
        }
    }

    private void XoaThanhToan(int position) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có chắc muốn xoá mục này?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.xoaThanhToan(arrayThanhToan.get(position).getId());
                Toast.makeText(ThanhToanActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                getDataThanhToan();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    private void getWidget() {
        spMonth = (Spinner) findViewById(R.id.spinnerMonthTT);
        spPhong = (Spinner) findViewById(R.id.spinnerPhongTT);
        editDienNuocTT = (EditText) findViewById(R.id.editDienNuocTT);
        editDienNuocTT.setEnabled(false);
        editDichVuTT = (EditText) findViewById(R.id.editDichVuTT);
        editDichVuTT.setEnabled(false);
        btnAddTT = (Button) findViewById(R.id.btnAddTT);
        btnEditTT = (Button) findViewById(R.id.btnEditTT);
        btnExitTT = (Button) findViewById(R.id.btnExitTT);
        lvThanhToan = (ListView) findViewById(R.id.lvThanhToan);

        btnBack = (ImageButton) findViewById(R.id.imageButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void pressExit() {
        finish();
    }

    //xu ly spinner

    private class MyProcessEventMonth implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            temp_month = arrMonth[position]+"";
            arrayPhong = db.getListRoomThanhToan(temp_month);
            getDataPhong();
            if (spPhong.getSelectedItem() == null) {
                temp_room = "";
                editDichVuTT.setText("");
                editDienNuocTT.setText("");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            temp_month = "";
        }
    }

    private class MyProcessEventRoom implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            temp_room = arrRoomName.get(position).toString();
            int tienDichVu = db.getSoNguoi(temp_room)*25000;
            editDichVuTT.setText(tienDichVu+"");
            int tienDienNuoc = db.getTTDienNuoc(temp_room, temp_month).getTongTien();
            editDienNuocTT.setText(tienDienNuoc+"");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            temp_room = "";
        }
    }

    //GET DU LIEU PHONG` THEO THANG

    private void getDataPhong() {
        arrRoomName.clear();
        for (int i = 0; i<arrayPhong.size(); i++) {
            int temp_phong = arrayPhong.get(i).getMaphong();
            arrRoomName.add(temp_phong);
        }

        ArrayAdapter<Integer> adapterRoom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrRoomName);
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPhong.setAdapter(adapterRoom);
        spPhong.setOnItemSelectedListener(new MyProcessEventRoom());
    }

    private void addEvents() {
        btnAddTT.setOnClickListener(new ProcessMyEvent());
        btnEditTT.setOnClickListener(new ProcessMyEvent());
        btnExitTT.setOnClickListener(new ProcessMyEvent());
    }

    private class ProcessMyEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAddTT:themThanhToan(); break;
                case R.id.btnEditTT:EditDienNuoc(); break;
                case R.id.btnExitTT:pressExit(); break;
            }
        }
    }
}