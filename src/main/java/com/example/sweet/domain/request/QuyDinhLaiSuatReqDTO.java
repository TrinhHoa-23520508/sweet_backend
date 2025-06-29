package com.example.sweet.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class QuyDinhLaiSuatReqDTO {
    private Long quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;
    private Long nguoiLapQuyDinhID;
    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;
    private Boolean active;
    private List<ChiTietQuyDinhLaiSuatReqDTO> chiTietQuyDinhLaiSuats;
}
