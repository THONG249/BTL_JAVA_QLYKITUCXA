package com.example.baitaplon_nhom7_kytucxa.Utiti;

public class DuyTriThietBi {
    public Integer IDThietBi;
    private String tenThietBi;
    private String maPhong;
    private String tinhTrang;
    private String ngayLap;

    public DuyTriThietBi() {
    }

    public DuyTriThietBi(Integer IDThietBi, String tenThietBi, String maPhong, String tinhTrang, String ngayLap) {
        this.IDThietBi = IDThietBi;
        this.tenThietBi = tenThietBi;
        this.maPhong = maPhong;
        this.tinhTrang = tinhTrang;
        this.ngayLap = ngayLap;
    }

    public Integer getIDThietBi() {
        return IDThietBi;
    }

    public void setIDThietBi(Integer IDThietBi) {
        this.IDThietBi = IDThietBi;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String toString() {
        return "Ten thiet bi: " +this.tenThietBi + " - Ma phong : " + this.maPhong + "\n Tinh Trang: " + this.tinhTrang + "- Ngay lap: "+ this.ngayLap;
    }
}
