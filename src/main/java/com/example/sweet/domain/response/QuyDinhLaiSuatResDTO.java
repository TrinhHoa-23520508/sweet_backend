package com.example.sweet.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class QuyDinhLaiSuatResDTO {
    private Long quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;
    private NhanVienNoVaiTroResponseDTO nguoiLapQuyDinh;
    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;
    private List<ChiTietQuyDinhLaiSuatResDTO> chiTietQuyDinhLaiSuats;
}
