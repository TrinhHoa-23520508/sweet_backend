package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PhieuTraLai {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int phieuTraLaiID;
    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien", nullable = false)
    private PhieuGuiTien phieuGuiTien;

    private LocalDateTime ngayTraLai;
}
