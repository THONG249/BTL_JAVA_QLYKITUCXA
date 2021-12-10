package com.example.baitaplon_nhom7_kytucxa.Utiti;

public class DienNuoc {

    int id, soDien, soNuoc;
    String phong, thang;
    int tongTien;

    public DienNuoc() {
    }

    public DienNuoc(int id, String phong, String thang, int soDien, int soNuoc, int tongTien) {
        this.id = id;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.phong = phong;
        this.thang = thang;
        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoDien() {
        return soDien;
    }

    public void setSoDien(int soDien) {
        this.soDien = soDien;
    }

    public int getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        this.soNuoc = soNuoc;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
