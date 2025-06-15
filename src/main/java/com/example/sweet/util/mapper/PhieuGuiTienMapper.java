package com.example.sweet.util.mapper;

import org.springframework.stereotype.Component;

import com.example.sweet.database.repository.dto.PhieuGuiTienDTO;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;

@Component
public class PhieuGuiTienMapper {
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepository;
    private final LoaiKyHanRepository loaiKyHanRepository;
    private final HinhThucDaoHanRepository hinhThucDaoHanRepository;
    private final TrangThaiRepository trangThaiRepository;

    public PhieuGuiTienMapper(
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            LoaiTietKiemRepository loaiTietKiemRepository,
            TanSuatNhanLaiRepository tanSuatNhanLaiRepository,
            LoaiKyHanRepository loaiKyHanRepository,
            HinhThucDaoHanRepository hinhThucDaoHanRepository,
            TrangThaiRepository trangThaiRepository,
            GiaoDichRepository giaoDichRepository) {
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.tanSuatNhanLaiRepository = tanSuatNhanLaiRepository;
        this.loaiKyHanRepository = loaiKyHanRepository;
        this.hinhThucDaoHanRepository = hinhThucDaoHanRepository;
        this.trangThaiRepository = trangThaiRepository;
    }

    public PhieuGuiTienDTO toDTO(PhieuGuiTien phieuGuiTien) {
        if (phieuGuiTien == null) {
            return null;
        }

        PhieuGuiTienDTO dto = new PhieuGuiTienDTO();
        dto.setPhieuGuiTienID(phieuGuiTien.getPhieuGuiTienID());

        dto.setKhachHangId(phieuGuiTien.getKhachHang() != null ? phieuGuiTien.getKhachHang().getKhachHangID() : null);
        dto.setGiaoDichVienId(
                phieuGuiTien.getGiaoDichVien() != null ? phieuGuiTien.getGiaoDichVien().getNhanVienID() : null);
        dto.setLoaiTietKiemId(
                phieuGuiTien.getLoaiTietKiem() != null ? phieuGuiTien.getLoaiTietKiem().getLoaiTietKiemID() : null);
        dto.setTanSuatNhanLaiId(
                phieuGuiTien.getTanSuatNhanLai() != null ? phieuGuiTien.getTanSuatNhanLai().getTanSoNhanLaiID() : null);
        dto.setLoaiKyHanId(phieuGuiTien.getLoaiKyHan() != null ? phieuGuiTien.getLoaiKyHan().getLoaiKyHanID() : null);
        dto.setHinhThucDaoHanId(
                phieuGuiTien.getHinhThucDaoHan() != null ? phieuGuiTien.getHinhThucDaoHan().getHinhThucDaoHangID()
                        : null);
        dto.setNgayGuiTien(phieuGuiTien.getNgayGuiTien());
        dto.setSoTienGuiBanDau(phieuGuiTien.getSoTienGuiBanDau());
        dto.setLaiSuatCamKet(phieuGuiTien.getLaiSuatCamKet());
        dto.setTenGoiNho(phieuGuiTien.getTenGoiNho());
        dto.setTrangThaiId(phieuGuiTien.getTrangThai() != null ? phieuGuiTien.getTrangThai().getTrangThaiID() : null);

        // Add new fields
        dto.setSoDuHienTai(phieuGuiTien.getSoDuHienTai());
        dto.setTongTienLaiDuKien(phieuGuiTien.getTongTienLaiDuKien());
        dto.setTienLaiNhanDinhKy(phieuGuiTien.getTienLaiNhanDinhKy());
        dto.setTienLaiDaNhanNhungChuaQuyetToan(phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan());
        dto.setTongLaiQuyetToan(phieuGuiTien.getTongLaiQuyetToan());
        dto.setNgayDaoHan(phieuGuiTien.getNgayDaoHan());

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

        if (dto.getLoaiTietKiemId() != null) {
            LoaiTietKiem loaiTietKiem = loaiTietKiemRepository.findById(dto.getLoaiTietKiemId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tiết kiệm"));
            entity.setLoaiTietKiem(loaiTietKiem);
        }

        if (dto.getTanSuatNhanLaiId() != null) {
            TanSuatNhanLai tanSuatNhanLai = tanSuatNhanLaiRepository.findById(dto.getTanSuatNhanLaiId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tần suất nhận lãi"));
            entity.setTanSuatNhanLai(tanSuatNhanLai);
        }

        if (dto.getLoaiKyHanId() != null) {
            LoaiKyHan loaiKyHan = loaiKyHanRepository.findById(dto.getLoaiKyHanId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy loại kỳ hạn"));
            entity.setLoaiKyHan(loaiKyHan);
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
        entity.setLaiSuatCamKet(dto.getLaiSuatCamKet());
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
