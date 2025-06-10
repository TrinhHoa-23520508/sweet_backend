package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class TaiKhoanThanhToan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private int soTaiKhoan;

    @ManyToOne
    @JoinColumn(name = "khach_hang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "trang_thai", nullable = false)
    private TrangThai trangThai;

    private int soDu;
    private LocalDateTime ngayTao;
}
