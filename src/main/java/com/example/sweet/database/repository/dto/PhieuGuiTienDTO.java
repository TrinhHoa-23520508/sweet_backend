package com.example.sweet.database.repository.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuGuiTienDTO {
    private Long phieuGuiTienID;
    private Long khachHangId;
    private Long giaoDichId;
    private Long giaoDichVienId;
    private Long loaiTietKiemId;
    private Long tanSuatNhanLaiId;
    private Long loaiKyHanId;
    private Long hinhThucDaoHanId;
    private LocalDate ngayGuiTien;
    private Long soTienGuiBanDau;
    private Float laiSuatCamKet;
    private String tenGoiNho;
    private Long trangThaiId;
    private Long soDuHienTai;
    private Long tongTienLaiDuKien;
    private Long tienLaiNhanDinhKy;
    private Boolean tienLaiDaNhanNhungChuaDuyetToan;
    private Long tongLaiQuyetToan;
    private LocalDate ngayDaoHan;
}
