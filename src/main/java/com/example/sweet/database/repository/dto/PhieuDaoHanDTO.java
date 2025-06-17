package com.example.sweet.database.repository.dto;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
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
	Long maPhieuDaoHan; // khoa chinh //

	// input
	Long maPhieuGuiTien; // khoa ngoai, tham chieu toi phieu gui tien //
	Long maKhachHang; // khoa ngoai, tham chieu toi khach //

	// Thong tin khach hang
	String cccdKhachHang; //
	String hoTenKhachHang; //

	// Thong tin phieu gui tien
	Instant ngayGuiTien; //
	Long soTienGui; //
	LoaiTietKiem loaiTietKiem; // cần tên loại //
	TanSuatNhanLai tanSuatNhanLai; //

	HinhThucDaoHan hinhThucDaoHan; // cần tên hình thức
	LoaiKyHan loaiKyHan; // cần tên loại kì hạn

	Float laiSuat; // chờ phương pháp lấy data //

	Long soDuHienTai; //
	Long tongLaiDuKien; //
	Long tongLaiDinhKi; //
	Long tongLaiChuaQuyetToan; //
	Long tongLaiQuyetToan; //

	Instant ngayDaoHan; //

	// Thông tin mã phiếu trả lãi gần nhất

	// thông tin của chu kì kế tiếp
	Instant ngayGuiTienCKSau; //
	Long soTienGuiKiSau; //
	Float laiSuatKiSau; //
	Long soDuHienTaiKiSau; //
	Long tongLaiDuKienKiSau; //
	Long tongLaiDinhKiKiSau; //

	Long tongLaiChuaQuyetToanKiSau; //
	Long tongLaiQuyetToanKiSau; //
	Instant ngayDaoHanKiSau; //

	// thông tin tài chính của phiếu đáo hạn
	Long soTienNhanVeSauDaoHan; //
	Long soTienQuayVePhieuGuiTienMoi;//
	Long soDuSauDaoHan; // ?
	Long tienLaiQuyetToanSauDaoHan; // ?

	Long tienLaiNhanDuocTruocQuyetToanSauDaoHan;
	Long tongTienLaiSauQuyetToan;

	public PhieuDaoHanDTO(PhieuDaoHan entity) {
		this.maPhieuDaoHan = entity.getPhieuDaoHanID();
		this.ngayDaoHan = entity.getNgayDaoHan();
	}

	public void tinhToan(PhieuGuiTien phieuGuiTienKiTruoc, PhieuGuiTien phieuGuiTienMoi) {
		this.ngayGuiTien = phieuGuiTienKiTruoc.getNgayGuiTien();
		this.soTienGui = phieuGuiTienKiTruoc.getSoTienGuiBanDau();
		this.loaiTietKiem = phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getLoaiTietKiem();
		this.tanSuatNhanLai = phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai();
		this.laiSuat = phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getLaiSuat();
		this.tongLaiDinhKi = phieuGuiTienKiTruoc.getTienLaiNhanDinhKy();
		tinhLaiSuatDaoHan(phieuGuiTienKiTruoc); // B5
		xuLiHinhThucDaoHan(phieuGuiTienKiTruoc, phieuGuiTienMoi); // B6
		capNhatThongTinLaiSuat(); // B7
		initSoTienGuiPhieuGuiTienMoi(phieuGuiTienKiTruoc, phieuGuiTienMoi); // B8
		this.ngayGuiTienCKSau = this.ngayDaoHan; // B9
		// B10
		this.ngayDaoHanKiSau = this.ngayGuiTienCKSau.plus(
				Duration.between(phieuGuiTienKiTruoc.getNgayDaoHan(), phieuGuiTienKiTruoc.getNgayGuiTien()));
		phieuGuiTienMoi.setNgayDaoHan(this.ngayDaoHanKiSau);

		// B11
		switch (phieuGuiTienKiTruoc.getHinhThucDaoHan().getTenHinhThucDaoHang()) {
			case "Tất toán phiếu gửi tiền":
				// do nothing
				break;
			default:
				phieuGuiTienMoi.setTongTienLaiDuKien(
						Long.valueOf(Math.round(phieuGuiTienKiTruoc.getSoDuHienTai() *
								phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getLoaiKyHan().getSoThang() *
								phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getLaiSuat() / 12)));
				this.tongLaiDuKienKiSau = phieuGuiTienMoi.getTongTienLaiDuKien();
				this.soDuHienTaiKiSau = phieuGuiTienMoi.getSoDuHienTai();
				this.laiSuatKiSau = phieuGuiTienMoi.getChiTietQuyDinhLaiSuat().getLaiSuat();
				this.tongLaiDinhKiKiSau = phieuGuiTienMoi.getTongTienLaiDuKien()
						/ (tinhSoLanNhanLai(
								phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai().getTenTanSoNhanLai(),
								phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat().getLoaiKyHan().getSoThang()));
				phieuGuiTienMoi.setTienLaiNhanDinhKy(this.tongLaiDinhKiKiSau);
				// tiền lãi đã nhận nhưng chưa quyết toán của kỳ hạn kế tiếp = 0
				phieuGuiTienMoi.setTienLaiDaNhanNhungChuaQuyetToan(0L);
				this.tongLaiChuaQuyetToanKiSau = phieuGuiTienMoi.getTienLaiDaNhanNhungChuaQuyetToan();
				// tổng lãi quyết toán của kỳ hạn kế tiếp = 0
				phieuGuiTienMoi.setTongLaiQuyetToan(0L);
				this.tongLaiQuyetToanKiSau = phieuGuiTienMoi.getTongLaiQuyetToan();
				break;
		}

	}

	/// Hiện thực B5
	public void tinhLaiSuatDaoHan(PhieuGuiTien phieuGuiTienKiTruoc) {
		// Lãi quyết toán khi đáo hạn = Tổng tiền lãi dự kiến
		this.tongLaiDuKien = phieuGuiTienKiTruoc.getTongTienLaiDuKien();
		this.tongLaiQuyetToan = this.tongLaiDuKien;
		// Số dư sau khi đáo hạn = 0
		phieuGuiTienKiTruoc.setSoDuHienTai(0L);
		this.soDuHienTai = 0L;
		// Tiền lãi đã nhận nhưng chưa quyết toán sau khi đáo hạn = 0
		phieuGuiTienKiTruoc.setTienLaiDaNhanNhungChuaQuyetToan(0L);
		this.tongLaiChuaQuyetToan = 0L;
		// Tổng lãi quyết toán sau khi đáo hạn = Tổng lãi quyết toán + Lãi quyết toán
		// khi đáo hạn
		this.tongLaiQuyetToan += phieuGuiTienKiTruoc.getTongLaiQuyetToan();
		phieuGuiTienKiTruoc.setTongLaiQuyetToan(this.tongLaiQuyetToan);

	}

	public PhieuGuiTien taoPhieuGuiTienKiSau(PhieuGuiTien phieuGuiTienKiTruoc) {
		// init data

		PhieuGuiTien phieuGuiTienMoi = new PhieuGuiTien(null,
				phieuGuiTienKiTruoc.getKhachHang(),
				phieuGuiTienKiTruoc.getGiaoDichVien(),
				phieuGuiTienKiTruoc.getChiTietQuyDinhLaiSuat(),
				phieuGuiTienKiTruoc.getHinhThucDaoHan(),
				Instant.now(), // ngayGuiTien
				0L, // soTienGuiBanDau
				phieuGuiTienKiTruoc.getTenGoiNho(),
				phieuGuiTienKiTruoc.getTrangThai(),
				0L, // soDuHienTai
				0L, // tongTienLaiDuKien
				0L, // tienLaiNhanDinhKy
				0L, // tienLaiDaNhanNhungChuaQuyetToan
				0L, // tongLaiQuyetToan
				phieuGuiTienKiTruoc.getNgayDaoHan() // ngayDaoHan
		);

		return phieuGuiTienMoi;
	}

	// Hiện thực B6
	public void xuLiHinhThucDaoHan(PhieuGuiTien phieuGuiTienKiTruoc, PhieuGuiTien phieuGuiTienMoi) {

		switch (phieuGuiTienKiTruoc.getHinhThucDaoHan().getTenHinhThucDaoHang()) {
			case "Tất toán phiếu gửi tiền":
				// Số tiền nhận được về tài khoản thanh toán khi đáo hạn = Số dư hiện tại +
				// Số tiền nhận được khi trả lãi
				this.soTienNhanVeSauDaoHan = phieuGuiTienKiTruoc.getSoDuHienTai()
						+ phieuGuiTienKiTruoc.getTienLaiDaNhanNhungChuaQuyetToan();
				this.soTienQuayVePhieuGuiTienMoi = 0L;
				this.soDuSauDaoHan = this.soTienNhanVeSauDaoHan;
				this.tienLaiQuyetToanSauDaoHan = phieuGuiTienKiTruoc.getTongLaiQuyetToan();
				phieuGuiTienMoi.setSoDuHienTai(0L);
				phieuGuiTienMoi.setTongTienLaiDuKien(0L);
				break;
			case "Tái tục gốc":
				// Số tiền nhận được về tài khoản thanh toán khi đáo hạn = Số tiền nhận
				// được khi trả lãi
				this.soTienNhanVeSauDaoHan = phieuGuiTienKiTruoc.getTienLaiDaNhanNhungChuaQuyetToan();
				// Số tiền nhận được về phiếu gửi tiền mới khi đáo hạn = Số dư hiện tại
				this.soTienQuayVePhieuGuiTienMoi = phieuGuiTienKiTruoc.getSoDuHienTai();
				this.soDuSauDaoHan = this.soTienQuayVePhieuGuiTienMoi;
				this.tienLaiQuyetToanSauDaoHan = phieuGuiTienKiTruoc.getTongLaiQuyetToan();
				phieuGuiTienMoi.setSoDuHienTai(phieuGuiTienKiTruoc.getSoDuHienTai());
				break;
			case "Tái tục gốc và lãi":
				// Số tiền nhận được về tài khoản thanh toán khi đáo hạn = 0
				this.soTienNhanVeSauDaoHan = 0L;
				// Số tiền nhận được về phiếu gửi tiền mới khi đáo hạn = Số dư hiện tại +
				// Số tiền nhận được khi trả lãi
				this.soTienQuayVePhieuGuiTienMoi = phieuGuiTienKiTruoc.getSoDuHienTai()
						+ phieuGuiTienKiTruoc.getTienLaiDaNhanNhungChuaQuyetToan();
				this.soDuSauDaoHan = this.soTienQuayVePhieuGuiTienMoi;
				this.tienLaiQuyetToanSauDaoHan = phieuGuiTienKiTruoc.getTongLaiQuyetToan();
				phieuGuiTienMoi.setSoDuHienTai(
						phieuGuiTienKiTruoc.getSoDuHienTai()
								+ phieuGuiTienKiTruoc.getTienLaiDaNhanNhungChuaQuyetToan());
				phieuGuiTienMoi.setSoTienGuiBanDau(phieuGuiTienMoi.getSoDuHienTai());
				// phieuGuiTienMoi.setTongTienLaiDuKien(
				// Long.valueOf(Math.round(phieuGuiTienKiTruoc.getSoDuHienTai() *
				// phieuGuiTienKiTruoc.getLoaiKyHan().getSoThang() *
				// phieuGuiTienKiTruoc.getLaiSuatCamKet() / 12))
				// );
				break;
			default:
				break;
		}
		this.tienLaiNhanDuocTruocQuyetToanSauDaoHan = phieuGuiTienKiTruoc.getTienLaiDaNhanNhungChuaQuyetToan();
		this.tongTienLaiSauQuyetToan = this.tienLaiQuyetToanSauDaoHan + this.tienLaiNhanDuocTruocQuyetToanSauDaoHan;
	}

	/// Hiện thực B7
	public void capNhatThongTinLaiSuat() {
		this.tongLaiDuKienKiSau = 0L;
		this.soDuHienTaiKiSau = this.soDuSauDaoHan;
		this.tongLaiChuaQuyetToanKiSau = this.tienLaiNhanDuocTruocQuyetToanSauDaoHan;
		this.tongLaiQuyetToanKiSau = this.tienLaiQuyetToanSauDaoHan;
	}

	// B8
	public void initSoTienGuiPhieuGuiTienMoi(PhieuGuiTien phieuGuiTienKiTruoc, PhieuGuiTien phieuGuiTienMoi) {
		switch (phieuGuiTienKiTruoc.getHinhThucDaoHan().getTenHinhThucDaoHang()) {
			case "Tất toán phiếu gửi tiền":
				// Không cần làm gì
				return;
			case "Tái tục gốc":
				// Số tiền gửi ban đầu của kỳ hạn kế tiếp = Số tiền gửi ban đầu
				phieuGuiTienMoi.setSoTienGuiBanDau(phieuGuiTienKiTruoc.getSoTienGuiBanDau());
				this.soTienGuiKiSau = phieuGuiTienMoi.getSoTienGuiBanDau();
				break;
			case "Tái tục gốc và lãi":
				// Số tiền gửi ban đầu của kỳ hạn kế tiếp = Số tiền gửi ban đầu + Tiền lãi
				// nhận được khi đáo hạn
				phieuGuiTienMoi.setSoTienGuiBanDau(
						phieuGuiTienKiTruoc.getSoTienGuiBanDau()
								+ phieuGuiTienKiTruoc.getTienLaiDaNhanNhungChuaQuyetToan());
				this.soTienGuiKiSau = phieuGuiTienMoi.getSoTienGuiBanDau();
				break;
			default:
				break;
		}

	}

	private int tinhSoLanNhanLai(String tanSuat, int kyHan) { // copy từ phiếu gửi tiền serivce
		return switch (tanSuat.toLowerCase()) {
			case "đầu kỳ hạn", "cuối kỳ hạn" -> 1;
			case "hàng tháng" -> kyHan;
			case "hàng quý" -> kyHan / 3;
			default -> throw new RuntimeException("Tần suất nhận lãi không hợp lệ");
		};
	}

}

