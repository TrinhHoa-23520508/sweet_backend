package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiGiaoDich {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int loaiGiaoDichID;
    private String tenLoaiGiaoDich;
    private int maLoaiGiaoDich;
    private String moTa;

    public int getLoaiGiaoDichID() {
        return loaiGiaoDichID;
    }

    public void setLoaiGiaoDichID(int loaiGiaoDichID) {
        this.loaiGiaoDichID = loaiGiaoDichID;
    }

    public String getTenLoaiGiaoDich() {
        return tenLoaiGiaoDich;
    }

    public void setTenLoaiGiaoDich(String tenLoaiGiaoDich) {
        this.tenLoaiGiaoDich = tenLoaiGiaoDich;
    }

    public int getMaLoaiGiaoDich() {
        return maLoaiGiaoDich;
    }

    public void setMaLoaiGiaoDich(int maLoaiGiaoDich) {
        this.maLoaiGiaoDich = maLoaiGiaoDich;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
