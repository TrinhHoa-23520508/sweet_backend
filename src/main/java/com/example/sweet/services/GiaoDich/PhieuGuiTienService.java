package com.example.sweet.services.GiaoDich;

import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.*;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.repository.dto.PhieuGuiTienDTO;
import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.util.mapper.PhieuGuiTienMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Instant;

@AllArgsConstructor
@Service
public class PhieuGuiTienService {
    private final LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    private final LoaiGiaoDichRepository loaiGiaoDichRepository;
    private final GiaoDichService giaoDichService;
    private final HinhThucDaoHanRepository hinhThucDaoHanRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PhieuGuiTienMapper phieuGuiTienMapper;
    private final TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
    private final KenhGiaoDichRepository kenhGiaoDichRepository;
    private final LichSuGiaoDich_PhieuGuiTienRepository lichSuPGTRepo;
    private final ChiTietQuyDinhLaiSuatRepository chiTietQuyDinhLaiSuatRepository;
    private final PhieuTraLaiService phieuTraLaiService;

    @Transactional
    public PhieuGuiTienDTO createPhieuGuiTien(PhieuGuiTienDTO dto) {
        try {
            // B3-B8: Validate dữ liệu đầu vào
            validatePhieuGuiTien(dto);

            // Get ChiTietQuyDinhLaiSuat
            ChiTietQuyDinhLaiSuat chiTietQuyDinh = chiTietQuyDinhLaiSuatRepository
                    .findByLoaiKyHan_LoaiKyHanIDAndLoaiTietKiem_LoaiTietKiemIDAndTanSuatNhanLai_TanSoNhanLaiID(
                            dto.getSoThang(),
                            dto.getLoaiTietKiemId(),
                            dto.getTanSuatNhanLaiId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy quy định lãi suất phù hợp"));

            // Set trạng thái mặc định là "chưa tất toán" (ID = 10L)
            dto.setTrangThaiId(10L);

            // Set ngày gửi tiền to now
            dto.setNgayGuiTien(Instant.now());

            // B9: Tính ngày đáo hạn
            Instant ngayDaoHan = dto.getNgayGuiTien()
                    .plusMillis(chiTietQuyDinh.getLoaiKyHan().getSoThang() * 30L * 24L * 60L * 60L * 1000L);

            // B10: Tính toán các giá trị
            Long soTienGui = dto.getSoTienGuiBanDau();
            Float laiSuat = chiTietQuyDinh.getLaiSuat();
            int kyHan = chiTietQuyDinh.getLoaiKyHan().getSoThang();

            // Tính tổng tiền lãi dự kiến
            Long tongTienLaiDuKien = (long) Math.round(soTienGui * kyHan * (laiSuat / 12));

            // Tính số lần nhận lãi theo tần suất
            int soLanNhanLai = tinhSoLanNhanLai(
                    chiTietQuyDinh.getTanSuatNhanLai().getTenTanSoNhanLai(),
                    kyHan);

            // Tính tiền lãi nhận định kỳ
            Long tienLaiNhanDinhKy = tongTienLaiDuKien / soLanNhanLai;

            // Tạo object GiaoDich trước
            GiaoDich giaoDich = new GiaoDich();
            giaoDich.setSoTienGiaoDich(dto.getSoTienGuiBanDau());
            giaoDich.setThoiGianGiaoDich(dto.getNgayGuiTien());
            giaoDich.setNoiDung("Tạo sổ tiết kiệm: " + dto.getTenGoiNho());

            // Set khách hàng và giao dịch viên
            KhachHang khachHang = khachHangRepository.findById(dto.getKhachHangId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

            NhanVien giaoDichVien = nhanVienRepository.findById(dto.getGiaoDichVienId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch viên"));
            giaoDich.setNhanVienGiaoDich(giaoDichVien);

            // PhieuGuiTien chưa được tạo nên chưa có ID, sẽ set sau khi lưu
            PhieuGuiTien phieuGuiTien = phieuGuiTienMapper.toEntity(dto);
            phieuGuiTien.setSoDuHienTai(dto.getSoTienGuiBanDau()); // Set số dư = số tiền gửi ban đầu
            phieuGuiTien = phieuGuiTienRepository.save(phieuGuiTien); // Lưu trước để có ID

            // Set tài khoản nguồn và đích
            TaiKhoanThanhToan taiKhoanNguon = taiKhoanThanhToanRepository
                    .findByKhachHangKhachHangID(khachHang.getKhachHangID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán"));
            taiKhoanNguon = taiKhoanThanhToanRepository.save(taiKhoanNguon);
            giaoDich.setTaiKhoanNguon(taiKhoanNguon.getSoTaiKhoan());
            giaoDich.setLoaiTaiKhoanNguon(
                    loaiTaiKhoanRepository.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tài khoản"))); // 1L cho tài
                                                                                                        // khoản thanh
                                                                                                        // toán

            giaoDich.setTaiKhoanDich(phieuGuiTien.getPhieuGuiTienID()); // phieuGuiTien sẽ là tài khoản đích
            giaoDich.setLoaiTaiKhoanDich(
                    loaiTaiKhoanRepository.findById(2L)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tài khoản"))); // 2L cho tài
                                                                                                        // khoản tiết
                                                                                                        // kiệm

            // Set loại và kênh giao dịch
            giaoDich.setLoaiGiaoDich(loaiGiaoDichRepository.findById(3L).get());
            giaoDich.setKenhGiaoDich(kenhGiaoDichRepository.findById(1L).get());

            // Gọi service để tạo giao dịch
            GiaoDich savedGiaoDich = giaoDichService.createGiaoDich(giaoDich);

            // Sau khi có savedGiaoDich, lưu lịch sử giao dịch
            LichSuGiaoDich_PhieuGuiTien lichSuPGT = new LichSuGiaoDich_PhieuGuiTien();
            lichSuPGT.setPhieuGuiTien(phieuGuiTien);
            lichSuPGT.setGiaoDich(savedGiaoDich);
            lichSuPGT.setSoTienGocGiaoDich(phieuGuiTien.getSoDuHienTai());
            lichSuPGT.setSoDuHienTai_SauGD(phieuGuiTien.getSoDuHienTai());
            lichSuPGT.setTienLai_TrongGD(0.0);
            lichSuPGT.setLaiQuyetToan_TrongGD(0.0);
            lichSuPGT.setTienLaiDaNhanNhungChuaQuyetToan_SauGD(0.0);
            lichSuPGT.setTongLaiQuyetToan_SauGD(0.0);

            lichSuPGTRepo.save(lichSuPGT);

            // Tiếp tục code tạo PhieuGuiTien như cũ
            phieuGuiTien.setNgayDaoHan(ngayDaoHan);
            phieuGuiTien.setTongTienLaiDuKien(tongTienLaiDuKien);
            phieuGuiTien.setTienLaiNhanDinhKy(tienLaiNhanDinhKy);
            phieuGuiTien.setTienLaiDaNhanNhungChuaQuyetToan(0L);
            phieuGuiTien.setTongLaiQuyetToan(0L);

            // Lưu phiếu gửi tiền
            PhieuGuiTien savedPhieuGuiTien = phieuGuiTienRepository.save(phieuGuiTien);

            // Nếu là đầu kỳ hạn -> tạo phiếu trả lãi ngay
            String tanSuat = chiTietQuyDinh.getTanSuatNhanLai().getTenTanSoNhanLai();
            if ("đầu kỳ hạn".equalsIgnoreCase(tanSuat)) {
                PhieuTraLaiDTO phieuTraLaiDTO = new PhieuTraLaiDTO();
                phieuTraLaiDTO.setPhieuGuiTienID(savedPhieuGuiTien.getPhieuGuiTienID());
                phieuTraLaiDTO.setNgayTraLai(dto.getNgayGuiTien()); // trả lãi ngay tại thời điểm gửi
                phieuTraLaiService.createPhieuTraLai(phieuTraLaiDTO);
            }

            return phieuGuiTienMapper.toDTO(savedPhieuGuiTien);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo phiếu gửi tiền: " + e.getMessage());
        }
    }

    private void validatePhieuGuiTien(PhieuGuiTienDTO dto) {
        // Validate required IDs exist
        if (dto.getSoThang() == null || dto.getLoaiTietKiemId() == null || dto.getTanSuatNhanLaiId() == null) {
            throw new RuntimeException("Thiếu thông tin về loại kỳ hạn, loại tiết kiệm hoặc tần suất nhận lãi");
        }

        // Validate ChiTietQuyDinhLaiSuat exists
        ChiTietQuyDinhLaiSuat chiTietQuyDinh = chiTietQuyDinhLaiSuatRepository
                .findByLoaiKyHan_LoaiKyHanIDAndLoaiTietKiem_LoaiTietKiemIDAndTanSuatNhanLai_TanSoNhanLaiID(
                        dto.getSoThang(),
                        dto.getLoaiTietKiemId(),
                        dto.getTanSuatNhanLaiId())
                .orElseThrow(() -> new RuntimeException("Quy định lãi suất không hợp lệ"));

        // Validate minimum deposit amount
        if (dto.getSoTienGuiBanDau() < 100000L) {
            throw new RuntimeException("Số tiền gửi phải lớn hơn số tiền tối thiểu: 100.000 VND");
        }

        // Validate hình thức đáo hạn
        if (!hinhThucDaoHanRepository.existsById(dto.getHinhThucDaoHanId())) {
            throw new RuntimeException("Hình thức đáo hạn không hợp lệ");
        }
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

    public List<PhieuGuiTienDTO> getPhieuGuiTienByKhachHangId(Long khachHangId) {
        List<PhieuGuiTien> phieuGuiTiens = phieuGuiTienRepository.findByKhachHang_KhachHangID(khachHangId);
        return phieuGuiTiens.stream()
                .map(phieuGuiTienMapper::toDTO)
                .collect(Collectors.toList());
    }

}
