package com.example.baitaplon_nhom7_kytucxa.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.baitaplon_nhom7_kytucxa.Adapter.ListviewAdapter;
import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Nguoi;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Tang;

import java.util.ArrayList;

public class QuanliPhong extends AppCompatActivity {

    Database database;
    ListView lvPhong;
    Spinner spinner;
    ArrayList<Tang> arrayTang = new ArrayList<>();
    ArrayAdapter<Tang> adapterSpinner;
    ArrayList<Phong> arrayPhong = new ArrayList<>();
    ListviewAdapter adapterPhong;
    Phong phongselected = new Phong();
    ArrayList<Nguoi> listSv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanli_phong);
        database = new Database(this);
        AnhXa();
        Hienthi();
        getDataPhong();
        registerForContextMenu(lvPhong);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getDataPhong();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
         lvPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent = new Intent(QuanliPhong.this,xemdanhsachsinhvien.class);
                 phongselected = arrayPhong.get(i);
                 Bundle b =new Bundle();
                  b.putSerializable("sinhvien",phongselected);
                  intent.putExtra("dulieu",b);
                  startActivity(intent);



             }
         });

        lvPhong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                phongselected = arrayPhong.get(position);
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.contextAdd)
            DialogThem();
        return super.onOptionsItemSelected(item);
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.optSua)
            dialogSua();
        if (item.getItemId() == R.id.optXoa)
            Xoa();
        return super.onContextItemSelected(item);
    }


    private void AnhXa() {

        spinner = (Spinner) findViewById(R.id.spinner1);

        lvPhong = (ListView) findViewById(R.id.lvPhong);


    }

    private void Hienthi() {

        arrayTang = database.getlistTang();
        adapterSpinner = new ArrayAdapter<Tang>(QuanliPhong.this, android.R.layout.simple_spinner_item, arrayTang);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        adapterSpinner.notifyDataSetChanged();

    }

    private void getDataPhong() {

        arrayPhong = database.getList(spinner.getSelectedItem() + "");
        adapterPhong = new ListviewAdapter(QuanliPhong.this, R.layout.custom_listview, arrayPhong);
        lvPhong.setAdapter(adapterPhong);
        adapterPhong.notifyDataSetChanged();
    }


    private boolean ktra(int ma) {
        boolean ktra = false;
        arrayPhong = database.checkID(ma);
        if (arrayPhong.size() == 0)
            ktra = false;
        else
            ktra = true;
        return ktra;
    }

    private void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_phong);

        EditText edtMaphong = (EditText) dialog.findViewById(R.id.edtMaPhong);
        EditText edtSogiuong = (EditText) dialog.findViewById(R.id.edtSogiuong);
        EditText edtSonguoi = (EditText) dialog.findViewById(R.id.edtSonguoi);
        CheckBox ckVip = (CheckBox) dialog.findViewById(R.id.ckVIP);
        CheckBox ckThuong = (CheckBox) dialog.findViewById(R.id.ckThuong);
        Button btnNhap = (Button) dialog.findViewById(R.id.btnThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnThoat);


        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ktrasogiuong,ktrasonguoi;
                ktrasogiuong= Integer.parseInt(edtSogiuong.getText().toString());
                ktrasonguoi=Integer.parseInt(edtSonguoi.getText().toString());
                if (edtMaphong.getText().toString().equalsIgnoreCase("") || edtSogiuong.getText().toString().equalsIgnoreCase("") || edtSonguoi.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(QuanliPhong.this, "Bạn nhập thiếu thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    if (ktra(Integer.parseInt(edtMaphong.getText().toString())) == true) {
                        Toast.makeText(QuanliPhong.this, "Mã bị trùng !", Toast.LENGTH_SHORT).show();
                    } else if (ckVip.isChecked() == false && ckThuong.isChecked() == false) {
                        Toast.makeText(QuanliPhong.this, "Chọn 1 trong 2 loại !", Toast.LENGTH_SHORT).show();
                    } else if (ckVip.isChecked() == true && ckThuong.isChecked() == true) {
                        Toast.makeText(QuanliPhong.this, "Chỉ được chọn 1 !", Toast.LENGTH_SHORT).show();
                    } else if(ktrasogiuong>6||ktrasonguoi>12){
                        Toast.makeText(QuanliPhong.this,"Số người không quá 12 và số giường không quá 6 !",Toast.LENGTH_SHORT).show();
                    }else
                        {
                        int maphong, sogiuong, songuoi;
                        String loaiphong, idTang;
                        maphong = Integer.parseInt(edtMaphong.getText() + "");
                        sogiuong = Integer.parseInt(edtSogiuong.getText() + "");
                        songuoi = Integer.parseInt(edtSonguoi.getText() + "");
                        idTang = spinner.getSelectedItem() + "";

                        if (ckThuong.isChecked() == true) {
                            loaiphong = "Thường";
                        } else {
                            loaiphong = "VIP";

                        }

                        Phong phong = new Phong(maphong, sogiuong, songuoi, loaiphong, idTang);
                        database.insertPhong(phong);
                        Toast.makeText(QuanliPhong.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        getDataPhong();

                    }
                }
            }


        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void dialogSua() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        EditText edtMaphong = (EditText) dialog.findViewById(R.id.edtMaPhong);
        EditText edtSogiuong = (EditText) dialog.findViewById(R.id.edtSogiuong);
        EditText edtSonguoi = (EditText) dialog.findViewById(R.id.edtSonguoi);
        CheckBox ckVip = (CheckBox) dialog.findViewById(R.id.ckVIP);
        CheckBox ckThuong = (CheckBox) dialog.findViewById(R.id.ckThuong);
        Button btnedit = (Button) dialog.findViewById(R.id.btnEdit);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnThoat);

        edtMaphong.setText(phongselected.getMaphong() + "");
        edtSogiuong.setText(phongselected.getSogiuong() + "");
        edtSonguoi.setText(phongselected.getSonguoi() + "");
        edtMaphong.setEnabled(false);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ktrasogiuong,ktrasonguoi;
                ktrasogiuong= Integer.parseInt(edtSogiuong.getText().toString());
                ktrasonguoi=Integer.parseInt(edtSonguoi.getText().toString());
                if (edtMaphong.getText().toString().equalsIgnoreCase("") || edtSogiuong.getText().toString().equalsIgnoreCase("") || edtSonguoi.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(QuanliPhong.this, "Bạn nhập thiếu thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    if (ktra(Integer.parseInt(edtMaphong.getText().toString())) == false) {
                        Toast.makeText(QuanliPhong.this, "Mã không tồn tại !", Toast.LENGTH_SHORT).show();
                    } else if (ckVip.isChecked() == false && ckThuong.isChecked() == false) {
                        Toast.makeText(QuanliPhong.this, "Chọn 1 trong 2 loại !", Toast.LENGTH_SHORT).show();
                    } else if (ckVip.isChecked() == true && ckThuong.isChecked() == true) {
                        Toast.makeText(QuanliPhong.this, "Chỉ được chọn 1 !", Toast.LENGTH_SHORT).show();
                    } else if(ktrasogiuong>6||ktrasonguoi>12){
                        Toast.makeText(QuanliPhong.this,"Số người không quá 12 và số giường không quá 6 !",Toast.LENGTH_SHORT).show();
                    }else
                        {
                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(QuanliPhong.this);
                        builder.setTitle("Sửa chương trình ");
                        builder.setMessage("Bạn có muốn sửa thông tin ?");
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.setPositiveButton("Có", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dalog, int which) {
                                        int maphong, sogiuong, songuoi;
                                        String loaiphong, idTang;
                                        maphong = Integer.parseInt(edtMaphong.getText() + "");
                                        sogiuong = Integer.parseInt(edtSogiuong.getText() + "");
                                        songuoi = Integer.parseInt(edtSonguoi.getText() + "");

                                        if (ckThuong.isChecked() == true) {
                                            loaiphong = "Thường";
                                        } else {
                                            loaiphong = "VIP";

                                        }

                                        Phong phong = new Phong(maphong, sogiuong, songuoi, loaiphong);
                                        database.updatePhong(phong);
                                        Toast.makeText(QuanliPhong.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        getDataPhong();

                                    }
                                });
                        builder.create().show();

                    }

                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void Xoa() {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(QuanliPhong.this);
        builder.setTitle("Xoa chương trình ");
        builder.setMessage("Bạn có muốn xóa thông tin ?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Có", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int ma = phongselected.getMaphong();
                        database.deletePhong(ma);
                        Toast.makeText(QuanliPhong.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                        getDataPhong();

                    }
                });
        builder.create().show();
    }
}
