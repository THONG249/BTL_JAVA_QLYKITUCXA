package com.example.baitaplon_nhom7_kytucxa.Utiti;

import java.io.Serializable;

public class Nguoi implements Serializable {
    String masv, Hovaten, Lop, Gioitinh,Khoa;
    int phong;
    public Nguoi(){
    }
    public Nguoi(String masv, String hovaten, String lop, String khoa, String gioitinh, int phong) {
        this.masv = masv;
        this.Hovaten = hovaten;
        this.Lop = lop;
        this.Khoa = khoa;
        this.Gioitinh = gioitinh;
       this.phong=phong;
    }



    public String getMasv() {

        return masv;
    }

    public void setMasv(String masv) {

        this.masv = masv;
    }

    public void setHovaten(String hovaten) {

        this.Hovaten = hovaten;
    }

    public String getHovaten() {

        return Hovaten;
    }

    public String getLop() {

        return Lop;
    }

    public void setLop(String lop) {

        this.Lop = lop;
    }

    public String getKhoa() {

        return Khoa;
    }

    public void setKhoa(String khoa) {

        this.Khoa = khoa;
    }

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }

    public String getGioitinh() {

        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {

        Gioitinh = gioitinh;
    }

    public String toString(){
        return this.masv+" - "+this.Hovaten+" - "+this.Lop+" - "+this.Khoa+" - "+this.Gioitinh+" - "+this.phong;
    }
}
