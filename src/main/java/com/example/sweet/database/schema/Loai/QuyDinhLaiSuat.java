package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class QuyDinhLaiSuat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "nguoi_lap_quy_dinh", nullable = false)
    private NhanVien nguoiLapQuyDinh;

    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;

    public int getQuyDinhLaiSuatID() {
        return quyDinhLaiSuatID;
    }

    public void setQuyDinhLaiSuatID(int quyDinhLaiSuatID) {
        this.quyDinhLaiSuatID = quyDinhLaiSuatID;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public NhanVien getNguoiLapQuyDinh() {
        return nguoiLapQuyDinh;
    }

    public void setNguoiLapQuyDinh(NhanVien nguoiLapQuyDinh) {
        this.nguoiLapQuyDinh = nguoiLapQuyDinh;
    }

    public float getLaiSuatKhongKyHan() {
        return laiSuatKhongKyHan;
    }

    public void setLaiSuatKhongKyHan(float laiSuatKhongKyHan) {
        this.laiSuatKhongKyHan = laiSuatKhongKyHan;
    }

    public int getSoTienGuiToiThieu() {
        return soTienGuiToiThieu;
    }

    public void setSoTienGuiToiThieu(int soTienGuiToiThieu) {
        this.soTienGuiToiThieu = soTienGuiToiThieu;
    }
}
