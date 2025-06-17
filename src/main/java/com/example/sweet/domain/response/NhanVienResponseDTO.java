package com.example.sweet.domain.response;

import com.example.sweet.domain.request.VaiTroDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienResponseDTO {
    private Long nhanVienId;
    private String hoTen;
    private LocalDate ngaySinh;
    private String cccd;
    private String email;
    private String soDienThoai;
    private Long diaChiThuongTruId;
    private Long diaChiLienLacId;
    private LocalDate ngayTuyenDung;
    private Long trangThaiNhanVienId;
    private String tenDangNhap;
    private VaiTroDTO vaiTro;
    private Long trangThaiTaiKhoanId;
}

