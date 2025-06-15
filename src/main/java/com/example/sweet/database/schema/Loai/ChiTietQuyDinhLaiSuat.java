package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
