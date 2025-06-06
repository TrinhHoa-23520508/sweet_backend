package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NhanVien {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int nhanVienID;
    private String hoTen;
    private LocalDateTime ngaySinh;
    private String cccd;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dia_chi_thuong_tru", nullable = false)
    private DiaChi diaChiThuongTruID;
    @ManyToOne
    @JoinColumn(name = "dia_chi_lien_lac", nullable = false)
    private DiaChi diaChiLienLacID;
    private LocalDateTime ngayTuyenDung;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vai_tro", nullable = false)
    private VaiTro vaiTro;
//    @OneToMany(mappedBy = "quyenHan")
//    private List<VaiTroQuyenHan> danhSachVaiTro;
    @ManyToOne
    @JoinColumn(name = "trang_thai_tai_khoan", nullable = false)
    private TrangThai trangThaiTaiKhoan;

    public int getNhanVienID() {
        return nhanVienID;
    }

    public void setNhanVienID(int nhanVienID) {
        this.nhanVienID = nhanVienID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDateTime getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDateTime ngaySinh) {
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

    public LocalDateTime getNgayTuyenDung() {
        return ngayTuyenDung;
    }

    public void setNgayTuyenDung(LocalDateTime ngayTuyenDung) {
        this.ngayTuyenDung = ngayTuyenDung;
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
}
