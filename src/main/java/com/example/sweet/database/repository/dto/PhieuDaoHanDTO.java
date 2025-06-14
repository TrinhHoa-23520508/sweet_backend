package com.example.sweet.database.repository.dto;

import java.time.LocalDate;

import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhieuDaoHanDTO {
	Long maPhieuDaoHan; // khoa chinh

	// input
	String maPhieuGuiTien; // khoa ngoai, tham chieu toi phieu gui tien
	String maKhachHang; // khoa ngoai, tham chieu toi khach hang

	// Thong tin khach hang
	String cccdKhachHang;
	String hoTenKhachHang;

	// Thong tin phieu gui tien
	LocalDate ngayGuiTien;
	Long soTienGui;
	LoaiTietKiem loaiTietKiem; // cần tên loại
	TanSuatNhanLai tanSuatNhanLai;

	HinhThucDaoHan hinhThucDaoHan; // cần tên hình thức
	LoaiKyHan loaiKyHan; // cần tên loại kì hạn

	Float laiSuat; // chờ phương pháp lấy data

	Long soDuHienTai;
	Long tongLaiDuKien;
	Long tongLaiDinhKi;
	Long tongTaiChuaQuyetToan;
	Long tongLaiQuyetToan;

	LocalDate ngayDaoHan;

	// Thông tin mã phiếu trả lãi gần nhất

	// thông tin của chu kì kế tiếp
	LocalDate ngayGuiTienCKSau;
	Long soTienGuiKiSau;
	Long laiSuatKiSau;
	Long soDuHienTaiKiSau;
	Long tongLaiDuKienKiSau;
	Long tongLaiDinhKiKiSau;

	Long tongTaiChuaQuyetToanKiSau;
	Long tongLaiQuyetToanKiSau;
	LocalDate ngayDaoHanKiSau;

	// thông tin tài chính của phiếu đáo hạn
	Long soTienNhanVeSauDaoHan;
	Long soTienQuayVePhieuGuiTienMoi;
	Long soDuSauDaoHan;
	Long tienLaiQuyetToanSauDaoHan;

	Long tienLaiNhanDuocTruocQuyetToanSauDaoHan;
	Long tongTienLaiSauQuyetToan;

	public PhieuDaoHanDTO(PhieuDaoHan entity) {
		this.maPhieuDaoHan = entity.getPhieuDaoHanID();
	}
}
