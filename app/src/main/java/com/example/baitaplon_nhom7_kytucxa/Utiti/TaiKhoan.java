package com.example.baitaplon_nhom7_kytucxa.Utiti;

public class TaiKhoan {
    private String TaiKhoan;
    private String MatKhau;
    private String HoVaTen;
    private String ChucVu;


    public TaiKhoan(String taiKhoan, String matKhau, String hoVaTen, String chucVu) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        HoVaTen = hoVaTen;
        ChucVu = chucVu;
    }
    public TaiKhoan(String taiKhoan, String matKhau) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
    }
    public  TaiKhoan(){}

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }
}
