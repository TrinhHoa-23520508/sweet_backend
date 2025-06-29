package com.example.sweet.util.mapper;

import org.springframework.stereotype.Component;

import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.response.GiaoDich.PhieuGuiTienDTO;
import com.example.sweet.services.QuyDinhLaiSuatService;

import lombok.AllArgsConstructor;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;

@AllArgsConstructor
@Component
public class PhieuGuiTienMapper {
        private final KhachHangRepository khachHangRepository;
        private final NhanVienRepository nhanVienRepository;
        private final HinhThucDaoHanRepository hinhThucDaoHanRepository;
        private final TrangThaiRepository trangThaiRepository;
        private final ChiTietQuyDinhLaiSuatRepository chiTietQuyDinhLaiSuatRepository;
        private final LichSuGiaoDich_PhieuGuiTienRepository lichSuGiaoDichPhieuGuiTienRepository;
        private final QuyDinhLaiSuatService quyDinhLaiSuatService;

        public PhieuGuiTienDTO toDTO(PhieuGuiTien phieuGuiTien) {
                if (phieuGuiTien == null) {
                        return null;
                }

                PhieuGuiTienDTO dto = new PhieuGuiTienDTO();
                dto.setPhieuGuiTienID(phieuGuiTien.getPhieuGuiTienID());

                dto.setKhachHang(phieuGuiTien.getKhachHang() != null ? phieuGuiTien.getKhachHang()
                                : null);
                dto.setGiaoDichVienId(
                                phieuGuiTien.getGiaoDichVien() != null ? phieuGuiTien.getGiaoDichVien().getNhanVienID()
                                                : null);
                dto.setSoThang(phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiKyHan() != null
                                ? phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiKyHan().getSoThang()
                                : null);
                dto.setLoaiTietKiemId(phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiTietKiem() != null
                                ? phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiTietKiem().getLoaiTietKiemID()
                                : null);
                dto.setTanSuatNhanLaiId(phieuGuiTien.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai() != null
                                ? phieuGuiTien.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai().getTanSoNhanLaiID()
                                : null);
                dto.setChiTietQuyDinhLaiSuatId(phieuGuiTien.getChiTietQuyDinhLaiSuat() != null
                                ? phieuGuiTien.getChiTietQuyDinhLaiSuat().getChiTietQuyDinhID()
                                : null);
                dto.setHinhThucDaoHanId(
                                phieuGuiTien.getHinhThucDaoHan() != null
                                                ? phieuGuiTien.getHinhThucDaoHan().getHinhThucDaoHangID()
                                                : null);
                dto.setNgayGuiTien(phieuGuiTien.getNgayGuiTien());
                dto.setSoTienGuiBanDau(phieuGuiTien.getSoTienGuiBanDau());
                dto.setTenGoiNho(phieuGuiTien.getTenGoiNho());
                dto.setTrangThaiId(phieuGuiTien.getTrangThai() != null ? phieuGuiTien.getTrangThai().getTrangThaiID()
                                : null);

                // Add new fields
                dto.setSoDuHienTai(phieuGuiTien.getSoDuHienTai());
                dto.setTongTienLaiDuKien(phieuGuiTien.getTongTienLaiDuKien());
                dto.setTienLaiNhanDinhKy(phieuGuiTien.getTienLaiNhanDinhKy());
                dto.setTienLaiDaNhanNhungChuaQuyetToan(phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan());
                dto.setTongLaiQuyetToan(phieuGuiTien.getTongLaiQuyetToan());
                dto.setNgayDaoHan(phieuGuiTien.getNgayDaoHan());

                // Add laiSuat from ChiTietQuyDinhLaiSuat
                if (phieuGuiTien.getChiTietQuyDinhLaiSuat() != null) {
                        dto.setLaiSuat(phieuGuiTien.getChiTietQuyDinhLaiSuat().getLaiSuat());
                }
                LichSuGiaoDich_PhieuGuiTien lichSu = lichSuGiaoDichPhieuGuiTienRepository
                                .findFirstByPhieuGuiTien(phieuGuiTien)
                                .orElse(null);

                if (lichSu != null && lichSu.getGiaoDich() != null && lichSu.getGiaoDich().getKenhGiaoDich() != null) {
                        dto.setKenhGiaoDich(lichSu.getGiaoDich().getKenhGiaoDich());
                        // Nếu dùng DTO cho KenhGiaoDich thì chuyển đổi sang DTO ở đây
                }

                return dto;
        }

