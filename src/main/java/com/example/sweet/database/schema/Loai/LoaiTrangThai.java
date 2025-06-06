package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LoaiTrangThai {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loaiTrangThaiID;
    private int maLoaiTrangThai;
    private String tenLoaiTrangThai;
    private String moTa;

    public int getLoaiTrangThaiID() {
        return loaiTrangThaiID;
    }

    public void setLoaiTrangThaiID(int loaiTrangThaiID) {
        this.loaiTrangThaiID = loaiTrangThaiID;
    }

    public int getMaLoaiTrangThai() {
        return maLoaiTrangThai;
    }

    public void setMaLoaiTrangThai(int maLoaiTrangThai) {
        this.maLoaiTrangThai = maLoaiTrangThai;
    }

    public String getTenLoaiTrangThai() {
        return tenLoaiTrangThai;
    }

    public void setTenLoaiTrangThai(String tenLoaiTrangThai) {
        this.tenLoaiTrangThai = tenLoaiTrangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
