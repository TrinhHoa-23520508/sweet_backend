package com.example.sweet.database.schema.GiaoDich;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import jakarta.persistence.*;

@Entity
public class LichSuGiaoDich_TKTT {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int lichSuGiaoDichID;

    @ManyToOne
    @JoinColumn(name = "tai_khoan", nullable = false)
    private TaiKhoanThanhToan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;

    private int SoDu_SauGD;
}
