package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import jakarta.persistence.*;

@Entity
public class ChiTietQuyDinhLaiSuat {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int chiTietQuyDinhID;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_nguon", nullable = false)
    private QuyDinhLaiSuat quyDinhLaiSuat;

    @ManyToOne
    @JoinColumn(name = "loai_tiet_kiem", nullable = false)
    private LoaiTietKiem loaiTietKiem;

    @ManyToOne
    @JoinColumn(name = "tan_suat_nhan_lai", nullable = false)
    private TanSuatNhanLai tanSuatNhanLai;

    @ManyToOne
    @JoinColumn(name = "loai_ky_han", nullable = false)
    private LoaiKyHan loaiKyHan;

    public int getChiTietQuyDinhID() {
        return chiTietQuyDinhID;
    }

    public void setChiTietQuyDinhID(int chiTietQuyDinhID) {
        this.chiTietQuyDinhID = chiTietQuyDinhID;
    }

    public QuyDinhLaiSuat getQuyDinhLaiSuat() {
        return quyDinhLaiSuat;
    }

    public void setQuyDinhLaiSuat(QuyDinhLaiSuat quyDinhLaiSuat) {
        this.quyDinhLaiSuat = quyDinhLaiSuat;
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
}