        public PhieuGuiTien toEntity(PhieuGuiTienDTO dto) {
                if (dto == null) {
                        return null;
                }

                PhieuGuiTien entity = new PhieuGuiTien();

                // Set ID nếu có
                if (dto.getPhieuGuiTienID() != null) {
                        entity.setPhieuGuiTienID(dto.getPhieuGuiTienID());
                }

                // Set các đối tượng tham chiếu
                if (dto.getKhachHangId() != null) {
                        KhachHang khachHang = khachHangRepository.findById(dto.getKhachHangId())
                                        .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
                        entity.setKhachHang(khachHang);
                }

                if (dto.getGiaoDichVienId() != null) {
                        NhanVien giaoDichVien = nhanVienRepository.findById(dto.getGiaoDichVienId())
                                        .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên giao dịch"));
                        entity.setGiaoDichVien(giaoDichVien);
                }

                if (dto.getSoThang() != null && dto.getLoaiTietKiemId() != null
                                && dto.getTanSuatNhanLaiId() != null) {
                        var currentQuyDinhLaiSuat = quyDinhLaiSuatService.findCurrentQuyDinhLaiSuat()
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Không tìm thấy quy định lãi suất hiện tại"));
                        ChiTietQuyDinhLaiSuat chiTietQuyDinh = chiTietQuyDinhLaiSuatRepository
                                        .findByQuyDinhLaiSuat_QuyDinhLaiSuatIDAndLoaiKyHan_LoaiKyHanIDAndLoaiTietKiem_LoaiTietKiemIDAndTanSuatNhanLai_TanSoNhanLaiID(
                                                        currentQuyDinhLaiSuat.getQuyDinhLaiSuatID(),
                                                        dto.getSoThang(),
                                                        dto.getLoaiTietKiemId(),
                                                        dto.getTanSuatNhanLaiId())
                                        .orElseThrow(() -> new RuntimeException("Quy định lãi suất không hợp lệ"));
                        entity.setChiTietQuyDinhLaiSuat(chiTietQuyDinh);
                        // Set laiSuat từ ChiTietQuyDinhLaiSuat
                        dto.setLaiSuat(chiTietQuyDinh.getLaiSuat());
                }

                if (dto.getHinhThucDaoHanId() != null) {
                        HinhThucDaoHan hinhThucDaoHan = hinhThucDaoHanRepository.findById(dto.getHinhThucDaoHanId())
                                        .orElseThrow(() -> new RuntimeException("Không tìm thấy hình thức đáo hạn"));
                        entity.setHinhThucDaoHan(hinhThucDaoHan);
                }

                if (dto.getTrangThaiId() != null) {
                        TrangThai trangThai = trangThaiRepository.findById(dto.getTrangThaiId())
                                        .orElseThrow(() -> new RuntimeException("Không tìm thấy trạng thái"));
                        entity.setTrangThai(trangThai);
                }

                // Set các trường dữ liệu cơ bản
                entity.setNgayGuiTien(dto.getNgayGuiTien());
                entity.setSoTienGuiBanDau(dto.getSoTienGuiBanDau());
                entity.setTenGoiNho(dto.getTenGoiNho());

                // Add new fields
                entity.setSoDuHienTai(dto.getSoDuHienTai());
                entity.setTongTienLaiDuKien(dto.getTongTienLaiDuKien());
                entity.setTienLaiNhanDinhKy(dto.getTienLaiNhanDinhKy());
                entity.setTienLaiDaNhanNhungChuaQuyetToan(dto.getTienLaiDaNhanNhungChuaQuyetToan());
                entity.setTongLaiQuyetToan(dto.getTongLaiQuyetToan());
                entity.setNgayDaoHan(dto.getNgayDaoHan());

                return entity;
        }

}
