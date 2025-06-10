package com.example.sweet.database.schema.GiaoDich;

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
@AllArgsConstructor()
public class LichSuGiaoDich_TKTT {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private int lichSuGiaoDichID;

    @ManyToOne
    @JoinColumn(name = "tai_khoan", nullable = false)
    private TaiKhoanThanhToan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;

    private int SoDuSauGD;
}
