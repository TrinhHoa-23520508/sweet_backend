package com.example.sweet.services.GiaoDich;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.GiaoDich.PhieuTraLaiRepository;
import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.util.mapper.PhieuTraLaiMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PhieuTraLaiService {
    // Thêm các repository cần thiết
    private final PhieuTraLaiRepository phieuTraLaiRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final PhieuTraLaiMapper phieuTraLaiMapper;
    private final GiaoDichService giaoDichService;
    private final LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    private final LoaiGiaoDichRepository loaiGiaoDichRepository;
    private final KenhGiaoDichRepository kenhGiaoDichRepository;
    private final KhachHangRepository khachHangRepository;
    private final TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
    private final LichSuGiaoDich_PhieuGuiTienRepository lichSuPGTRepo;

    public List<PhieuTraLaiDTO> getAllPhieuTraLai() {
        Iterable<PhieuTraLai> phieuTraLais = phieuTraLaiRepository.findAll();
        List<PhieuTraLai> phieuTraLaiList = new ArrayList<>();
        phieuTraLais.forEach(phieuTraLaiList::add);

        return phieuTraLaiList.stream()
                .map(phieuTraLaiMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PhieuTraLaiDTO createPhieuTraLai(PhieuTraLaiDTO dto) {
        try {
            // Tự động set ngày trả lãi là thời điểm hiện tại
            dto.setNgayTraLai(Instant.now());

            // B3: Đọc thông tin từ DB
            PhieuGuiTien phieuGuiTien = phieuGuiTienRepository.findById(dto.getPhieuGuiTienID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền"));

            // B4: Kiểm tra ngày trả lãi
            if (!dto.getNgayTraLai().isBefore(phieuGuiTien.getNgayDaoHan())) {
                throw new RuntimeException("Ngày trả lãi phải trước ngày đáo hạn");
            }

            // B5: Kiểm tra trùng lặp phiếu trả lãi
            if (phieuTraLaiRepository.existsByPhieuGuiTienAndNgayTraLai(
                    phieuGuiTien, dto.getNgayTraLai())) {
                throw new RuntimeException("Đã tồn tại phiếu trả lãi cho ngày này");
            }

            // B6: Kiểm tra tần suất trả lãi
            // validateNgayTraLai(dto.getNgayTraLai(), phieuGuiTien);

            // B7-B8: Tính toán và cập nhật tiền lãi
            Long tienLaiNhanDuoc = phieuGuiTien.getTienLaiNhanDinhKy();
            Long tienLaiChuaQuyetToanMoi = phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan() + tienLaiNhanDuoc;
            phieuGuiTien.setTienLaiDaNhanNhungChuaQuyetToan(tienLaiChuaQuyetToanMoi);

            // B9: Kiểm tra điều kiện đặc biệt
            String tanSuat = phieuGuiTien.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai().getTenTanSoNhanLai();
            String hinhThucDaoHan = phieuGuiTien.getHinhThucDaoHan().getTenHinhThucDaoHang();

            if ("Cuối kỳ hạn".equals(tanSuat) &&
                    "Chuyển gốc và lãi sang kỳ hạn mới".equals(hinhThucDaoHan)) {
                return null;
            }

            // B10: Tạo giao dịch mới
            GiaoDich giaoDich = new GiaoDich();
            giaoDich.setSoTienGiaoDich(tienLaiNhanDuoc);
            giaoDich.setThoiGianGiaoDich(dto.getNgayTraLai());
            giaoDich.setNoiDung("trả lãi phiếu gửi tiền ID: " + phieuGuiTien.getPhieuGuiTienID());

            // Save PhieuGuiTien first to ensure it has ID
            phieuGuiTien = phieuGuiTienRepository.save(phieuGuiTien);

            // Get and save TaiKhoanThanhToan
            KhachHang khachHang = khachHangRepository.findById(phieuGuiTien.getKhachHang().getKhachHangID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

            TaiKhoanThanhToan taiKhoanDich = taiKhoanThanhToanRepository
                    .findByKhachHangKhachHangID(khachHang.getKhachHangID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán"));
            taiKhoanDich = taiKhoanThanhToanRepository.save(taiKhoanDich);

            // Now set source and destination accounts with saved IDs
            giaoDich.setTaiKhoanNguon(phieuGuiTien.getPhieuGuiTienID());
            giaoDich.setLoaiTaiKhoanNguon(
                    loaiTaiKhoanRepository.findById(2L)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tài khoản"))); // 2L - TK tiết
                                                                                                        // kiệm

            giaoDich.setTaiKhoanDich(taiKhoanDich.getSoTaiKhoan());
            giaoDich.setLoaiTaiKhoanDich(
                    loaiTaiKhoanRepository.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tài khoản"))); // 1L - TK thanh
                                                                                                        // toán

            // Set loại và kênh giao dịch
            giaoDich.setLoaiGiaoDich(loaiGiaoDichRepository.findById(6L).get()); // 4L - Trả lãi
            giaoDich.setKenhGiaoDich(kenhGiaoDichRepository.findById(2L).get()); // 1L - Tại quầy

            // Lưu giao dịch
            GiaoDich savedGiaoDich = giaoDichService.createGiaoDich(giaoDich);

            // B11: Lưu phiếu trả lãi
            PhieuTraLai phieuTraLai = phieuTraLaiMapper.toEntity(dto);
            phieuTraLai.setPhieuGuiTien(phieuGuiTien);
            phieuTraLai.setGiaoDich(savedGiaoDich);

            PhieuTraLai savedPhieuTraLai = phieuTraLaiRepository.save(phieuTraLai);
            phieuGuiTienRepository.save(phieuGuiTien);
            // Sau khi có savedGiaoDich, lưu lịch sử giao dịch
            LichSuGiaoDich_PhieuGuiTien lichSuPGT = new LichSuGiaoDich_PhieuGuiTien();
            lichSuPGT.setPhieuGuiTien(phieuGuiTien);
            lichSuPGT.setGiaoDich(savedGiaoDich);
            lichSuPGT.setSoTienGocGiaoDich(0.0);
            lichSuPGT.setSoDuHienTai_SauGD(phieuGuiTien.getSoDuHienTai());
            lichSuPGT.setTienLai_TrongGD(phieuGuiTien.getTienLaiNhanDinhKy());
            lichSuPGT.setLaiQuyetToan_TrongGD(0.0);
            lichSuPGT.setTienLaiDaNhanNhungChuaQuyetToan_SauGD(phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan());
            lichSuPGT.setTongLaiQuyetToan_SauGD(phieuGuiTien.getTongLaiQuyetToan());

            lichSuPGTRepo.save(lichSuPGT);

            return phieuTraLaiMapper.toDTO(savedPhieuTraLai);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo phiếu trả lãi: " + e.getMessage());
        }
    }

    @Transactional
    public void deletePhieuTraLai(Long id) {
        try {
            phieuTraLaiRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa phiếu trả lãi: " + e.getMessage());
        }
    }

    private void validateNgayTraLai(Instant ngayTraLai, PhieuGuiTien phieuGuiTien) {
        String tanSuat = phieuGuiTien.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai().getTenTanSoNhanLai();

        Instant ngayGui = phieuGuiTien.getNgayGuiTien();
        Instant ngayDaoHan = phieuGuiTien.getNgayDaoHan();

        // Get calendar instances for date comparisons
        Calendar calNgayGui = Calendar.getInstance();
        calNgayGui.setTimeInMillis(ngayGui.toEpochMilli());

        Calendar calNgayTra = Calendar.getInstance();
        calNgayTra.setTimeInMillis(ngayTraLai.toEpochMilli());

        switch (tanSuat.toLowerCase()) {
            case "đầu kỳ hạn":
                if (!ngayTraLai.equals(ngayGui)) {
                    throw new RuntimeException("Ngày trả lãi phải là ngày gửi tiền");
                }
                break;
            case "cuối kỳ hạn":
                if (!ngayTraLai.equals(ngayDaoHan)) {
                    throw new RuntimeException("Ngày trả lãi phải là ngày đáo hạn");
                }
                break;
            case "hàng tháng":
                if (calNgayTra.get(Calendar.DAY_OF_MONTH) != calNgayGui.get(Calendar.DAY_OF_MONTH)) {
                    throw new RuntimeException("Ngày trả lãi phải cùng ngày với ngày gửi tiền");
                }
                break;
            case "hàng quý":
                if (calNgayTra.get(Calendar.DAY_OF_MONTH) != calNgayGui.get(Calendar.DAY_OF_MONTH) ||
                        (calNgayTra.get(Calendar.MONTH) - calNgayGui.get(Calendar.MONTH)) % 3 != 0) {
                    throw new RuntimeException("Ngày trả lãi phải đúng chu kỳ quý");
                }
                break;
        }
    }
}
