package com.example.sweet.services.GiaoDich;

import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.dto.PhieuGuiTienDTO;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.util.mapper.PhieuGuiTienMapper;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class PhieuGuiTienService {

    private final HinhThucDaoHanRepository hinhThucDaoHanRepository;

    private final LoaiKyHanRepository loaiKyHanRepository;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final PhieuGuiTienMapper phieuGuiTienMapper;

    public PhieuGuiTienService(
            PhieuGuiTienRepository phieuGuiTienRepository,
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            LoaiTietKiemRepository loaiTietKiemRepository,
            TanSuatNhanLaiRepository tanSuatNhanLaiRepository,
            LoaiKyHanRepository loaiKyHanRepository,
            HinhThucDaoHanRepository hinhThucDaoHanRepository,
            TrangThaiRepository trangThaiRepository,
            PhieuGuiTienMapper phieuGuiTienMapper) {
        this.phieuGuiTienRepository = phieuGuiTienRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.phieuGuiTienMapper = phieuGuiTienMapper;
        this.tanSuatNhanLaiRepository = tanSuatNhanLaiRepository;
        this.loaiKyHanRepository = loaiKyHanRepository;
        this.hinhThucDaoHanRepository = hinhThucDaoHanRepository;
        this.trangThaiRepository = trangThaiRepository;
    }

    @Transactional
    public PhieuGuiTienDTO createPhieuGuiTien(PhieuGuiTienDTO phieuGuiTienDTO) {
        try {
            // B3-B8: Validate dữ liệu đầu vào
            validatePhieuGuiTien(phieuGuiTienDTO);

            // B9: Tính ngày đáo hạn
            LocalDate ngayDaoHan = tinhNgayDaoHan(
                    phieuGuiTienDTO.getNgayGuiTien(),
                    phieuGuiTienDTO.getLoaiKyHanId());

            // B10: Tính toán các giá trị
            LoaiKyHan loaiKyHan = loaiKyHanRepository.findById(phieuGuiTienDTO.getLoaiKyHanId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy loại kỳ hạn"));

            TanSuatNhanLai tanSuat = tanSuatNhanLaiRepository.findById(phieuGuiTienDTO.getTanSuatNhanLaiId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tần suất nhận lãi"));

            Long soTienGui = phieuGuiTienDTO.getSoTienGuiBanDau();
            Float laiSuat = phieuGuiTienDTO.getLaiSuatCamKet();
            int kyHan = loaiKyHan.getSoThang();

            // Tính tổng tiền lãi dự kiến
            Long tongTienLaiDuKien = (long) Math.round(soTienGui * kyHan * (laiSuat / 12));

            // Tính số lần nhận lãi theo tần suất
            int soLanNhanLai = tinhSoLanNhanLai(tanSuat.getTenTanSoNhanLai(), kyHan);

            // Tính tiền lãi nhận định kỳ
            Long tienLaiNhanDinhKy = tongTienLaiDuKien / soLanNhanLai;

            // Tạo entity và set các giá trị
            PhieuGuiTien phieuGuiTien = phieuGuiTienMapper.toEntity(phieuGuiTienDTO);
            phieuGuiTien.setNgayDaoHan(ngayDaoHan);
            phieuGuiTien.setSoDuHienTai(soTienGui);
            phieuGuiTien.setTongTienLaiDuKien(tongTienLaiDuKien);
            phieuGuiTien.setTienLaiNhanDinhKy(tienLaiNhanDinhKy);
            phieuGuiTien.setTienLaiDaNhanNhungChuaDuyetToan(false);
            phieuGuiTien.setTongLaiQuyetToan(0L);

            // B11: Lưu xuống database
            return phieuGuiTienMapper.toDTO(phieuGuiTienRepository.save(phieuGuiTien));

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo phiếu gửi tiền: " + e.getMessage());
        }
    }

    private void validatePhieuGuiTien(PhieuGuiTienDTO dto) {
        // B4: Kiểm tra loại tiết kiệm
        if (!loaiTietKiemRepository.existsById(dto.getLoaiTietKiemId())) {
            throw new RuntimeException("Loại tiết kiệm không hợp lệ");
        }

        // B5: Kiểm tra số tiền gửi tối thiểu
        if (dto.getSoTienGuiBanDau() < 100000) { // Giả sử 100,000 là số tiền tối thiểu
            throw new RuntimeException("Số tiền gửi phải lớn hơn số tiền tối thiểu");
        }

        // B6: Kiểm tra tần suất trả lãi
        if (!tanSuatNhanLaiRepository.existsById(dto.getTanSuatNhanLaiId())) {
            throw new RuntimeException("Tần suất trả lãi không hợp lệ");
        }

        // B7: Kiểm tra hình thức đáo hạn
        if (!hinhThucDaoHanRepository.existsById(dto.getHinhThucDaoHanId())) {
            throw new RuntimeException("Hình thức đáo hạn không hợp lệ");
        }

        // B8: Kiểm tra loại kỳ hạn
        if (!loaiKyHanRepository.existsById(dto.getLoaiKyHanId())) {
            throw new RuntimeException("Loại kỳ hạn không hợp lệ");
        }
    }

    private LocalDate tinhNgayDaoHan(LocalDate ngayGui, Long loaiKyHanId) {
        LoaiKyHan loaiKyHan = loaiKyHanRepository.findById(loaiKyHanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại kỳ hạn"));
        return ngayGui.plusMonths(loaiKyHan.getSoThang());
    }

    private int tinhSoLanNhanLai(String tanSuat, int kyHan) {
        return switch (tanSuat.toLowerCase()) {
            case "đầu kỳ hạn", "cuối kỳ hạn" -> 1;
            case "hàng tháng" -> kyHan;
            case "hàng quý" -> kyHan / 3;
            default -> throw new RuntimeException("Tần suất nhận lãi không hợp lệ");
        };
    }

    public List<PhieuGuiTienDTO> getAllPhieuGuiTien() {
        Iterable<PhieuGuiTien> phieuGuiTiens = phieuGuiTienRepository.findAll();
        List<PhieuGuiTien> phieuGuiTienList = new ArrayList<>();
        phieuGuiTiens.forEach(phieuGuiTienList::add);

        return phieuGuiTienList.stream()
                .map(phieuGuiTienMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePhieuGuiTien(Long id) {
        phieuGuiTienRepository.deleteById(id);
    }

    @Transactional
    public PhieuGuiTienDTO updatePhieuGuiTien(Long id, PhieuGuiTienDTO phieuGuiTienDTO) {
        PhieuGuiTien existingPhieuGuiTien = phieuGuiTienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền"));

        // Update các trường reference
        if (phieuGuiTienDTO.getKhachHangId() != null) {
            existingPhieuGuiTien.setKhachHang(khachHangRepository.findById(phieuGuiTienDTO.getKhachHangId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng")));
        }
        if (phieuGuiTienDTO.getGiaoDichVienId() != null) {
            existingPhieuGuiTien.setGiaoDichVien(nhanVienRepository.findById(phieuGuiTienDTO.getGiaoDichVienId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch viên")));
        }
        if (phieuGuiTienDTO.getLoaiTietKiemId() != null) {
            existingPhieuGuiTien.setLoaiTietKiem(loaiTietKiemRepository.findById(phieuGuiTienDTO.getLoaiTietKiemId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tiết kiệm")));
        }

        // Update các trường cơ bản
        existingPhieuGuiTien.setNgayGuiTien(phieuGuiTienDTO.getNgayGuiTien());
        existingPhieuGuiTien.setSoTienGuiBanDau(phieuGuiTienDTO.getSoTienGuiBanDau());
        existingPhieuGuiTien.setLaiSuatCamKet(phieuGuiTienDTO.getLaiSuatCamKet());
        existingPhieuGuiTien.setTenGoiNho(phieuGuiTienDTO.getTenGoiNho());

        // Update các trường mới
        existingPhieuGuiTien.setSoDuHienTai(phieuGuiTienDTO.getSoDuHienTai());
        existingPhieuGuiTien.setTongTienLaiDuKien(phieuGuiTienDTO.getTongTienLaiDuKien());
        existingPhieuGuiTien.setTienLaiNhanDinhKy(phieuGuiTienDTO.getTienLaiNhanDinhKy());
        existingPhieuGuiTien.setTienLaiDaNhanNhungChuaDuyetToan(phieuGuiTienDTO.getTienLaiDaNhanNhungChuaDuyetToan());
        existingPhieuGuiTien.setTongLaiQuyetToan(phieuGuiTienDTO.getTongLaiQuyetToan());
        existingPhieuGuiTien.setNgayDaoHan(phieuGuiTienDTO.getNgayDaoHan());

        return phieuGuiTienMapper.toDTO(phieuGuiTienRepository.save(existingPhieuGuiTien));
    }
}
