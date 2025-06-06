package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class QuyDinhLaiSuat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "nguoi_lap_quy_dinh", nullable = false)
    private NhanVien nguoiLapQuyDinh;

    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;
}
