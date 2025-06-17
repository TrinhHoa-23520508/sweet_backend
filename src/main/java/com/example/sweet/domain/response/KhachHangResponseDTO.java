package com.example.sweet.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangResponseDTO {
    private Long khachHangId;
    private String hoTen;
    private LocalDate ngaySinh;
    private String cccd;
    private String email;
    private String soDienThoai;
    private Long diaChiThuongTruId;
    private Long diaChiLienLacId;
    private LocalDate ngayDangKy;
    private Long trangThaiKhachHangId;
    private String tenDangNhap;
    private Long vaiTroId;
    private Long trangThaiTaiKhoanId;
}

