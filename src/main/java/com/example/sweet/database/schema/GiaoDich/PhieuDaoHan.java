package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PhieuDaoHan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int phieuDaoHanID;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_ky_truoc", nullable = false)
    private PhieuGuiTien phieuGuiTienKyTruoc;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_tiep_theo", nullable = false)
    private PhieuGuiTien phieuGuiTienTiepTheo;

    private LocalDateTime ngayDaoHan;

    public int getPhieuDaoHanID() {
        return phieuDaoHanID;
    }

    public void setPhieuDaoHanID(int phieuDaoHanID) {
        this.phieuDaoHanID = phieuDaoHanID;
    }

    public PhieuGuiTien getPhieuGuiTienKyTruoc() {
        return phieuGuiTienKyTruoc;
    }

    public void setPhieuGuiTienKyTruoc(PhieuGuiTien phieuGuiTienKyTruoc) {
        this.phieuGuiTienKyTruoc = phieuGuiTienKyTruoc;
    }

    public PhieuGuiTien getPhieuGuiTienTiepTheo() {
        return phieuGuiTienTiepTheo;
    }

    public void setPhieuGuiTienTiepTheo(PhieuGuiTien phieuGuiTienTiepTheo) {
        this.phieuGuiTienTiepTheo = phieuGuiTienTiepTheo;
    }

    public LocalDateTime getNgayDaoHan() {
        return ngayDaoHan;
    }

    public void setNgayDaoHan(LocalDateTime ngayDaoHan) {
        this.ngayDaoHan = ngayDaoHan;
    }
}
