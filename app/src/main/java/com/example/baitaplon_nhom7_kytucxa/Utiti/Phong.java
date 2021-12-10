package com.example.baitaplon_nhom7_kytucxa.Utiti;

import java.io.Serializable;

public class Phong implements Serializable {
    private int maphong;
    private int sogiuong;
    private int songuoi;
    private String loaiphong;
    private String idTang;

    public Phong() {
    }

    public Phong(int maphong, int sogiuong, int songuoi, String loaiphong, String idTang) {
        this.maphong = maphong;
        this.sogiuong = sogiuong;
        this.songuoi = songuoi;
        this.loaiphong = loaiphong;
        this.idTang = idTang;
    }

    public Phong(int maphong, int sogiuong, int songuoi, String loaiphong) {
        this.maphong = maphong;
        this.sogiuong = sogiuong;
        this.songuoi = songuoi;
        this.loaiphong = loaiphong;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public int getSogiuong() {
        return sogiuong;
    }

    public void setSogiuong(int sogiuong) {
        this.sogiuong = sogiuong;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public String getIdTang() {
        return idTang;
    }

    public void setIdTang(String idTang) {
        this.idTang = idTang;
    }

    public String toString(){
        return this.maphong+"";
    }
}
