package com.example.baitaplon_nhom7_kytucxa.Utiti;

public class Tang {
    private String id;
    private String mota;

    public Tang() {
    }

    public Tang(String id, String mota) {
        this.id = id;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    public String toString()
    {
        return this.id;
    }
}
