package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HinhThucDaoHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hinhThucDaoHangID;
    private String tenHinhThucDaoHang;
    private int maHinhThucDaoHang;
    private String moTa;

    public Long getHinhThucDaoHangID() {
        return hinhThucDaoHangID;
    }

    public void setHinhThucDaoHangID(Long hinhThucDaoHangID) {
        this.hinhThucDaoHangID = hinhThucDaoHangID;
    }

    public String getTenHinhThucDaoHang() {
        return tenHinhThucDaoHang;
    }

    public void setTenHinhThucDaoHang(String tenHinhThucDaoHang) {
        this.tenHinhThucDaoHang = tenHinhThucDaoHang;
    }

    public int getMaHinhThucDaoHang() {
        return maHinhThucDaoHang;
    }

    public void setMaHinhThucDaoHang(int maHinhThucDaoHang) {
        this.maHinhThucDaoHang = maHinhThucDaoHang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
