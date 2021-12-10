package com.example.baitaplon_nhom7_kytucxa.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Nguoi;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;

import java.util.ArrayList;

public class Dangky extends AppCompatActivity {

    EditText txtMasv, txtHovaten;
    CheckBox checkBoxNam, checkBoxNu,checkvip,checkthuong;
    RadioGroup radgroup;
    Button btndong, btnDangky;
    ListView lvDangky;
    String lop[] = {"CNTT1", "CNTT2", "CNTT3", "KTPM1", "KTPM3", "HTTT1"};
    String khoa[] = {"K13", "K14", "K15", "K21"};
    Spinner spinlop, spinkhoa,spinerVip,spinerThuong;
    ArrayList<Nguoi> arrayList = new ArrayList<Nguoi>();
    ArrayAdapter<Nguoi> adapter;
    ArrayList<Phong> listPhongVip,listPhongThuong;
    Database database;
    Nguoi ngSelected = new Nguoi ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        database = new Database (this);
        getControls();
        dospinerdsphong();
        doDangky();
        fill();
        getFormWidgets();
        getDataDanhsach ();

    }

    private void getControls() {
        txtMasv = (EditText) findViewById(R.id.edtMasv);
        txtHovaten = (EditText) findViewById(R.id.edtHovaten);
        checkBoxNam = (CheckBox) findViewById(R.id.chkNam);
        checkBoxNu = (CheckBox) findViewById(R.id.chkNu);
        spinlop = (Spinner) findViewById(R.id.spinerLop);
        spinkhoa = (Spinner) findViewById(R.id.spinerkhoa);
        spinerVip = (Spinner) findViewById(R.id.spinerloaiVIP);
        spinerThuong = (Spinner) findViewById(R.id.spinerloaiThuong);
        checkvip = (CheckBox) findViewById(R.id.checkLoaiVip);
        checkthuong = (CheckBox) findViewById(R.id.checkLoaiThuong);

        btndong = (Button) findViewById(R.id.btndong);
        btnDangky = (Button) findViewById(R.id.btnDangky);
        lvDangky = (ListView) findViewById(R.id.lvDangky);

        adapter = new ArrayAdapter<Nguoi>(Dangky.this, android.R.layout.simple_list_item_1, arrayList);
        lvDangky.setAdapter(adapter);

        ArrayAdapter<String> adaperspinlop = new ArrayAdapter<String>(Dangky.this, android.R.layout.simple_spinner_item, lop);
        adaperspinlop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinlop.setAdapter(adaperspinlop);

        ArrayAdapter<String> adapterkhoa = new ArrayAdapter<String>(Dangky.this, android.R.layout.simple_spinner_item, khoa);
        adapterkhoa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinkhoa.setAdapter(adapterkhoa);


    }
    private void getDataDanhsach(){

        arrayList = database.getListNguoi ();
        adapter = new ArrayAdapter<Nguoi>(Dangky.this, android.R.layout.simple_list_item_1, arrayList);
        lvDangky.setAdapter(adapter);
        adapter.notifyDataSetChanged ();


    }
    private void dospinerdsphong(){
        listPhongVip = database.getListLoaiPhong("VIP");
        listPhongThuong = database.getListLoaiPhong("Thường");

        ArrayAdapter<Phong> adapterVip = new ArrayAdapter<Phong>(Dangky.this, android.R.layout.simple_spinner_item, listPhongVip);
        adapterVip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerVip.setAdapter(adapterVip);

        ArrayAdapter<Phong> adapterThuong = new ArrayAdapter<Phong>(Dangky.this, android.R.layout.simple_spinner_item, listPhongThuong);
        adapterThuong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerThuong.setAdapter(adapterThuong);

    }

    private void doDangky() {

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (checkvip.isChecked() == false && checkthuong.isChecked() == false) {
                    Toast.makeText(Dangky.this, "Chọn 1 trong 2 loại !", Toast.LENGTH_SHORT).show();
                } else if (checkvip.isChecked() == true && checkthuong.isChecked() == true) {
                    Toast.makeText(Dangky.this, "Chỉ được chọn 1 !", Toast.LENGTH_SHORT).show();
                } else {
                    String gioitinh;
                    String Masv = txtMasv.getText() + "";
                    String Hovaten = txtHovaten.getText() + "";
                    String Lop = spinlop.getSelectedItem().toString();
                    String Khoa = spinkhoa.getSelectedItem().toString();
                    if (checkBoxNam.isChecked() == true) {
                        gioitinh = "Nam";
                        checkBoxNu.setChecked(false);
                    } else {
                        gioitinh = "Nữ";
                    }
                    int phong;
                    if (checkvip.isChecked() == true) {
                        phong = Integer.parseInt(spinerVip.getSelectedItem() + "");
                    } else
                    {
                        phong = Integer.parseInt(spinerThuong.getSelectedItem() + "");
                    }
                    ArrayList<Nguoi > listsv = database.getListSvTrongphong(phong);
                    Phong isphong = database.getMotPhong(phong);
                    if(listsv.size()>=isphong.getSonguoi()-1){
                        Toast.makeText(Dangky.this,"Phòng đã đầy !",Toast.LENGTH_SHORT).show();
                    }else {
                        Nguoi nguoi = new Nguoi(Masv, Hovaten, Lop, Khoa, gioitinh, phong);
                        database.dangky(nguoi);
                        getDataDanhsach();
                    }
                }
            }
        });

        lvDangky.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ngSelected = arrayList.get(position);
                return false;
            }
        });

        btndong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(Dangky.this);
                builder.setTitle("Thoát chương trình ");
                builder.setMessage("Bạn có muốn thoát chương trình không ?");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void fill() {
        lvDangky.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                txtMasv.setText(arrayList.get(arg2).getMasv() + "");
                txtHovaten.setText(arrayList.get(arg2).getHovaten() + "");
            }
        });
    }
    public void getFormWidgets() {

        registerForContextMenu(lvDangky);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_thongtin, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnusuathongtin:
                SuaThongTin();
                break;
            case R.id.mnuxoathongtin:
                XoaThongTin();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void SuaThongTin() {
        Dialog dialog = new Dialog (this);
        dialog.setContentView (R.layout.activity_suathongtin);

        txtMasv = (EditText) dialog.findViewById(R.id.edtMasv);
        txtHovaten = (EditText) dialog.findViewById(R.id.edtHovaten);
        checkBoxNam = (CheckBox) dialog.findViewById(R.id.chkNam);
        checkBoxNu = (CheckBox) dialog.findViewById(R.id.chkNu);
        spinlop = (Spinner) dialog.findViewById(R.id.spinerLop);
        spinkhoa = (Spinner) dialog.findViewById(R.id.spinerkhoa);
        spinerVip = (Spinner) dialog.findViewById(R.id.spinerloaiVIP);
        spinerThuong = (Spinner) dialog.findViewById(R.id.spinerloaiThuong);
        checkvip = (CheckBox) dialog.findViewById(R.id.checkLoaiVip);
        checkthuong = (CheckBox) dialog.findViewById(R.id.checkLoaiThuong);


        ArrayAdapter<String> adaperspinlop = new ArrayAdapter<String>(Dangky.this, android.R.layout.simple_spinner_item, lop);
        adaperspinlop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinlop.setAdapter(adaperspinlop);

        ArrayAdapter<String> adapterkhoa = new ArrayAdapter<String>(Dangky.this, android.R.layout.simple_spinner_item, khoa);
        adapterkhoa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinkhoa.setAdapter(adapterkhoa);
        dospinerdsphong();

        Button btnCapNhat = (Button) dialog.findViewById (R.id.btnCapnhat);

        txtMasv.setText(ngSelected.getMasv() + "");
        txtHovaten.setText(ngSelected.getHovaten() + "");

        btnCapNhat.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String gioitinh ;
                String Masv = txtMasv.getText ()+"";
                String Hovaten = txtHovaten.getText ()+"";
                String Lop = spinlop.getSelectedItem ().toString ();
                String Khoa = spinkhoa.getSelectedItem ().toString ();
                if (checkBoxNam.isChecked() == true) {
                    gioitinh ="Nam";
                    checkBoxNu.setChecked (false);
                }
                else {
                    gioitinh = "Nữ";
                }

                int phong;
                if(checkvip.isChecked()==true){
                    phong = Integer.parseInt(spinerVip.getSelectedItem()+"");
                    spinerThuong.setEnabled(false);}
                else if(checkthuong.isChecked()==true);{
                    phong = Integer.parseInt(spinerThuong.getSelectedItem()+"");
                    spinerVip.setEnabled(false);
                }
                ArrayList<Nguoi > listsv = database.getListSvTrongphong(phong);
                Phong isphong = database.getMotPhong(phong);
                if(listsv.size()>=isphong.getSonguoi()-1) {
                    Toast.makeText(Dangky.this, "Phòng đã đầy !", Toast.LENGTH_SHORT).show();
                }else {
                    Nguoi nguoi = new Nguoi(Masv, Hovaten, Lop, Khoa, gioitinh, phong);
                    database.update(nguoi);
                    dialog.dismiss();
                    Toast.makeText(Dangky.this, "Đã sửa thông tin thành công", Toast.LENGTH_LONG).show();
                    getDataDanhsach();
                }
            }
        });
        dialog.show ();
    }
    public void XoaThongTin() {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(Dangky.this);
        builder.setTitle("Xóa Thông Tin");
        builder.setMessage("Bạn có muốn xóa thông tin sinh viên không ?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String masv = ngSelected.getMasv ();
                database.delete (masv);
                Toast.makeText(Dangky.this, "Bạn đã xóa thành công", Toast.LENGTH_LONG).show();
                getDataDanhsach ();

            }
        });
        builder.show();
    }
}