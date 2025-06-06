package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import jakarta.persistence.*;

@Entity
public class ChiTietQuyDinhLaiSuat {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
}
