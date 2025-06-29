package com.example.sweet.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuRutTienreqDTO {
    private Long phieuGuiTienID; // Mã phiếu gửi tiền
    private Long soTienRut; // Số tiền gốc muốn rút
    private float laiSuatKhongKyHan;
    private Long kenhGiaoDichID;
}
