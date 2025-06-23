package com.example.sweet.domain.response.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.response.NhanVienNoVaiTroResponseDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDichResponseDTO {
    private Long giaoDichID;

    private Long taiKhoanNguon;
    private LoaiTaiKhoan loaiTaiKhoanNguon;

    private Long taiKhoanDich;
    private LoaiTaiKhoan loaiTaiKhoanDich;

    private LoaiGiaoDich loaiGiaoDich;

    private KenhGiaoDich kenhGiaoDich;

    private NhanVienNoVaiTroResponseDTO nhanVienGiaoDich;

    private Long soTienGiaoDich;
    private String noiDung;
    private Instant thoiGianGiaoDich;
}
