package com.example.sweet.database.repository.dto;

import java.time.Instant;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuGuiTienDTO {
    private Long phieuGuiTienID;
    private KhachHang khachHang;
    private Long giaoDichVienId;
    private Long chiTietQuyDinhLaiSuatId;
    private Long hinhThucDaoHanId;
    private Instant ngayGuiTien;
    private Long soTienGuiBanDau;
    private String tenGoiNho;
    private Long trangThaiId;
    private Long soDuHienTai;
    private Long tongTienLaiDuKien;
    private Long tienLaiNhanDinhKy;
    private Long tienLaiDaNhanNhungChuaQuyetToan;
    private Long tongLaiQuyetToan;
    private Instant ngayDaoHan;
    private Integer soThang;
    private Long loaiTietKiemId;
    private Long tanSuatNhanLaiId;
    private Float laiSuat;
}
