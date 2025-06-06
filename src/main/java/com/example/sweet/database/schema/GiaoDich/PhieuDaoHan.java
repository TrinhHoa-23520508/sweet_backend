package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PhieuDaoHan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int phieuDaoHanID;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_ky_truoc", nullable = false)
    private PhieuGuiTien phieuGuiTienKyTruoc;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_tiep_theo", nullable = false)
    private PhieuGuiTien phieuGuiTienTiepTheo;

    private LocalDateTime ngayDaoHan;
}
