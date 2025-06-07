package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PhieuTraLai {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int phieuTraLaiID;
    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien", nullable = false)
    private PhieuGuiTien phieuGuiTien;

    private LocalDateTime ngayTraLai;

    public int getPhieuTraLaiID() {
        return phieuTraLaiID;
    }

    public void setPhieuTraLaiID(int phieuTraLaiID) {
        this.phieuTraLaiID = phieuTraLaiID;
    }

    public GiaoDich getGiaoDich() {
        return giaoDich;
    }

    public void setGiaoDich(GiaoDich giaoDich) {
        this.giaoDich = giaoDich;
    }

    public PhieuGuiTien getPhieuGuiTien() {
        return phieuGuiTien;
    }

    public void setPhieuGuiTien(PhieuGuiTien phieuGuiTien) {
        this.phieuGuiTien = phieuGuiTien;
    }

    public LocalDateTime getNgayTraLai() {
        return ngayTraLai;
    }

    public void setNgayTraLai(LocalDateTime ngayTraLai) {
        this.ngayTraLai = ngayTraLai;
    }
}
