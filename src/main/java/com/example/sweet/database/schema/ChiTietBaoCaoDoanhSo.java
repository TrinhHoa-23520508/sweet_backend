package com.example.sweet.database.schema;

import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietBaoCaoDoanhSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chiTietBaoCaoID;

    @ManyToOne
    @JoinColumn(name = "bao_cao_doanh_so_id")
    private BaoCaoDoanhSo baoCaoDoanhSo;

    @ManyToOne
    @JoinColumn(name = "loai_tiet_kiem_id")
    private LoaiTietKiem loaiTietKiem;

    @ManyToOne
    @JoinColumn(name = "loai_ky_han_id")
    private LoaiKyHan loaiKyHan;

    @DecimalMin(value = "0.00", inclusive = true, message = "Tổng thu không được âm")
    private BigDecimal tongThu;

    @DecimalMin(value = "0.00", inclusive = true, message = "Tổng chi không đuược âm")
    private BigDecimal tongChi;

    private BigDecimal chengLech;


}
