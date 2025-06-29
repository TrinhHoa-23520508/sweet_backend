package com.example.sweet.database.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaoCaoDoanhSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long baoCaoID;

    @Min(value = 1, message = "Tháng không đuợc âm hoặc bằng 0")
    @Max(value = 12, message = "Giá trị của tháng không ược lớn hơn 12")
    private int thang;

    @Min(value = 1, message = "giá trị của năm không được bé hơn 1")
    private int nam;

    @DecimalMin(value = "0.00", inclusive = true, message = "Tổng số vốn huy động không được âm")
    private BigDecimal tongSoVonHuyDong;

    @DecimalMin(value = "0.00", inclusive = true, message = "Tổng vốn huy động ròng không được âm")
    private BigDecimal tongSoVonHuyDongRong;

    @OneToMany(mappedBy = "baoCaoDoanhSo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ChiTietBaoCaoDoanhSo> chiTietBaoCaoDoanhSos;
}
