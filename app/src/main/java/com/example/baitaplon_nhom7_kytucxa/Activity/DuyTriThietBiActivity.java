package com.example.baitaplon_nhom7_kytucxa.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_nhom7_kytucxa.Adapter.DuyTriThietBiAdapter;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.DuyTriThietBi;

import java.util.ArrayList;
import java.util.Calendar;

public class DuyTriThietBiActivity extends AppCompatActivity {
    Button btnAdd, btnEdit, btnExit, btnDate;
    ImageButton btnBack;
    EditText editStatus, editDate;
    Spinner spName, spRoom;
    ListView lv;
    ArrayList<DuyTriThietBi> arrList = new ArrayList<DuyTriThietBi>();
    DuyTriThietBi thietbi = null;
    String temp_name = "";
    String temp_room = "";
    int temp_id = 0;

    Database db;

    ArrayList<Integer> arrRoom= null;
    ArrayList<String> arrName= null;


    DuyTriThietBiAdapter duyTriThietBiAdapter;

    int lastSelectedYear;
    int lastSelectedMonth;
    int lastSelectedDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duytritb_main);
        this.getSupportActionBar().hide();
        getWidget();
        addEvents();

        /* Database */
        db = new Database(this);
        arrRoom = new ArrayList<>(db.getRoom());
        arrName = new ArrayList<>(db.getTenThietBi());

        /* Spinner */
        ArrayAdapter<String> adapterName = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, arrName);
        ArrayAdapter<Integer> adapterRoom = new ArrayAdapter<Integer> (this, android.R.layout.simple_spinner_item, arrRoom);

        adapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spName.setAdapter(adapterName);
        spRoom.setAdapter(adapterRoom);

        spName.setOnItemSelectedListener(new MyProcessEventName());
        spRoom.setOnItemSelectedListener(new MyProcessEventRoom());

        /* Date picker */

        this.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });

        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        /* Xử lý listview */

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    view.setSelected(true);
                    for (int i=0 ; i<arrName.size() ; i++) {
                        if (arrList.get(position).getTenThietBi().toString().equals(arrName.get(i).toString()))
                            spName.setSelection(i);
                    }

                    for (int i=0 ; i<arrRoom.size() ; i++) {
                        if (arrList.get(position).getMaPhong().equals(arrRoom.get(i)))
                            spRoom.setSelection(i);
                    }

                    editStatus.setText(arrList.get(position).getTinhTrang().toString());
                    editDate.setText(arrList.get(position).getNgayLap().toString());
                    temp_id = arrList.get(position).getIDThietBi();
                }
                catch (Exception e){
                    Log.e("PhongTran", "onItemClick: ",e );
                }
            }
        });

        /* Xoa thiet bi */
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XoaThietBi(position);
                return false;
            }
        });

        getDataThietBi();
    }

    /**/
    private void buttonSelectDate() {

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                editDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog = null;

        datePickerDialog = new DatePickerDialog(this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        datePickerDialog.show();
    }

    /**/
    private void getWidget() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnExit = (Button) findViewById(R.id.btnExit);
        spName = (Spinner) findViewById(R.id.spinner);
        spRoom = (Spinner) findViewById(R.id.spinner2);

        editStatus = (EditText) findViewById(R.id.editStatus);
        lv = (ListView) findViewById(R.id.listView);
        btnDate = (Button) findViewById(R.id.button_date);
        editDate = (EditText) findViewById(R.id.editDate);
        editDate.setEnabled(false);

        btnBack = findViewById(R.id.imageButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /* Lấy dữ liệu khi chọn ở spinner*/
    private class MyProcessEventName implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            temp_name = arrName.get(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            temp_name = "";
        }
    }

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

    /*Get data from CSDL*/

    private void getDataThietBi() {
        arrList = db.getDuyTriThietBi();
        duyTriThietBiAdapter = new DuyTriThietBiAdapter(this, R.layout.duytritb_dong_thiet_bi, arrList);
        lv.setAdapter(duyTriThietBiAdapter);
        duyTriThietBiAdapter.notifyDataSetChanged();
    }

    /* Các hàm xử lý khi thao tác thêm sửa xoá, thoát chương trình*/

    private void ThemThietBi() {
        if (editStatus.getText().toString().matches("") || editDate.getText().toString().matches("")) {
            Toast.makeText(this, "Hãy nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else {
            db.themDuyTriThietBi(temp_name, temp_room, editStatus.getText().toString(), editDate.getText().toString());
            Toast.makeText(this, "Đã thêm thiết bị", Toast.LENGTH_SHORT).show();
            getDataThietBi();
        }
    }

    private void EditThietBi() {
        if (editStatus.getText().toString().matches("") || editDate.getText().toString().matches("")) {
            Toast.makeText(this, "Chọn mục cần cập nhật và nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else {
            db.updateDuyTriThietBi(temp_name, temp_room, editStatus.getText().toString(), editDate.getText().toString(), temp_id);
            Toast.makeText(this, "Đã sửa", Toast.LENGTH_SHORT).show();
            getDataThietBi();
        }
    }

    private void XoaThietBi(int position) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có chắc muốn xoá thiết bị này?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.xoaDuyTriThietBi(arrList.get(position).getIDThietBi());
                Toast.makeText(DuyTriThietBiActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                getDataThietBi();
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

    private void addEvents() {
        btnAdd.setOnClickListener(new ProcessMyEvent());
        btnEdit.setOnClickListener(new ProcessMyEvent());
        btnExit.setOnClickListener(new ProcessMyEvent());
    }

    private class ProcessMyEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:ThemThietBi(); break;
                case R.id.btnEdit:EditThietBi(); break;
                case R.id.btnExit:pressExit(); break;
            }
        }
    }

}