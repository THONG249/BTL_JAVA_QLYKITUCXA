package com.example.baitaplon_nhom7_kytucxa.Utiti;

public class ThietBi {
    public Integer IdThietBi;
    private String tenThietBi;
    private String xuatXu;
    private String ngayNhap;

    public ThietBi() {
    }

    public ThietBi(Integer idThietBi, String tenThietBi, String xuatXu, String ngayNhap) {
        IdThietBi = idThietBi;
        this.tenThietBi = tenThietBi;
        this.xuatXu = xuatXu;
        this.ngayNhap = ngayNhap;
    }

    public Integer getIdThietBi() {
        return IdThietBi;
    }

    public void setIdThietBi(Integer idThietBi) {
        IdThietBi = idThietBi;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public String toString() {
        return "ThietBi{" +
                "IdThietBi=" + IdThietBi +
                ", tenThietBi='" + tenThietBi + '\'' +
                ", xuatXu='" + xuatXu + '\'' +
                ", ngayNhap='" + ngayNhap + '\'' +
                '}';
    }
}
