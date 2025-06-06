package com.example.sweet.database.schema.GiaoDich;

import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PhieuGuiTien {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int phieuGuiTienID;
    @ManyToOne
    @JoinColumn(name = "khach_hang", nullable = false)
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_giao_dich", nullable = false)
    private NhanVien giaoDichVien;

    @ManyToOne
    @JoinColumn(name = "loai_tiet_kiem", nullable = false)
    private LoaiTietKiem loaiTietKiem;
    @ManyToOne
    @JoinColumn(name = "tan_suat_nhan_lai", nullable = false)
    private TanSuatNhanLai tanSuatNhanLai;
    @ManyToOne
    @JoinColumn(name = "loai_ky_han", nullable = false)
    private LoaiKyHan loaiKyHan;

    @ManyToOne
    @JoinColumn(name = "hinh_thuc_dao_han", nullable = false)
    private HinhThucDaoHan hinhThucDaoHan;

    private LocalDate ngayGuiTien;
    private int soTienGuiBanDau;
    private float laiSuatCamKet;
    private String tenGoiNho;
    @ManyToOne
    @JoinColumn(name = "trang_thai", nullable = false)
    private TrangThai trangThai;

    public int getPhieuGuiTienID() {
        return phieuGuiTienID;
    }

    public void setPhieuGuiTienID(int phieuGuiTienID) {
        this.phieuGuiTienID = phieuGuiTienID;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public GiaoDich getGiaoDich() {
        return giaoDich;
    }

    public void setGiaoDich(GiaoDich giaoDich) {
        this.giaoDich = giaoDich;
    }

    public NhanVien getGiaoDichVien() {
        return giaoDichVien;
    }

    public void setGiaoDichVien(NhanVien giaoDichVien) {
        this.giaoDichVien = giaoDichVien;
    }

    public LoaiTietKiem getLoaiTietKiem() {
        return loaiTietKiem;
    }

    public void setLoaiTietKiem(LoaiTietKiem loaiTietKiem) {
        this.loaiTietKiem = loaiTietKiem;
    }

    public TanSuatNhanLai getTanSuatNhanLai() {
        return tanSuatNhanLai;
    }

    public void setTanSuatNhanLai(TanSuatNhanLai tanSuatNhanLai) {
        this.tanSuatNhanLai = tanSuatNhanLai;
    }

    public LoaiKyHan getLoaiKyHan() {
        return loaiKyHan;
    }

    public void setLoaiKyHan(LoaiKyHan loaiKyHan) {
        this.loaiKyHan = loaiKyHan;
    }

    public HinhThucDaoHan getHinhThucDaoHan() {
        return hinhThucDaoHan;
    }

    public void setHinhThucDaoHan(HinhThucDaoHan hinhThucDaoHan) {
        this.hinhThucDaoHan = hinhThucDaoHan;
    }

    public LocalDate getNgayGuiTien() {
        return ngayGuiTien;
    }

    public void setNgayGuiTien(LocalDate ngayGuiTien) {
        this.ngayGuiTien = ngayGuiTien;
    }

    public int getSoTienGuiBanDau() {
        return soTienGuiBanDau;
    }

    public void setSoTienGuiBanDau(int soTienGuiBanDau) {
        this.soTienGuiBanDau = soTienGuiBanDau;
    }

    public float getLaiSuatCamKet() {
        return laiSuatCamKet;
    }

    public void setLaiSuatCamKet(float laiSuatCamKet) {
        this.laiSuatCamKet = laiSuatCamKet;
    }

    public String getTenGoiNho() {
        return tenGoiNho;
    }

    public void setTenGoiNho(String tenGoiNho) {
        this.tenGoiNho = tenGoiNho;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }
}
