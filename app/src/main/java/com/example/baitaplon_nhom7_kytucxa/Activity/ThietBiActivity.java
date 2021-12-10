package com.example.baitaplon_nhom7_kytucxa.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon_nhom7_kytucxa.Adapter.ThietBiAdapter;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.ThietBi;

import java.util.ArrayList;
import java.util.Calendar;

public class ThietBiActivity extends AppCompatActivity {
    ListView lv;
    Button btnAdd, btnEdit, btnExit, btnDate;
    ImageButton btnBack;
    EditText editTenTB, editXuatXu, editNgayNhap;
    ArrayList<ThietBi> arrayList = new ArrayList<ThietBi>();
    ThietBiAdapter thietBiAdapter;
    Database db;
    int temp_id = 0;

    int lastSelectedYear;
    int lastSelectedMonth;
    int lastSelectedDayOfMonth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thiet_bi_main);
        this.getSupportActionBar().hide();
        getWidget();
        addEvents();

        db = new Database(this);
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
                    editTenTB.setText(arrayList.get(position).getTenThietBi().toString());
                    editXuatXu.setText(arrayList.get(position).getXuatXu().toString());
                    editNgayNhap.setText(arrayList.get(position).getNgayNhap().toString());
                    int temp_pos = position;
                    temp_id = arrayList.get(position).getIdThietBi();
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

    private void getWidget() {
        btnAdd = (Button) findViewById(R.id.btnAddTB);
        btnEdit = (Button) findViewById(R.id.btnEditTB);
        btnExit = (Button) findViewById(R.id.btnExitTB);

        lv = (ListView) findViewById(R.id.lvThietBi);
        btnDate = (Button) findViewById(R.id.buttonNgayNhap);
        editTenTB = (EditText) findViewById(R.id.editTenTB);
        editXuatXu = (EditText) findViewById(R.id.editXuatXu);
        editNgayNhap = (EditText) findViewById(R.id.editNgayNhap);
        editNgayNhap.setEnabled(false);

        btnBack = findViewById(R.id.imageButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void buttonSelectDate() {

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                editNgayNhap.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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

    private void getDataThietBi() {
        arrayList = db.getThietBi();
        thietBiAdapter = new ThietBiAdapter(this,R.layout.thietbi_dong_thiet_bi, arrayList);
        lv.setAdapter(thietBiAdapter);
        thietBiAdapter.notifyDataSetChanged();
    }

    private void ThemThietBi() {
        if (editTenTB.getText().toString().matches("") || editXuatXu.getText().toString().matches("") || editNgayNhap.getText().toString().matches("")) {
            Toast.makeText(this, "Hãy nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        }
        else {
            db.themThietBi(editTenTB.getText().toString(), editXuatXu.getText().toString(), editNgayNhap.getText().toString());
            Toast.makeText(this, "Đã thêm thiết bị", Toast.LENGTH_SHORT).show();
            getDataThietBi();
        }
    }

    private void EditThietBi() {
        if (editTenTB.getText().toString().matches("") || editXuatXu.getText().toString().matches("") || editNgayNhap.getText().toString().matches("")) {
            Toast.makeText(this, "Chọn mục cần cập nhật và nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            db.updateThietBi(editTenTB.getText().toString(), editXuatXu.getText().toString(), editNgayNhap.getText().toString(), temp_id);
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
                db.xoaThietBi(arrayList.get(position).getIdThietBi());
                Toast.makeText(ThietBiActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
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
                case R.id.btnAddTB:ThemThietBi(); break;
                case R.id.btnEditTB:EditThietBi(); break;
                case R.id.btnExitTB:pressExit(); break;
            }
        }
    }
}