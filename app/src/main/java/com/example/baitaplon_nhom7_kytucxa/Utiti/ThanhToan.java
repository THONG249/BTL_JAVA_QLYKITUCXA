package com.example.baitaplon_nhom7_kytucxa.Utiti;

public class ThanhToan {

    int id;
    String thang, phong, tinhTrang;
    int tienDienNuoc, tienDichVu, tongTien;

    public ThanhToan(int id, String thang, String phong, int tienDienNuoc, int tienDichVu, int tongTien, String tinhTrang) {
        this.id = id;
        this.thang = thang;
        this.phong = phong;
        this.tienDienNuoc = tienDienNuoc;
        this.tienDichVu = tienDichVu;
        this.tongTien = tongTien;
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getTienDienNuoc() {
        return tienDienNuoc;
    }

    public void setTienDienNuoc(int tienDienNuoc) {
        this.tienDienNuoc = tienDienNuoc;
    }
    public int getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(int tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
