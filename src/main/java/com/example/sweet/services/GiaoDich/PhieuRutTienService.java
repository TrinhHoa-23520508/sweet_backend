package com.example.sweet.services.GiaoDich;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuRutTienRepository;
import com.example.sweet.database.repository.Loai.*;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.domain.request.PhieuRutTienreqDTO;
import com.example.sweet.services.QuyDinhLaiSuatService;
import com.example.sweet.util.mapper.PhieuRutTienMapper;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PhieuRutTienService {

    private final LoaiGiaoDichRepository loaiGiaoDichRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PhieuRutTienMapper phieuRutTienMapper;

    private final PhieuRutTienRepository phieuRutTienRepository;
    private final GiaoDichService giaoDichService;
    private final KenhGiaoDichRepository kenhGiaoDichRepository;
    private final LichSuGiaoDich_PhieuGuiTienRepository lichSuPGTRepo;
    private final TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
    private final LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    private final QuyDinhLaiSuatService quyDinhLaiSuatService;

    @Transactional
    public List<PhieuRutTien> handleGetAllPhieuRutTien() {
        List<PhieuRutTien> phieuRutTienList = (List<PhieuRutTien>) this.phieuRutTienRepository.findAll();
        if (phieuRutTienList.isEmpty()) {
            throw new IllegalArgumentException("Không có phiếu rút tiền nào trong hệ thống");
        }
        return phieuRutTienList;
    }

    @Transactional
    public PhieuRutTien handleCreatePhieuRutTien(PhieuRutTienreqDTO dto) {
        try {
            // B1: Nhận D1 từ người dùng - đã có trong dto

            // B2 & B3: Đọc D3 từ bộ nhớ phụ
            PhieuGuiTien phieuGuiTien = phieuGuiTienRepository.findById(dto.getPhieuGuiTienID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền"));

            // B4: Kiểm tra số dư hiện tại = 0?
            if (phieuGuiTien.getSoDuHienTai() == 0) {
                throw new RuntimeException("Phiếu gửi tiền này đã tất toán");
            }

            // B5: Kiểm tra loại tiết kiệm
            String loaiTietKiem = phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiTietKiem().getTenLoaiTietKiem();
            if ("Tiết kiệm có kỳ hạn".equals(loaiTietKiem)) {
                if (dto.getSoTienRut().longValue() != phieuGuiTien.getSoDuHienTai().longValue()) {
                    throw new RuntimeException("Với loại tiết kiệm có kỳ hạn, số tiền rút phải bằng số dư hiện tại");
                }
            } else if ("Tiết kiệm linh hoạt".equals(loaiTietKiem)) {
                if (dto.getSoTienRut() > phieuGuiTien.getSoDuHienTai()) {
                    throw new RuntimeException("Số tiền rút không được lớn hơn số dư hiện tại");
                }
            }

            // B6: Kiểm tra ngày rút tiền và tính lãi quyết toán
            Instant ngayRut = Instant.now();
            long laiQuyetToanKhiRut;

            var currentQuyDinhLaiSuat = quyDinhLaiSuatService.findCurrentQuyDinhLaiSuat()
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy quy định lãi suất hiện tại"));

            if (ngayRut.isBefore(phieuGuiTien.getNgayDaoHan())) {
                // Tính số ngày gửi tiền
                long soNgayGuiTien = ChronoUnit.DAYS.between(phieuGuiTien.getNgayGuiTien(), ngayRut);
                // Lãi quyết toán = Số tiền gốc * số ngày * (lãi suất không kỳ hạn / 365)
                laiQuyetToanKhiRut = Math
                        .round(dto.getSoTienRut() * soNgayGuiTien
                                * (currentQuyDinhLaiSuat.getLaiSuatKhongKyHan() / 365.0));
            } else {
                // Nếu rút đúng hoặc sau ngày đáo hạn, lãi = tổng lãi dự kiến
                laiQuyetToanKhiRut = phieuGuiTien.getTongTienLaiDuKien();
            }

            // B7: Thực hiện các tính toán
            long tienLaiNhanDuocKhiRut = laiQuyetToanKhiRut - phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan();
            long soTienNhanDuocKhiRut = dto.getSoTienRut() + tienLaiNhanDuocKhiRut;
            long soDuSauKhiRut = phieuGuiTien.getSoDuHienTai() - dto.getSoTienRut();
            long tienLaiDaNhanNhungChuaQuyetToanSauRut = 0L;
            long tongLaiQuyetToanSauRut = phieuGuiTien.getTongLaiQuyetToan() + laiQuyetToanKhiRut;

            // B8: Cập nhật các giá trị mới cho phiếu gửi tiền
            double tyLe = (double) dto.getSoTienRut() / phieuGuiTien.getSoDuHienTai();
            long tongTienLaiDuKienMoi = phieuGuiTien.getTongTienLaiDuKien()
                    - Math.round(phieuGuiTien.getTongTienLaiDuKien() * tyLe);

            phieuGuiTien.setTienLaiDaNhanNhungChuaQuyetToan(tienLaiDaNhanNhungChuaQuyetToanSauRut);
            phieuGuiTien.setTongLaiQuyetToan(tongLaiQuyetToanSauRut);

            phieuGuiTien.setTongTienLaiDuKien(tongTienLaiDuKienMoi);

            // B9: Xác định loại giao dịch
            Long loaiGiaoDichId = (soDuSauKhiRut == 0) ? 5L : 4L; // 3L: Tất toán, 2L: Rút tiền

            // B10: Tạo giao dịch mới
            GiaoDich giaoDich = new GiaoDich();
            giaoDich.setSoTienGiaoDich(soTienNhanDuocKhiRut);
            giaoDich.setThoiGianGiaoDich(ngayRut);
            giaoDich.setNoiDung("Rút tiền từ phiếu gửi tiền ID: " + phieuGuiTien.getPhieuGuiTienID());
            giaoDich.setLoaiGiaoDich(loaiGiaoDichRepository.findById(loaiGiaoDichId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy loại giao dịch")));
            giaoDich.setKenhGiaoDich(kenhGiaoDichRepository.findById(dto.getKenhGiaoDichID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy kênh giao dịch")));

            KhachHang khachHang = khachHangRepository.findById(phieuGuiTien.getKhachHang().getKhachHangID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

            NhanVien giaoDichVien = nhanVienRepository.findById(phieuGuiTien.getGiaoDichVien().getNhanVienID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch viên"));
            giaoDich.setNhanVienGiaoDich(giaoDichVien);

            // Set tài khoản nguồn và đích
            TaiKhoanThanhToan taiKhoanDich = taiKhoanThanhToanRepository
                    .findByKhachHangKhachHangID(khachHang.getKhachHangID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán"));
            taiKhoanDich = taiKhoanThanhToanRepository.save(taiKhoanDich);
            giaoDich.setTaiKhoanDich(taiKhoanDich.getSoTaiKhoan());
            giaoDich.setLoaiTaiKhoanDich(
                    loaiTaiKhoanRepository.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tài khoản"))); // 1L cho tài
                                                                                                        // khoản thanh
                                                                                                        // toán

            giaoDich.setTaiKhoanNguon(phieuGuiTien.getPhieuGuiTienID()); // phieuGuiTien sẽ là tài khoản đích
            giaoDich.setLoaiTaiKhoanNguon(
                    loaiTaiKhoanRepository.findById(2L)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại tài khoản"))); // 2L cho tài
                                                                                                        // khoản tiết
                                                                                                        // kiệm

            GiaoDich savedGiaoDich = giaoDichService.createGiaoDich(giaoDich);
            phieuGuiTien.setSoDuHienTai(soDuSauKhiRut);
            // Lưu phiếu gửi tiền
            phieuGuiTien = phieuGuiTienRepository.save(phieuGuiTien);

            // Tạo và lưu phiếu rút tiền
            PhieuRutTien phieuRutTien = phieuRutTienMapper.toEntity(dto);
            phieuRutTien.setPhieuGuiTien(phieuGuiTien);
            phieuRutTien.setGiaoDich(giaoDich);
            phieuRutTien.setNgayRut(ngayRut);

            // Lưu giao dịch và phiếu rút tiền
            phieuRutTien = phieuRutTienRepository.save(phieuRutTien);

            // Lưu lịch sử giao dịch
            LichSuGiaoDich_PhieuGuiTien lichSuPGT = new LichSuGiaoDich_PhieuGuiTien();
            lichSuPGT.setPhieuGuiTien(phieuGuiTien);
            lichSuPGT.setGiaoDich(giaoDich);
            lichSuPGT.setSoTienGocGiaoDich((double) dto.getSoTienRut());
            lichSuPGT.setSoDuHienTai_SauGD((double) soDuSauKhiRut);
            lichSuPGT.setTienLai_TrongGD((double) tienLaiNhanDuocKhiRut);
            lichSuPGT.setLaiQuyetToan_TrongGD((double) laiQuyetToanKhiRut);
            lichSuPGT.setTienLaiDaNhanNhungChuaQuyetToan_SauGD((double) tienLaiDaNhanNhungChuaQuyetToanSauRut);
            lichSuPGT.setTongLaiQuyetToan_SauGD((double) tongLaiQuyetToanSauRut);

            lichSuPGTRepo.save(lichSuPGT);

            return phieuRutTien;

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo phiếu rút tiền: " + e.getMessage());
        }
    }
}
