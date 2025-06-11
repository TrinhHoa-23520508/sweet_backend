package com.example.sweet.database.schema.dto;

import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuGuiTienDTO {
    private Integer phieuGuiTienID;
    private Long khachHangId;
    private Long giaoDichId;
    private Long giaoDichVienId;
    private Long loaiTietKiemId;
    private Long tanSuatNhanLaiId;
    private Long loaiKyHanId;
    private Long hinhThucDaoHanId;
    private LocalDate ngayGuiTien;
    private Integer soTienGuiBanDau;
    private Float laiSuatCamKet;
    private String tenGoiNho;
    private Long trangThaiId;
}
