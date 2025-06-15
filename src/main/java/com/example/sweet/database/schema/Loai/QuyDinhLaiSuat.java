package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuyDinhLaiSuat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "nguoi_lap_quy_dinh", nullable = false)
    private NhanVien nguoiLapQuyDinh;

    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;
}
