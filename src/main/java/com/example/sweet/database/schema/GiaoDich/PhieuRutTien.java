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
}
