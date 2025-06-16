package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long khachHangID;
    private String hoTen;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String cccd;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dia_chi_thuong_tru")
    private DiaChi diaChiThuongTru;

    @ManyToOne
    @JoinColumn(name = "dia_chi_lien_lac")
    private DiaChi diaChiLienLac;
    private LocalDate ngayDangKy;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vai_tro")
    private VaiTro vaiTro;

    @ManyToOne
    @JoinColumn(name = "trang_thai_tai_khoan")
    private TrangThai trangThaiTaiKhoan;

    @ManyToOne
    @JoinColumn(name = "trang_thai_khach_hang")
    private TrangThai trangThaiKhachHang;


}
