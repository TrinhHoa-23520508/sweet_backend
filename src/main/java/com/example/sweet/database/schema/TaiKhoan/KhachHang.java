package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long khachHangID;
    private String hoTen;
    private LocalDate ngaySinh;
    private String cccd;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dia_chi_thuong_tru")
    private DiaChi diaChiThuongTruID;

    public Long getKhachHangID() {
        return khachHangID;
    }

    public void setKhachHangID(Long khachHangID) {
        this.khachHangID = khachHangID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DiaChi getDiaChiThuongTruID() {
        return diaChiThuongTruID;
    }

    public void setDiaChiThuongTruID(DiaChi diaChiThuongTruID) {
        this.diaChiThuongTruID = diaChiThuongTruID;
    }

    public DiaChi getDiaChiLienLacID() {
        return diaChiLienLacID;
    }

    public void setDiaChiLienLacID(DiaChi diaChiLienLacID) {
        this.diaChiLienLacID = diaChiLienLacID;
    }

    public LocalDate getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(LocalDate ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }

    public TrangThai getTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public void setTrangThaiTaiKhoan(TrangThai trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

    @ManyToOne
    @JoinColumn(name = "dia_chi_lien_lac")
    private DiaChi diaChiLienLacID;
    private LocalDate ngayDangKy;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vai_tro")
    private VaiTro vaiTro;

    @ManyToOne
    @JoinColumn(name = "trang_thai_tai_khoan")
    private TrangThai trangThaiTaiKhoan;

}
