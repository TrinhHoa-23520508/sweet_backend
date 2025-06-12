package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiKyHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loaiKyHanID;
    private String tenLoaiKyHan;
    private int soThang;

    public Long getLoaiKyHanID() {
        return loaiKyHanID;
    }

    public void setLoaiKyHanID(Long loaiKyHanID) {
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
