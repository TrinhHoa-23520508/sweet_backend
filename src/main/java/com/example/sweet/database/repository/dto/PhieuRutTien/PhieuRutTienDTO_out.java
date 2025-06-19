package com.example.sweet.database.repository.dto.PhieuRutTien;

import java.time.Instant;

import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuRutTienDTO_out {
    Long maKH; // Mã khách hàng //
    String hoTenKhachHang; //
    String cccd; //

    Long maPhieuGuiTien; //
    Instant ngayGuiTien; //
    Long soTienGui; //

    LoaiTietKiem loaiTietKiem; // Loại tiết kiệm //
    TanSuatNhanLai tanSuatNhanLai; // Tần suất nhận lãi //

    HinhThucDaoHan hinhThucDaoHan; // Hình thức đáo hạn //
    LoaiKyHan loaiKyHan; // Loại kỳ hạn //

    float laiSuat; //

    Long soDuHienTai; // Số dư hiện tại
    Long tongLaiDuKien; // Tổng tiền lãi dự kiến
    Long tienLaiNhanDinhKy; // Tiền lãi nhận định kỳ
    Long tienLaiDaNhanNhungChuaQuyetToan;

    Long tongLaiQuyetToan; // Tổng tiền lãi đã nhận khi quyết toán
    Instant ngayDaoHan;

    Long maPhieuRutTien; // Mã phiếu rút tiền //auto generated
    Instant ngayRut; // Ngày rút tiền //
    int soTienRut; // Số tiền rút //
    LoaiGiaoDich loaiGiaoDich; // Loại giao dịch (Rút tiền)
    int laiSuatKhongKyHan; // Lãi suất không kỳ hạn

    Long soTienNhanDuocKhiRut; // Số tiền nhận được khi rút tiền
    Long tienLaiNhanDuocKhiRut; // Tiền lãi nhận được khi rút tiền
    Long soDuSauKhiRut; // Số dư sau khi rút tiền
    Long laiQuyetToanKhiRut; // Lãi quyết toán khi rút tiền
    Long tienLaiDaNhanNhungChuaQuyetToanSauRut; // Tiền lãi đã nhận nhưng chưa quyết toán sau khi rút
    Long tongLaiQuyetToanSauRut; // Tổng lãi quyết toán sau khi rút tiền

    Long maGiaoDichVien; // Mã giao dịch viên
    String tenGiaoDichVien; // Tên giao dịch viên
}
