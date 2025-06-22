package com.example.sweet.database.repository.dto.PhieuRutTien;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PhieuRutTienDTO_inp {
    private Long maPhieuGuiTien; // Mã phiếu gửi tiền
    private Long maKhachHang; // Mã khách hàng
    // private LocalDateTime ngayRut; // Ngày rút (dạng chuỗi, có thể chuyển đổi
    // sang LocalDateTime)
    private int soTienRut; // Số tiền gốc muốn rút
    private Long maGiaoDichVien; // Mã giao dịch viên
    private Long kenhGiaoDichID; // Mã kênh giao dịch: 1 là trực tiếp, 2 là online
}

///
/// D1: Mã phiếu gửi tiền, Mã khách hàng, Ngày rút, Số tiền gốc muốn rút, Mã
/// giao dịch viên