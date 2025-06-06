package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PhieuRutTien {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int phieuRutTienID;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien", nullable = false)
    private PhieuGuiTien phieuGuiTien;
    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;

    private int soTienRut;
    private LocalDateTime ngayRut;
    private float laiSuatKhongKyHan;

    public int getPhieuRutTienID() {
        return phieuRutTienID;
    }

    public void setPhieuRutTienID(int phieuRutTienID) {
        this.phieuRutTienID = phieuRutTienID;
    }

    public PhieuGuiTien getPhieuGuiTien() {
        return phieuGuiTien;
    }

    public void setPhieuGuiTien(PhieuGuiTien phieuGuiTien) {
        this.phieuGuiTien = phieuGuiTien;
    }

    public GiaoDich getGiaoDich() {
        return giaoDich;
    }

    public void setGiaoDich(GiaoDich giaoDich) {
        this.giaoDich = giaoDich;
    }

    public int getSoTienRut() {
        return soTienRut;
    }

    public void setSoTienRut(int soTienRut) {
        this.soTienRut = soTienRut;
    }

    public LocalDateTime getNgayRut() {
        return ngayRut;
    }

    public void setNgayRut(LocalDateTime ngayRut) {
        this.ngayRut = ngayRut;
    }

    public float getLaiSuatKhongKyHan() {
        return laiSuatKhongKyHan;
    }

    public void setLaiSuatKhongKyHan(float laiSuatKhongKyHan) {
        this.laiSuatKhongKyHan = laiSuatKhongKyHan;
    }
}
