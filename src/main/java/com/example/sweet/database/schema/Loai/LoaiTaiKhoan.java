package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiTaiKhoan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int loaiTaiKhoanID;
    private String tenLoaiTaiKhoan;
    private int maLoaiTaiKhoan;
    private String moTa;

    public int getLoaiTaiKhoanID() {
        return loaiTaiKhoanID;
    }

    public void setLoaiTaiKhoanID(int loaiTaiKhoanID) {
        this.loaiTaiKhoanID = loaiTaiKhoanID;
    }

    public String getTenLoaiTaiKhoan() {
        return tenLoaiTaiKhoan;
    }

    public void setTenLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
    }

    public int getMaLoaiTaiKhoan() {
        return maLoaiTaiKhoan;
    }

    public void setMaLoaiTaiKhoan(int maLoaiTaiKhoan) {
        this.maLoaiTaiKhoan = maLoaiTaiKhoan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
