package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TaiKhoanThanhToan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int SoTaiKhoan;

    @ManyToOne
    @JoinColumn(name = "khach_hang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "trang_thai", nullable = false)
    private TrangThai trangThai;

    private LocalDateTime ngayTao;
}
