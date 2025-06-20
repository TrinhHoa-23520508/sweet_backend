package com.example.sweet.domain.response;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.domain.request.TrangThaiDTO;
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
public class KhachHangResponseDTO {
    private Long khachHangID;
    private String hoTen;
    private LocalDate ngaySinh;
    private String cccd;
    private String email;
    private int tuoi;
    private String soDienThoai;
    private DiaChi diaChiThuongTru;
    private DiaChi diaChiLienLac;
    private LocalDate ngayDangKy;
    private TrangThaiDTO trangThaiKhachHang;
    private VaiTroDTO vaiTro;
    private TrangThaiDTO trangThaiTaiKhoan;
}

