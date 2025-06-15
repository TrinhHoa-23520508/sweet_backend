package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long soTaiKhoan;

    @ManyToOne
    @JoinColumn(name = "khach_hang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "trang_thai", nullable = false)
    private TrangThai trangThai;

    private Long soDu;
    private LocalDateTime ngayTao;
}
