package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int khachHangID;
    private String hoTen;
    private LocalDateTime ngaySinh;
    private String cccd;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dia_chi_thuong_tru")
    private DiaChi diaChiThuongTruID;
    @ManyToOne
    @JoinColumn(name = "dia_chi_lien_lac")
    private DiaChi diaChiLienLacID;
    private LocalDateTime ngayTuyenDung;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vai_tro")
    private VaiTro vaiTroID;
    @ManyToOne
    @JoinColumn(name = "trang_thai_tai_khoan")
    private TrangThai trangThaiTaiKhoan;
}
