package com.example.sweet.database.repository.dto;

import java.time.Instant;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuTraLaiDTO {
    private Long phieuTraLaiID;
    private Long phieuGuiTienID;
    private Long giaoDichID;
    private Instant ngayTraLai;

    // Thêm các trường mới
    private String maKhachHang;
    private String hoTen;
    private String cccd;
    private Long maPhieuGuiTien;
    private Instant ngayGuiTien;
    private String loaiTietKiem;
    private String tanSuatTraLai;
    private Long tienLaiNhanDinhKy;
    private Long tienLaiDaNhanNhungChuaQuyetToan;
    private Instant ngayDaoHan;
    private Long soTienNhanDuocKhiTraLai;
    private Long tienLaiDaNhanNhungChuaQuyetToanSauKhiTraLai;
}
