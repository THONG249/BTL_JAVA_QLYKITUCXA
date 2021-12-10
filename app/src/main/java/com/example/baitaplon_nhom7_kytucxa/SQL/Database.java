package com.example.baitaplon_nhom7_kytucxa.SQL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.baitaplon_nhom7_kytucxa.Adapter.ListviewAdapter;
import com.example.baitaplon_nhom7_kytucxa.Utiti.DienNuoc;
import com.example.baitaplon_nhom7_kytucxa.Utiti.DuyTriThietBi;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Nguoi;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;
import com.example.baitaplon_nhom7_kytucxa.Utiti.TaiKhoan;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Tang;
import com.example.baitaplon_nhom7_kytucxa.Utiti.ThanhToan;
import com.example.baitaplon_nhom7_kytucxa.Utiti.ThietBi;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {
// Quanliphong
    final static String DB_NAME="QL_KYTUCXA2";
    final static String TABLE_TANG="TANG";
    final static String  TANG_ID="Id";
    final  static String TANG_MOTA="MoTa";


    final static String TABLE_PHONG="PHONG";
    final static String  PHONG_ID="Id";
    final  static String PHONG_SOGIUONG="SoGiuong";
    final static  String PHONG_SONGUOI ="SoNguoi";
    final static  String PHONG_LOAIPHONG="LoaiPhong";
    final static String PHONG_IDTANG="IdTang";
    final static int VERSION =1;
// Quanli taikhoan
    final static String TABLE_TAIKHOAN="TAIKHOAN";
    final static  String TEN_TAIKHOAN="TaiKhoan";
    final static  String MATKHAU = "MatKhau";
    final static String HOVATEN = "HoVaTen";
    final static String CHUCVU = "ChucVu";
//quanli dang ky
    final static String TABLE_DKY = "DANGKY";
    final static String MASV = "MASV";
    final static String HOVaTEN = "HOVATEN";
    final static String LOP = "LOP";
    final static String KHOA = "KHOA";
    final static String GIOITINH = "GIOITINH";
    final static String PHONG = "PHONG";
    //QUAN LY THIET BI
    private final String TABLE_THIETBI = "thietBi";
    private final String THIETBI_ID = "ID";
    private final String THIETBI_NAME = "tenThietBi";
    private final String THIETBI_XUATXU = "xuatXu";
    private final String THIETBI_DATE = "ngayNhap";


    //QUAN LY DUY TRI THIET BI
    private final String TABLE_DUYTRITHIETBI = "duyTriThietBi";
    private final String DUYTRITHIETBI_ID = "ID";
    private final String DUYTRITHIETBI_NAME = "tenThietBi";
    private final String DUYTRITHIETBI_STATUS = "tinhTrang";
    private final String DUYTRITHIETBI_DATE = "ngayLapDat";
    private final String DUYTRITHIETBI_ROOM = "tenPhong";

    //QUAN LY DIEN NUOC
    private final String TABLE_DIENNUOC = "dienNuoc";
    private final String DIENNUOC_ID = "ID";
    private final String DIENNUOC_PHONG = "maPhong";
    private final String DIENNUOC_THANG = "thang";
    private final String DIENNUOC_SODIEN = "soDien";
    private final String DIENNUOC_SONUOC = "soNuoc";
    private final String DIENNUOC_TONGTIEN = "tongTien";

    //QUAN LY THANH TOAN
    private final String TABLE_THANHTOAN = "thanhToan";
    private final String THANHTOAN_ID = "ID";
    private final String THANHTOAN_PHONG = "maPhong";
    private final String THANHTOAN_THANG = "thang";
    private final String THANHTOAN_DIENNUOC = "tienDienNuoc";
    private final String THANHTOAN_DICHVU = "tienDichVu";
    private final String THANHTOAN_TONGTIEN = "tongTien";
    private final String THANHTOAN_TINHTRANG = "tinhTrang";


    //Truy van khong tra ket qua
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy van co tra ket qua
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, null);
    }

    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1  = "CREATE TABLE IF NOT EXISTS TAIKHOAN(TaiKhoan VARCHAR(30) PRIMARY KEY,MatKhau VARCHAR(30)," +
                "HoVaTen VARCHAR(50),ChucVu VARCHAR(30))";

        String sql = "CREATE TABLE IF NOT EXISTS TANG(Id VARCHAR(20) PRIMARY KEY,MoTa VARCHAR(20))";

        String sql2 = "CREATE TABLE IF NOT EXISTS PHONG(Id INTEGER PRIMARY KEY,SoGiuong INTEGER,SoNguoi INTEGER," +
                "LoaiPhong VARCHAR(100),IdTang VARCHAR(20) CONSTRAINT TANGId REFERENCES TANG(Id))";

        String sql3 = "CREATE TABLE IF NOT EXISTS DANGKY(MASV VARCHAR(10) PRIMARY KEY, HOVATEN VARCHAR(50), LOP VARCHAR(50), KHOA VARCHAR(50), GIOITINH VARCHAR(50), PHONG INTEGER CONSTRAINT IDPHONG REFERENCES PHONG(Id))";

        String createDuyTriThietBiTable = "CREATE TABLE IF NOT EXISTS duyTriThietBi(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tenThietBi VARCHAR, tenPhong VARCHAR, tinhTrang VARCHAR, ngayLapDat VARCHAR)";
        String createThietBiTable = "CREATE TABLE IF NOT EXISTS thietBi(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tenThietBi VARCHAR, xuatXu VARCHAR, ngayNhap VARCHAR)";

        String createDienNuoc = "CREATE TABLE IF NOT EXISTS dienNuoc(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maPhong VARCHAR, thang VARCHAR, soDien INTEGER, soNuoc INTEGER, tongTien INT)";

        String createThanhToan = "CREATE TABLE IF NOT EXISTS thanhToan(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " thang VARCHAR, maPhong VARCHAR, tienDienNuoc INTEGER, tienDichVu INTEGER, tongTien INTEGER, tinhTrang VARCHAR)";

        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(createThietBiTable);
        db.execSQL(createDuyTriThietBiTable);
        db.execSQL(createDienNuoc);
        db.execSQL(createThanhToan);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_TAIKHOAN);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHONG);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_TANG);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_DKY);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_DIENNUOC);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_DUYTRITHIETBI);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_THANHTOAN);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_THIETBI);
            onCreate(db);
        }
    }

    //TRUY VAN QUAN LY THIET BI
    //add floor if not exist
    public void addThietBi() {
        String countThietBi = "SELECT * FROM "+TABLE_THIETBI;
        Cursor cursor = GetData(countThietBi);
        cursor.moveToFirst();
        if(!cursor.moveToFirst()) {
            themThietBi("Giường","Việt Nam", "1/2/2020");
            themThietBi("Tủ đồ","Việt Nam", "2/3/2020");
            themThietBi("Bàn học","Việt Nam", "3/4/2020");
            themThietBi("Điều hoà","Việt Nam", "4/5/2020");
            themThietBi("Nóng lạnh","Việt Nam", "5/6/2020");
        }
    }

    @SuppressLint("Range")
    public ArrayList<ThietBi> getThietBi()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<ThietBi> list=new ArrayList<>();
        try
        {
            cursor=sqLiteDatabase.query(TABLE_THIETBI, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int idThietBi = cursor.getInt(cursor.getColumnIndex(THIETBI_ID));
            String tenThietBi =cursor.getString(cursor.getColumnIndex(THIETBI_NAME));
            String xuatXu = cursor.getString(cursor.getColumnIndex(THIETBI_XUATXU));
            String date = cursor.getString(cursor.getColumnIndex(THIETBI_DATE));

            ThietBi thietBi = new ThietBi(idThietBi, tenThietBi, xuatXu, date);
            list.add(thietBi);
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<String> getTenThietBi()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<String> list=new ArrayList<>();
        try
        {
            cursor=sqLiteDatabase.query(TABLE_THIETBI, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {

            String tenThietBi =cursor.getString(cursor.getColumnIndex(THIETBI_NAME));
            list.add(tenThietBi);
        }
        return list;
    }

    public void themThietBi(String tenThietBi, String xuatXu, String ngayNhap) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(THIETBI_NAME, tenThietBi);
        values.put(THIETBI_XUATXU, xuatXu);
        values.put(THIETBI_DATE, ngayNhap);

        db.insert(TABLE_THIETBI, null, values);
        db.close();
    }

    public void xoaThietBi(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_THIETBI,THIETBI_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateThietBi(String tenThietBi, String xuatXu, String ngayNhap, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(THIETBI_NAME, tenThietBi);
        values.put(THIETBI_XUATXU, xuatXu);
        values.put(THIETBI_DATE, ngayNhap);
        db.update(TABLE_THIETBI, values, THIETBI_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    //TRUY VAN QUAN LY DUY TRI THIET BI


    @SuppressLint("Range")
    public ArrayList<DuyTriThietBi> getDuyTriThietBi()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<DuyTriThietBi> list=new ArrayList<>();
        try
        {
            cursor=sqLiteDatabase.query(TABLE_DUYTRITHIETBI, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int idThietBi = cursor.getInt(cursor.getColumnIndex(DUYTRITHIETBI_ID));
            String tenThietBi =cursor.getString(cursor.getColumnIndex(DUYTRITHIETBI_NAME));
            String room = cursor.getString(cursor.getColumnIndex(DUYTRITHIETBI_ROOM));
            String status = cursor.getString(cursor.getColumnIndex(DUYTRITHIETBI_STATUS));
            String date = cursor.getString(cursor.getColumnIndex(DUYTRITHIETBI_DATE));

            DuyTriThietBi duyTriThietBithietBi = new DuyTriThietBi(idThietBi, tenThietBi, room, status, date);
            list.add(duyTriThietBithietBi);
        }
        return list;
    }

    public void themDuyTriThietBi(String tenThietBi, String tenPhong, String tinhTrang, String ngayLapDat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DUYTRITHIETBI_NAME, tenThietBi);
        values.put(DUYTRITHIETBI_ROOM, tenPhong);
        values.put(DUYTRITHIETBI_STATUS, tinhTrang);
        values.put(DUYTRITHIETBI_DATE, ngayLapDat);

        db.insert(TABLE_DUYTRITHIETBI, null, values);
        db.close();
    }

    public void xoaDuyTriThietBi(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DUYTRITHIETBI,DUYTRITHIETBI_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateDuyTriThietBi(String tenThietBi, String tenPhong, String tinhTrang, String ngayLapDat, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DUYTRITHIETBI_NAME, tenThietBi);
        values.put(DUYTRITHIETBI_ROOM, tenPhong);
        values.put(DUYTRITHIETBI_STATUS, tinhTrang);
        values.put(DUYTRITHIETBI_DATE, ngayLapDat);
        db.update(TABLE_DUYTRITHIETBI, values, DUYTRITHIETBI_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
// QL Va truy van tai khoan
    public void insertTaiKhoan(TaiKhoan tk){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(TEN_TAIKHOAN,tk.getTaiKhoan());
        contentValues.put(MATKHAU,tk.getMatKhau());
        contentValues.put(HOVATEN,tk.getHoVaTen());
        contentValues.put(CHUCVU,tk.getChucVu());
        sqLiteDatabase.insert(TABLE_TAIKHOAN,null,contentValues);
    }

    public void updateTaiKhoan(TaiKhoan tk){
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(TEN_TAIKHOAN,tk.getTaiKhoan());
        contentValues.put(MATKHAU,tk.getMatKhau());
        sqLiteDatabase.update(TABLE_TAIKHOAN, contentValues, TEN_TAIKHOAN + "=?", new String[]{String.valueOf(tk.getTaiKhoan())});
    }

    @SuppressLint("Range")
    public  ArrayList<TaiKhoan> getListTaiKhoan(){
        sqLiteDatabase =getReadableDatabase();
        ArrayList<TaiKhoan> list = new ArrayList<>();
        TaiKhoan taiKhoan;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_TAIKHOAN, null, null, null, null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            String taikhoan=cursor.getString(cursor.getColumnIndex(TEN_TAIKHOAN));
            String matkhau = cursor.getString(cursor.getColumnIndex(MATKHAU));
            String hoten = cursor.getString(cursor.getColumnIndex(HOVATEN));
            String chucvu = cursor.getString(cursor.getColumnIndex(CHUCVU));

            taiKhoan = new TaiKhoan(taikhoan,matkhau,hoten,chucvu);
            list.add(taiKhoan);
        }

        return list;
    }

    @SuppressLint("Range")
    public  TaiKhoan getTaikhoan(String tentk){
        sqLiteDatabase =getReadableDatabase();
        TaiKhoan taiKhoan=null;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_TAIKHOAN, null, TEN_TAIKHOAN+"=?", new String[]{String.valueOf(tentk)} ,null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            String taikhoan=cursor.getString(cursor.getColumnIndex(TEN_TAIKHOAN));
            String matkhau = cursor.getString(cursor.getColumnIndex(MATKHAU));
            String hoten = cursor.getString(cursor.getColumnIndex(HOVATEN));
            String chucvu = cursor.getString(cursor.getColumnIndex(CHUCVU));
            taiKhoan = new TaiKhoan(taikhoan,matkhau,hoten,chucvu);

        }

        return taiKhoan;
    }

   //Truy van va quan li  tang va phong

    public void insertTang(Tang tang)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(TANG_ID, tang.getId());
        contentValues.put(TANG_MOTA, tang.getMota());
        sqLiteDatabase.insert(TABLE_TANG,null,contentValues);
    }

    //add floor if not exist
    public void addFloor() {
        String countFloor = "SELECT * FROM "+TABLE_TANG;
        Cursor cursor1 = GetData(countFloor);
        cursor1.moveToFirst();
        if(!cursor1.moveToFirst()) {
            insertTang(new Tang("Tầng 1","Max 10 Phong"));
            insertTang(new Tang("Tầng 2","Max 10 Phong"));
            insertTang(new Tang("Tầng 3","Max 10 Phong"));
            insertTang(new Tang("Tầng 4","Max 10 Phong"));
            insertTang(new Tang("Tầng 5","Max 10 Phong"));
        }
    }
    public void addPhong() {
        String countPhong = "SELECT * FROM "+TABLE_PHONG;
        Cursor cursor = GetData(countPhong);
        cursor.moveToFirst();
        if(!cursor.moveToFirst()) {
            insertPhong(new Phong(102, 5, 5, "Thường", "Tầng 1"));
            insertPhong(new Phong(103, 5, 5, "Thường", "Tầng 1"));
            insertPhong(new Phong(203, 2, 2, "VIP", "Tầng 2"));
            insertPhong(new Phong(304, 5, 5, "Thường", "Tầng 3"));
            insertPhong(new Phong(405, 2, 2, "VIP", "Tầng 4"));
            insertPhong(new Phong(506, 5, 5, "Thường", "Tầng 5"));
        }
    }
    public void insertPhong(Phong phong)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(PHONG_ID,phong.getMaphong());
        contentValues.put(PHONG_SOGIUONG,phong.getSogiuong());
        contentValues.put(PHONG_SONGUOI,phong.getSonguoi());
        contentValues.put(PHONG_LOAIPHONG,phong.getLoaiphong());
        contentValues.put(PHONG_IDTANG,phong.getIdTang());
        sqLiteDatabase.insert(TABLE_PHONG,null,contentValues);
    }
    public void deletePhong(int id){
        sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TABLE_PHONG,PHONG_ID+"=?",new String[]{String.valueOf(id)});
    }
    public void updatePhong(Phong phong)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(PHONG_ID,phong.getMaphong());
        contentValues.put(PHONG_SOGIUONG,phong.getSogiuong());
        contentValues.put(PHONG_SONGUOI,phong.getSonguoi());
        contentValues.put(PHONG_LOAIPHONG,phong.getLoaiphong());
        sqLiteDatabase.update(TABLE_PHONG, contentValues, PHONG_ID + "=?", new String[]{String.valueOf(phong.getMaphong())});
    }

    @SuppressLint("Range")
    public ArrayList<Phong> getList(String idTang)
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Phong> list=new ArrayList<>();
        Phong phong;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_PHONG, null, PHONG_IDTANG +"=?", new String[]{String.valueOf(idTang)}, null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(PHONG_ID));
            int sogiuong = cursor.getInt(cursor.getColumnIndex(PHONG_SOGIUONG));
            int songuoi = cursor.getInt(cursor.getColumnIndex(PHONG_SONGUOI));
            String loaiphong = cursor.getString(cursor.getColumnIndex(PHONG_LOAIPHONG));
            String idtang = cursor.getString(cursor.getColumnIndex(PHONG_IDTANG));
            phong = new Phong(id,sogiuong,songuoi,loaiphong,idtang);
            list.add(phong);

        }

        return list;
    }
    @SuppressLint("Range")
    public ArrayList<Phong> getListLoaiPhong(String LoaiPhong)
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Phong> list=new ArrayList<>();
        Phong phong;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_PHONG, null, PHONG_LOAIPHONG +"=?", new String[]{String.valueOf(LoaiPhong)}, null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(PHONG_ID));
            int sogiuong = cursor.getInt(cursor.getColumnIndex(PHONG_SOGIUONG));
            int songuoi = cursor.getInt(cursor.getColumnIndex(PHONG_SONGUOI));
            String loaiphong = cursor.getString(cursor.getColumnIndex(PHONG_LOAIPHONG));
            String idtang = cursor.getString(cursor.getColumnIndex(PHONG_IDTANG));
            phong = new Phong(id,sogiuong,songuoi,loaiphong,idtang);
            list.add(phong);

        }

        return list;
    }

    @SuppressLint("Range")
    public ArrayList<Tang> getlistTang(){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Tang> list=new ArrayList<>();
        Tang tang;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_TANG, null, null, null, null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            String id=cursor.getString(cursor.getColumnIndex(TANG_ID));
            String mota = cursor.getString(cursor.getColumnIndex(TANG_MOTA));

            tang = new Tang(id,mota);
            list.add(tang);
        }

        return list;
    }

    @SuppressLint("Range")
    public ArrayList<Phong> checkID(int idPhong)
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Phong> list=new ArrayList<>();
        Phong phong;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_PHONG, null, PHONG_ID +"=?", new String[]{String.valueOf(idPhong)}, null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(PHONG_ID));
            int sogiuong = cursor.getInt(cursor.getColumnIndex(PHONG_SOGIUONG));
            int songuoi = cursor.getInt(cursor.getColumnIndex(PHONG_SONGUOI));
            String loaiphong = cursor.getString(cursor.getColumnIndex(PHONG_LOAIPHONG));
            String idtang = cursor.getString(cursor.getColumnIndex(PHONG_IDTANG));
            phong = new Phong(id,sogiuong,songuoi,loaiphong,idtang);
            list.add(phong);

        }

        return list;
    }

    //Truy van va thuc hien Dang Ky
    public void dangky(Nguoi nguoi)
    {
        sqLiteDatabase = getWritableDatabase ();
        contentValues = new ContentValues ();
        contentValues.put (MASV, nguoi.getMasv ());
        contentValues.put (HOVaTEN, nguoi.getHovaten ());
        contentValues.put (LOP, nguoi.getLop ());
        contentValues.put (KHOA, nguoi.getKhoa ());
        contentValues.put (GIOITINH, nguoi.getGioitinh ());
        contentValues.put (PHONG, nguoi.getPhong());
        sqLiteDatabase.insert (TABLE_DKY,null,contentValues);
    }
    public void delete(String Masv){
        sqLiteDatabase = getWritableDatabase ();
        sqLiteDatabase.delete (TABLE_DKY,MASV+"=?", new String[]{String.valueOf (Masv)});
    }
    public void update(Nguoi nguoi)
    {
        sqLiteDatabase = getWritableDatabase ();
        contentValues = new ContentValues ();
        contentValues.put (MASV, nguoi.getMasv ());
        contentValues.put (HOVaTEN, nguoi.getHovaten ());
        contentValues.put (LOP, nguoi.getLop ());
        contentValues.put (KHOA, nguoi.getKhoa ());
        contentValues.put (GIOITINH, nguoi.getGioitinh ());
        contentValues.put (PHONG, nguoi.getPhong());
        sqLiteDatabase.update (TABLE_DKY, contentValues,MASV+"=?", new String[]{String.valueOf (nguoi.getMasv ())});
    }

    @SuppressLint("Range")
    public ArrayList<Nguoi> getListNguoi()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Nguoi> list=new ArrayList<>();
        Nguoi nguoi;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_DKY, null, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            String Masv=cursor.getString (cursor.getColumnIndex(MASV));
            String Hovaten = cursor.getString(cursor.getColumnIndex(HOVaTEN));
            String Lop = cursor.getString (cursor.getColumnIndex (LOP) );
            String Khoa = cursor.getString (cursor.getColumnIndex (KHOA) );
            String GioiTinh = cursor.getString (cursor.getColumnIndex (GIOITINH) );
            int Phong = cursor.getInt (cursor.getColumnIndex (PHONG) );
            nguoi = new Nguoi(Masv,Hovaten,Lop,Khoa,GioiTinh,Phong);
            list.add(nguoi);

        }
        return list;
    }


    @SuppressLint("Range")
    public ArrayList<Nguoi> getListSvTrongphong(int maphong)
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Nguoi> list=new ArrayList<>();
        Nguoi nguoi;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_DKY, null, PHONG +"=?", new String[]{String.valueOf(maphong)}, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            String Masv=cursor.getString (cursor.getColumnIndex(MASV));
            String Hovaten = cursor.getString(cursor.getColumnIndex(HOVaTEN));
            String Lop = cursor.getString (cursor.getColumnIndex (LOP) );
            String Khoa = cursor.getString (cursor.getColumnIndex (KHOA) );
            String GioiTinh = cursor.getString (cursor.getColumnIndex (GIOITINH) );
            int Phong = cursor.getInt (cursor.getColumnIndex (PHONG) );
            nguoi = new Nguoi(Masv,Hovaten,Lop,Khoa,GioiTinh,Phong);
            list.add(nguoi);

        }
        return list;
    }
