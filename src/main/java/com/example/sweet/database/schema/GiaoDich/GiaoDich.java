package com.example.sweet.database.schema.GiaoDich;

import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
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
public class GiaoDich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giaoDichID;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_nguon", nullable = false)
    private TaiKhoanThanhToan taiKhoanNguon;
    @ManyToOne
    @JoinColumn(name = "loai_tai_khoan_nguon", nullable = false)
    private LoaiTaiKhoan loaiTaiKhoanNguon;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_dich", nullable = false)
    private TaiKhoanThanhToan taiKhoanDich;
    @ManyToOne
    @JoinColumn(name = "loai_tai_khoan_dich", nullable = false)
    private LoaiTaiKhoan loaiTaiKhoanDich;

    @ManyToOne
    @JoinColumn(name = "loai_giao_dich", nullable = false)
    private LoaiGiaoDich loaiGiaoDich;

    @ManyToOne
    @JoinColumn(name = "kenh_giao_dich", nullable = false)
    private KenhGiaoDich kenhGiaoDich;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_giao_dich", nullable = false)
    private NhanVien nhanVienGiaoDich;

    private int soTienGiaoDich;
    private String noiDung;
    private LocalDateTime thoiGianGiaoDich;
}
