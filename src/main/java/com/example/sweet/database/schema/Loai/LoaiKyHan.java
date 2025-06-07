package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiKyHan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int loaiKyHanID;
    private String tenLoaiKyHan;
    private int soThang;

    public int getLoaiKyHanID() {
        return loaiKyHanID;
    }

    public void setLoaiKyHanID(int loaiKyHanID) {
        this.loaiKyHanID = loaiKyHanID;
    }

    public String getTenLoaiKyHan() {
        return tenLoaiKyHan;
    }

    public void setTenLoaiKyHan(String tenLoaiKyHan) {
        this.tenLoaiKyHan = tenLoaiKyHan;
    }

    public int getSoThang() {
        return soThang;
    }

    public void setSoThang(int soThang) {
        this.soThang = soThang;
    }
}
