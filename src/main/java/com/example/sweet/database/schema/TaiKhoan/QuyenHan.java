package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuyenHan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int quyenHanID;
    private String tenQuyenHan;
    private String moTa;

    public int getQuyenHanID() {
        return quyenHanID;
    }

    public void setQuyenHanID(int quyenHanID) {
        this.quyenHanID = quyenHanID;
    }

    public String getTenQuyenHan() {
        return tenQuyenHan;
    }

    public void setTenQuyenHan(String tenQuyenHan) {
        this.tenQuyenHan = tenQuyenHan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