/*
 * /// 3.7.3. Thuật toán:
 * B1: Nhận D1 từ người dùng
 * B2: Kết nối cơ sở dữ liệu
 * B3: Đọc D3 từ bộ nhớ phụ
 * B4: Kiểm tra Thông tin phiếu đáo hạn (Mã phiếu gửi tiền) (D1) = Thông tin
 * phiếu đáo hạn (Mã phiếu gửi tiền) (D3) hay không? Nếu có thì đến B13
 * B5: Thực hiện các tính toán sau:
 * - Lãi quyết toán khi đáo hạn = Tổng tiền lãi dự kiến
 * - Số dư sau khi đáo hạn = 0
 * - Tiền lãi đã nhận nhưng chưa quyết toán sau khi đáo hạn = 0
 * - Tổng lãi quyết toán sau khi đáo hạn = Tổng lãi quyết toán + Lãi quyết toán
 * khi đáo hạn
 * 
 * B6: Kiểm tra Hình thức đáo hạn thuộc loại nào?
 * - Nếu Hình thức đáo hạn = “Nhận cả gốc và lãi” thì:
 * + Số tiền nhận được về tài khoản thanh toán khi đáo hạn = Số dư hiện tại + Số
 * tiền nhận được khi trả lãi
 * + Số tiền nhân được về phiếu gửi tiền mới khi đáo hạn = 0
 * 
 * - Nếu Hình thức đáo hạn = “Nhận lãi, chuyển gốc sang kỳ hạn mới” thì:
 * + Số tiền nhận được về tài khoản thanh toán khi đáo hạn = Số tiền nhận được
 * khi trả lãi
 * + Số tiền nhận được về phiếu gửi tiền mới khi đáo hạn = Số dư hiện tại
 * 
 * - Nếu Hình thức đáo hạn = “Chuyển gốc và lãi sang kỳ hạn mới” thì:
 * + Số tiền nhận được về tài khoản thanh toán khi đáo hạn = 0
 * + Số tiền nhận được về phiếu gửi tiền mới khi đáo hạn = Số dư hiện tại + Số
 * tiền nhận được khi trả lãi
 * 
 * B7: Thực hiện tính toán để chuẩn bị cập nhật:
 * - Tổng tiền lãi dự kiến (mới) = 0
 * - Số dư hiện tại (mới) = Số dư sau khi đáo hạn
 * - Tiền lãi đã nhận nhưng chưa quyết toán (mới) = Tiền lãi đã nhận nhưng chưa
 * quyết toán sau khi đáo hạn
 * - Tổng lãi quyết toán (mới) = Tổng lãi quyết toán sau khi đáo hạn
 * B8: Kiểm tra Hình thức đáo hạn thuộc loại nào?
 * - Nếu Hình thức đáo hạn = “Nhận cả gốc và lãi” thì đến B13
 * - Nếu Hình thức đáo hạn = “Nhận lãi, chuyển gốc sang kỳ hạn mới” thì: Số tiền
 * gửi ban đầu của kỳ hạn kế tiếp = Số tiền gửi ban đầu
 * - Nếu Hình thức đáo hạn = “Chuyển lãi và gốc sang kỳ hạn mới” thì: Số tiền
 * gửi ban đầu của kỳ hạn kế tiếp = Số tiền gửi ban đầu + Tiền lãi nhận được khi
 * đáo hạn
 * B9: Thực hiện tính toán: Ngày gửi tiền của kỳ hạn kế tiếp = Ngày đáo hạn
 * B10: Tính Ngày đáo hạn của kỳ hạn kế tiếp (dựa trên Ngày gửi tiền của kỳ hạn
 * kế tiếp và Loại kỳ hạn)
 * B11: Thực hiện tính toán:
 * - Tổng tiền lãi dự kiến của kỳ hạn kế tiếp = Số tiền gửi ban đầu của kỳ hạn
 * kế tiếp * Loại kỳ hạn * (Lãi suất cam kết của kỳ hạn kế tiếp / 12)
 * - Số dư hiện tại của kỳ hạn kế tiếp = Số tiền gửi ban đầu của kỳ hạn kế tiếp
 * - Tiền lãi nhận định kỳ của kỳ hạn kế tiếp = Tổng tiền lãi dự kiến của kỳ hạn
 * kế tiếp / Số lần nhận lãi
 * ( + Số lần nhận lãi = 1 Nếu Tần suất trả lãi = “Đầu kỳ hạn” hoặc “Cuối kỳ
 * hạn”
 * + Số lần nhận lãi = Loại kỳ hạn / tháng Nếu Tần suất trả lãi = “Hàng tháng”
 * + Số lần nhận lãi = Loại kỳ hạn / 3 tháng Nếu Tần suất nhận lãi = “Hàng quý”
 * )
 * - Tiền lãi đã nhận nhưng chưa quyết toán của kỳ hạn kế tiếp = 0;
 * - Tổng lãi quyết toán của kỳ hạn kế tiếp = 0
 */

// B12: Lưu D4 xuống bộ nhớ phụ
// B13: Đóng kết nối cơ sở dữ liệu
// B14: Kết thúc