// tra ve 1 phong
    @SuppressLint("Range")
    public  Phong getMotPhong(int  maphong){
        sqLiteDatabase =getReadableDatabase();
        Phong phong=null;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_PHONG, null, PHONG_ID+"=?", new String[]{String.valueOf(maphong)} ,null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(PHONG_ID));
            int sogiuong = cursor.getInt(cursor.getColumnIndex(PHONG_SOGIUONG));
            int songuoi = cursor.getInt(cursor.getColumnIndex(PHONG_SONGUOI));
            String loaiphong = cursor.getString(cursor.getColumnIndex(PHONG_LOAIPHONG));
            String idtang = cursor.getString(cursor.getColumnIndex(PHONG_IDTANG));
            phong = new Phong(id,sogiuong,songuoi,loaiphong,idtang);

        }

        return phong;
    }

    //TRUY VAN QUAN LY DIEN NUOC
    @SuppressLint("Range")
    public ArrayList<Integer> getRoom()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Integer> list=new ArrayList<>();
        try
        {
            cursor=sqLiteDatabase.query(TABLE_PHONG, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            Integer id=cursor.getInt(cursor.getColumnIndex(PHONG_ID));
            list.add(id);
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<DienNuoc> getDienNuoc()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<DienNuoc> list=new ArrayList<>();
        try
        {
            cursor=sqLiteDatabase.query(TABLE_DIENNUOC, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int idDienNuoc = cursor.getInt(cursor.getColumnIndex(DIENNUOC_ID));
            String phong =cursor.getString(cursor.getColumnIndex(DIENNUOC_PHONG));
            String thang = cursor.getString(cursor.getColumnIndex(DIENNUOC_THANG));
            int soDien = cursor.getInt(cursor.getColumnIndex(DIENNUOC_SODIEN));
            int soNuoc = cursor.getInt(cursor.getColumnIndex(DIENNUOC_SONUOC));
            int tongTien = cursor.getInt(cursor.getColumnIndex(DIENNUOC_TONGTIEN));

            DienNuoc dienNuoc = new DienNuoc(idDienNuoc, phong, thang, soDien, soNuoc, tongTien);
            list.add(dienNuoc);
        }
        return list;
    }

    public void themDienNuoc(String maPhong, String thang, int soDien, int soNuoc, int tongTien) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DIENNUOC_PHONG, maPhong);
        values.put(DIENNUOC_THANG, thang);
        values.put(DIENNUOC_SODIEN, soDien);
        values.put(DIENNUOC_SONUOC, soNuoc);
        values.put(DIENNUOC_TONGTIEN, tongTien);

        db.insert(TABLE_DIENNUOC, null, values);
        db.close();
    }

    public void xoaDienNuoc(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIENNUOC,DIENNUOC_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateDienNuoc(String maPhong, String thang, int soDien, int soNuoc, int tongTien, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DIENNUOC_PHONG, maPhong);
        values.put(DIENNUOC_THANG, thang);
        values.put(DIENNUOC_SODIEN, soDien);
        values.put(DIENNUOC_SONUOC, soNuoc);
        values.put(DIENNUOC_TONGTIEN, tongTien);
        db.update(TABLE_DIENNUOC, values, DIENNUOC_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //TRUY VAN QUAN LY THANH TOAN

    @SuppressLint("Range")
    public ArrayList<Phong> getListRoomThanhToan(String thang)
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Phong> list=new ArrayList<>();
        Phong phong;
        Cursor cursor2;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_DIENNUOC, null, DIENNUOC_THANG + "=?",new String[]{String.valueOf(thang)},null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int idPhongdn =cursor.getInt(cursor.getColumnIndex(DIENNUOC_PHONG));


            cursor2 = sqLiteDatabase.query(TABLE_PHONG, null, PHONG_ID + "=?", new String[]{String.valueOf(idPhongdn)}, null
                    , null, null, null);
            while(cursor2.moveToNext())
            {

                int id=cursor2.getInt(cursor2.getColumnIndex(PHONG_ID));
                int sogiuong = cursor2.getInt(cursor2.getColumnIndex(PHONG_SOGIUONG));
                int songuoi = cursor2.getInt(cursor2.getColumnIndex(PHONG_SONGUOI));
                String loaiphong = cursor2.getString(cursor2.getColumnIndex(PHONG_LOAIPHONG));
                String idtang = cursor2.getString(cursor2.getColumnIndex(PHONG_IDTANG));
                phong = new Phong(id,sogiuong,songuoi,loaiphong,idtang);
                list.add(phong);
            }
        }
        return list;
    }

    @SuppressLint("Range")
    public int getSoNguoi(String roomName)
    {
        sqLiteDatabase=getReadableDatabase();
        int count = 0;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_PHONG, null, PHONG_ID + "=?",new String[]{String.valueOf(roomName)},null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            count = cursor.getInt(cursor.getColumnIndex(PHONG_SONGUOI));
        }
        return count;
    }
    @SuppressLint("Range")
    public DienNuoc getTTDienNuoc(String phong, String thang)
    {
        sqLiteDatabase=getReadableDatabase();
        DienNuoc dienNuoc = null;
        try
        {
            String sql = "SELECT * FROM dienNuoc WHERE maPhong ='"+phong+"' AND thang = '"+thang+"'";
            cursor=sqLiteDatabase.rawQuery(sql, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int idDienNuoc = cursor.getInt(cursor.getColumnIndex(DIENNUOC_ID));
            String phong2 =cursor.getString(cursor.getColumnIndex(DIENNUOC_PHONG));
            String thang2 = cursor.getString(cursor.getColumnIndex(DIENNUOC_THANG));
            int soDien = cursor.getInt(cursor.getColumnIndex(DIENNUOC_SODIEN));
            int soNuoc = cursor.getInt(cursor.getColumnIndex(DIENNUOC_SONUOC));
           int tongTien2 = cursor.getInt(cursor.getColumnIndex(DIENNUOC_TONGTIEN));

            dienNuoc = new DienNuoc(idDienNuoc, phong2, thang2, soDien, soNuoc, tongTien2);
        }
        return dienNuoc;
    }

    @SuppressLint("Range")
    public ArrayList<ThanhToan> getThanhToan()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<ThanhToan> list=new ArrayList<>();
        try
        {
            cursor=sqLiteDatabase.query(TABLE_THANHTOAN, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int idThanhToan = cursor.getInt(cursor.getColumnIndex(THANHTOAN_ID));
            String thang = cursor.getString(cursor.getColumnIndex(THANHTOAN_THANG));
            String phong = cursor.getString(cursor.getColumnIndex(THANHTOAN_PHONG));
            int tienDienNuoc = cursor.getInt(cursor.getColumnIndex(THANHTOAN_DIENNUOC));
            int tienDichVu = cursor.getInt(cursor.getColumnIndex(THANHTOAN_DICHVU));
            int tongTien = cursor.getInt(cursor.getColumnIndex(THANHTOAN_TONGTIEN));
            String tinhTrang = cursor.getString(cursor.getColumnIndex(THANHTOAN_TINHTRANG));


            ThanhToan thanhToan = new ThanhToan(idThanhToan, thang, phong, tienDienNuoc, tienDichVu, tongTien, tinhTrang);
            list.add(thanhToan);
        }
        return list;
    }



    public void themThanhToan(String thang, String maPhong, int dienNuoc, int dichVu, int tongTien, String tinhTrang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(THANHTOAN_THANG, thang);
        values.put(THANHTOAN_PHONG, maPhong);
        values.put(THANHTOAN_DIENNUOC, dienNuoc);
        values.put(THANHTOAN_DICHVU, dichVu);
        values.put(THANHTOAN_TONGTIEN, tongTien);
        values.put(THANHTOAN_TINHTRANG, tinhTrang);

        db.insert(TABLE_THANHTOAN, null, values);
        db.close();
    }


    public void xoaThanhToan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_THANHTOAN,THANHTOAN_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateThanhToan(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(THANHTOAN_TINHTRANG, "Đã thanh toán");

        db.update(TABLE_THANHTOAN, values, THANHTOAN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
