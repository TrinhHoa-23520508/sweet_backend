package com.example.sweet.services.GiaoDich;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuDaoHanRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuRutTienRepository;
import com.example.sweet.database.repository.Loai.*;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.repository.dto.PhieuRutTien.PhieuRutTienDTO_inp;
import com.example.sweet.database.repository.dto.PhieuRutTien.PhieuRutTienDTO_out;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.util.mapper.PhieuDaoHanMapper;
import com.example.sweet.util.mapper.PhieuGuiTienMapper;

import jakarta.transaction.Transactional;

@Service
public class PhieuRutTienService {

    private final LoaiGiaoDichRepository loaiGiaoDichRepository;
    private final HinhThucDaoHanRepository hinhThucDaoHanRepository;

    private final LoaiKyHanRepository loaiKyHanRepository;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final PhieuGuiTienMapper phieuGuiTienMapper;

    private final PhieuDaoHanRepository phieuDaoHanRepository;
    private final PhieuDaoHanMapper phieuDaoHanMapper;

    private final PhieuRutTienRepository phieuRutTienRepository;
    private final TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
    private final LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    private final GiaoDichRepository giaoDichRepository;

    public PhieuRutTienService(
            PhieuRutTienRepository phieuRutTienRepository,
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            LoaiTietKiemRepository loaiTietKiemRepository,
            TanSuatNhanLaiRepository tanSuatNhanLaiRepository,
            LoaiKyHanRepository loaiKyHanRepository,
            HinhThucDaoHanRepository hinhThucDaoHanRepository,
            TrangThaiRepository trangThaiRepository,
            PhieuGuiTienMapper phieuGuiTienMapper,
            PhieuGuiTienRepository phieuGuiTienRepository,
            PhieuDaoHanMapper phieuDaoHanMapper,
            PhieuDaoHanRepository phieuDaoHanRepository,
            TaiKhoanThanhToanRepository taiKhoanThanhToanRepository,
            LoaiTaiKhoanRepository loaiTaiKhoanRepository,
            GiaoDichRepository giaoDichRepository,
            LoaiGiaoDichRepository loaiGiaoDichRepository) {
        this.phieuDaoHanRepository = phieuDaoHanRepository;
        this.phieuRutTienRepository = phieuRutTienRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.tanSuatNhanLaiRepository = tanSuatNhanLaiRepository;
        this.loaiKyHanRepository = loaiKyHanRepository;
        this.hinhThucDaoHanRepository = hinhThucDaoHanRepository;
        this.trangThaiRepository = trangThaiRepository;
        this.phieuGuiTienMapper = phieuGuiTienMapper;
        this.phieuDaoHanMapper = phieuDaoHanMapper;
        this.phieuGuiTienRepository = phieuGuiTienRepository;
        this.taiKhoanThanhToanRepository = taiKhoanThanhToanRepository;
        this.loaiTaiKhoanRepository = loaiTaiKhoanRepository;
        this.giaoDichRepository = giaoDichRepository;
        this.loaiGiaoDichRepository = loaiGiaoDichRepository;
    }

    @Transactional
    public List<PhieuRutTien> handleGetAllPhieuRutTien() {
        List<PhieuRutTien> phieuRutTienList = (List<PhieuRutTien>) this.phieuRutTienRepository.findAll();
        if (phieuRutTienList.isEmpty()) {
            throw new IllegalArgumentException("Không có phiếu rút tiền nào trong hệ thống");
        }
        return phieuRutTienList;
    }

    @Transactional
    public PhieuRutTienDTO_out handleCreatePhieuRutTien(PhieuRutTienDTO_inp dto) {
        if (!this.phieuGuiTienRepository.existsById(dto.getMaPhieuGuiTien()))
            throw new IllegalArgumentException("Mã phiếu gửi tiền không tồn tại");
        if (!this.khachHangRepository.existsById(dto.getMaKhachHang()))
            throw new IllegalArgumentException("Mã khách hàng không tồn tại");
        if (!this.nhanVienRepository.existsById(dto.getMaGiaoDichVien()))
            throw new IllegalArgumentException("Mã giao dịch viên không tồn tại");
        if (dto.getSoTienRut() <= 0)
            throw new IllegalArgumentException("Số tiền rút phải lớn hơn 0");

        PhieuGuiTien phieuGuiTien = this.phieuGuiTienRepository.findById(dto.getMaPhieuGuiTien())
                .orElseThrow(() -> new IllegalArgumentException("Mã phiếu gửi tiền không tồn tại"));
        PhieuRutTien phieuRutTien = new PhieuRutTien(null,
                phieuGuiTien,
                null, // dc gán sau khi lưu giao dịch
                dto.getSoTienRut(),
                Instant.now(),
                phieuGuiTien.getChiTietQuyDinhLaiSuat().getLaiSuat());

        phieuRutTien = phieuRutTienRepository.saveAndFlush(phieuRutTien);
        PhieuRutTienDTO_out phieuRutTienDTO_out = new PhieuRutTienDTO_out();

        GiaoDich giaoDich = new GiaoDich();

        KhachHang khachHang = this.khachHangRepository.findById(dto.getMaKhachHang()).get();

        giaoDich.setTaiKhoanDich(taiKhoanThanhToanRepository.findByKhachHang_KhachHangID(khachHang.getKhachHangID())
                .get().getSoTaiKhoan());
        giaoDich.setLoaiTaiKhoanDich(loaiTaiKhoanRepository.findById(1L).get());
        NhanVien giaoDichVien = this.nhanVienRepository.findById(dto.getMaGiaoDichVien())
                .orElseThrow(() -> new IllegalArgumentException("Mã giao dịch viên không tồn tại"));
        giaoDich.setNhanVienGiaoDich(giaoDichVien);
        TaiKhoanThanhToan tktt = new TaiKhoanThanhToan();
        giaoDich.setNoiDung("Rút tiền từ phiếu gửi tiền " + phieuGuiTien.getPhieuGuiTienID() + " của khách hàng "
                + khachHang.getKhachHangID() + " - " + khachHang.getHoTen());
        giaoDich.setThoiGianGiaoDich(Instant.now());
        giaoDich.setTaiKhoanNguon(null);

        giaoDich = this.giaoDichRepository.saveAndFlush(giaoDich);
        phieuRutTien.setGiaoDich(giaoDich);
        initDTO(dto, phieuRutTienDTO_out, phieuGuiTien);

        xuLiRutTien(dto, phieuRutTienDTO_out, phieuGuiTien, giaoDich);
        return phieuRutTienDTO_out;
    }

    private void initDTO(PhieuRutTienDTO_inp dto_inp, PhieuRutTienDTO_out dto_out, PhieuGuiTien phieuGuiTien) {
        dto_out.setMaKH(dto_inp.getMaKhachHang());
        dto_out.setHoTenKhachHang(this.khachHangRepository.findById(dto_inp.getMaKhachHang()).get().getHoTen());
        dto_out.setCccd(this.khachHangRepository.findById(dto_inp.getMaKhachHang()).get().getCccd());

        dto_out.setMaPhieuGuiTien(dto_inp.getMaPhieuGuiTien());
        dto_out.setNgayGuiTien(phieuGuiTien.getNgayGuiTien());

        dto_out.setNgayGuiTien(phieuGuiTien.getNgayGuiTien());
        dto_out.setSoTienGui(phieuGuiTien.getSoTienGuiBanDau());
        dto_out.setLoaiTietKiem(phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiTietKiem());
        dto_out.setTanSuatNhanLai(phieuGuiTien.getChiTietQuyDinhLaiSuat().getTanSuatNhanLai());
        dto_out.setHinhThucDaoHan(phieuGuiTien.getHinhThucDaoHan());
        dto_out.setLoaiKyHan(phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiKyHan());

        dto_out.setLaiSuat(phieuGuiTien.getChiTietQuyDinhLaiSuat().getLaiSuat());

        dto_out.setSoDuHienTai(phieuGuiTien.getSoDuHienTai());
        dto_out.setTongLaiDuKien(phieuGuiTien.getTongTienLaiDuKien());
        dto_out.setTienLaiNhanDinhKy(phieuGuiTien.getTienLaiNhanDinhKy());
        dto_out.setTienLaiDaNhanNhungChuaQuyetToan(phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan());

        dto_out.setTongLaiQuyetToan(phieuGuiTien.getTongLaiQuyetToan());
        dto_out.setNgayDaoHan(phieuGuiTien.getNgayDaoHan());
        dto_out.setNgayRut(Instant.now());
        dto_out.setSoTienRut(dto_inp.getSoTienRut());

    }

    public void xuLiRutTien(PhieuRutTienDTO_inp dto_inp, PhieuRutTienDTO_out dto_out, PhieuGuiTien phieuGuiTien,
            GiaoDich giaoDich) {
        if (isPhieuGuiTienDaTatToan(phieuGuiTien)) {
            throw new IllegalArgumentException("Phiếu gửi tiền đã tất toán, không thể rút tiền");
        }
        if (!isDuocPhepRutTien(phieuGuiTien, dto_inp)) {
            throw new IllegalArgumentException("Không được phép rút tiền từ phiếu gửi tiền này");
        }
        xuLyLaiQuyetToan(phieuGuiTien, dto_inp, dto_out);
        capNhatThongTinLaiSuat(phieuGuiTien, dto_inp, dto_out);
        capNhatThongTinSauRutTien(phieuGuiTien, dto_out);
        capNhatLoaiGiaoDich(phieuGuiTien, dto_out, giaoDich);
        giaoDich = giaoDichRepository.saveAndFlush(giaoDich);
        phieuGuiTienRepository.save(phieuGuiTien);

    }

    // Hiện thực B4
    public Boolean isPhieuGuiTienDaTatToan(PhieuGuiTien phieuGuiTien) {
        // Kiểm tra xem Số dư hiện tại của phiếu gửi tiền có bằng 0 hay không
        return phieuGuiTien.getSoDuHienTai() == 0;
    }

    // HIện thực B5
    public Boolean isDuocPhepRutTien(PhieuGuiTien phieuGuiTien, PhieuRutTienDTO_inp dto_inp) {
        // Kiểm tra xem Loại tiết kiệm có kỳ hạn hay không
        // - Nếu Loại tiết kiệm = “Tiết kiệm có kỳ hạn” thì Kiểm tra Số tiền gốc muốn
        // rút (D1) = Số dư hiện tại (D3) hay không? Nếu không thì đến B12
        if (phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiTietKiem().getTenLoaiTietKiem()
                .equals("Tiết kiệm có kỳ hạn")) {
            if (phieuGuiTien.getSoDuHienTai() == dto_inp.getSoTienRut()) {
                return true;
            }
            return false;
        }
        // - Nếu Loại tiết kiệm = “Tiết kiệm có kỳ hạn rút gốc linh hoạt” thì Kiểm tra
        // Số tiền gốc muốn rút > Số dư hiện tại (D3
        if (phieuGuiTien.getChiTietQuyDinhLaiSuat().getLoaiTietKiem().getTenLoaiTietKiem()
                .equals("Tiết kiệm linh hoạt")) {
            if (phieuGuiTien.getSoDuHienTai() <= dto_inp.getSoTienRut()) {
                return true;
            }
            return false;
        }
        return false;
    }

    // Hiện thực B6
    public void xuLyLaiQuyetToan(PhieuGuiTien phieuGuiTien, PhieuRutTienDTO_inp dto_inp, PhieuRutTienDTO_out dto_out) {
        if (dto_out.getNgayRut().isBefore(phieuGuiTien.getNgayDaoHan())) {
            // Lãi quyết toán khi rút tiền = Số tiền gốc muốn rút * Số ngày gửi tiền (đến
            // thời điểm Ngày rút) * (Lãi suất tiết kiệm không kỳ hạn / 365)
            long soNgayGuiTien = LocalDate.from(dto_out.getNgayRut()).toEpochDay()
                    - LocalDate.from(phieuGuiTien.getNgayGuiTien()).toEpochDay();
            dto_out.setLaiQuyetToanKhiRut((long) (dto_inp.getSoTienRut() * soNgayGuiTien
                    * (phieuGuiTien.getChiTietQuyDinhLaiSuat().getLaiSuat() / 365)));
        } else {
            // Lãi quyết toán khi rút tiền = Tổng tiền lãi dự kiến
            dto_out.setLaiQuyetToanKhiRut((phieuGuiTien.getTongTienLaiDuKien()));
        }
    }

    // Hiện thực B7
    public void capNhatThongTinLaiSuat(PhieuGuiTien phieuGuiTien, PhieuRutTienDTO_inp dto_inp,
            PhieuRutTienDTO_out dto_out) {
        dto_out.setTienLaiNhanDuocKhiRut((dto_out.getLaiQuyetToanKhiRut()
                - phieuGuiTien.getTienLaiDaNhanNhungChuaQuyetToan()));
        dto_out.setSoTienNhanDuocKhiRut(dto_inp.getSoTienRut() + dto_out.getTienLaiNhanDuocKhiRut());
        dto_out.setSoDuSauKhiRut((phieuGuiTien.getSoDuHienTai() - dto_inp.getSoTienRut()));
        phieuGuiTien.setSoDuHienTai(dto_out.getSoDuSauKhiRut());
        dto_out.setTienLaiDaNhanNhungChuaQuyetToanSauRut(0L);
        dto_out.setTongLaiQuyetToanSauRut((phieuGuiTien.getTongLaiQuyetToan()
                + dto_out.getLaiQuyetToanKhiRut()));
    }

    // HIện thực B8
    public void capNhatThongTinSauRutTien(PhieuGuiTien phieuGuiTien, PhieuRutTienDTO_out dto_out) {
        // Tổng tiền lãi dự kiến (mới) = Tổng tiền lãi dự kiến - Tổng tiền lãi dự kiến
        // * (Số tiền gốc muốn rút / Số dư hiện tại)
        dto_out.setTongLaiDuKien((long) (phieuGuiTien.getTongTienLaiDuKien()
                - phieuGuiTien.getTongTienLaiDuKien() * (dto_out.getSoTienRut() / phieuGuiTien.getSoDuHienTai())));
        // Tiền lãi đã nhận nhưng chưa quyết toán (mới) = Tiền lãi đã nhận nhưng chưa
        // quyết toán sau khi rút
        dto_out.setTienLaiDaNhanNhungChuaQuyetToan(dto_out.getTienLaiDaNhanNhungChuaQuyetToanSauRut());
        // Tổng lãi quyết toán (mới) = Tổng lãi quyết toán sau khi rút
        dto_out.setTongLaiQuyetToan(dto_out.getTongLaiQuyetToanSauRut());
        // Số dư hiện tại (mới) = Số dư sau khi rút tiền
        phieuGuiTien.setSoDuHienTai(dto_out.getSoDuSauKhiRut());
    }

    // Hiện thực B9
    public void capNhatLoaiGiaoDich(PhieuGuiTien phieuGuiTien, PhieuRutTienDTO_out dto_out, GiaoDich giaoDich) {
        if (phieuGuiTien.getSoDuHienTai() == 0) {
            // Nếu Số dư hiện tại = 0 thì: Loại giao dịch = “Tất toán phiếu gửi tiền”.
            giaoDich.setLoaiGiaoDich(loaiGiaoDichRepository.findById(5L).get());
            dto_out.setLoaiGiaoDich(giaoDich.getLoaiGiaoDich());
        } else {
            // Nếu Số dư hiện tại ≠ 0 thì: Loại giao dịch = “Rút tiền từ phiếu gửi tiền”
            giaoDich.setLoaiGiaoDich(loaiGiaoDichRepository.findById(4L).get());
            dto_out.setLoaiGiaoDich(giaoDich.getLoaiGiaoDich());
        }
    }
}

// D1: Mã phiếu gửi tiền, Mã khách hàng, Ngày rút, Số tiền gốc muốn rút, Mã giao
// dịch viên

// D3: Thông tin về phiếu gửi tiền (Loại tiết kiệm, Số tiền gửi ban đầu, Số dư
// hiện tại, Ngày gửi tiền, Ngày đáo hạn, Hình thức đáo hạn, Tần suất nhận lãi,
// Tổng tiền lãi dự kiến, Tiền lãi nhận định kỳ, Tiền lãi đã nhận nhưng chưa
// quyết toán, Tổng lãi quyết toán) ứng với Mã phiếu gửi tiền (D1),
// Thông tin khách hàng (Họ tên, CCCD/CMND) ứng với Mã khách hàng (D1),
// Thông tin về Mức lãi suất tiết kiệm không kỳ hạn
// Thông tin giao dịch viên (Họ tên giao dịch viên) ứng với Mã giao dịch viên
// (D1)

// 3.8.3. Thuật toán:
// B1: Nhận D1 từ người dùng
// B2: Kết nối cơ sở dữ liệu
// B3: Đọc D3 từ bộ nhớ phụ
// B4: Kiểm tra Số dư hiện tại (D3) = 0 hay không? Nếu có thì đến B12 (Nghĩa là
// phiếu gửi tiền này đã tất toán)
// B5: Kiểm tra xem Loại tiết kiệm (D3) thuộc loại nào?
// - Nếu Loại tiết kiệm = “Tiết kiệm có kỳ hạn” thì Kiểm tra Số tiền gốc muốn
// rút (D1) = Số dư hiện tại (D3) hay không? Nếu không thì đến B12
// - Nếu Loại tiết kiệm = “Tiết kiệm có kỳ hạn rút gốc linh hoạt” thì Kiểm tra
// Số tiền gốc muốn rút > Số dư hiện tại (D3) hay không? Nếu có thì đến B12

// B6: Kiểm tra Ngày rút tiền (D1) < Ngày đáo hạn (D3) hay không?
// - Nếu có thì: Lãi quyết toán khi rút tiền = Số tiền gốc muốn rút * Số ngày
// gửi tiền (đến thời điểm Ngày rút) * (Lãi suất tiết kiệm không kỳ hạn / 365)
// - Nếu không thì: Lãi quyết toán khi rút tiền = Tổng tiền lãi dự kiến

// B7: Thực hiện các tính toán sau:
// - Tiền lãi nhận được khi rút tiền = Lãi quyết toán khi rút tiền - Tiền lãi đã
// nhận nhưng chưa quyết toán
// - Số tiền nhận được khi rút tiền = Số tiền gốc muốn rút + Tiền lãi nhận được
// khi rút tiền
// - Số dư sau khi rút tiền = Số dư hiện tại - Số tiền gốc muốn rút,
// - Tiền lãi đã nhận nhưng chưa quyết toán sau khi rút = 0
// - Tổng lãi quyết toán sau khi rút = Tổng lãi quyết toán + Lãi quyết toán khi
// rút tiền

// B8: Thực hiện tính toán để chuẩn bị cập nhật:
// - Tổng tiền lãi dự kiến (mới) = Tổng tiền lãi dự kiến - Tổng tiền lãi dự kiến
// * (Số tiền gốc muốn rút / Số dư hiện tại)
// - Tiền lãi đã nhận nhưng chưa quyết toán (mới) = Tiền lãi đã nhận nhưng chưa
// quyết toán sau khi rút
// - Tổng lãi quyết toán (mới) = Tổng lãi quyết toán sau khi rút
// - Số dư hiện tại (mới) = Số dư sau khi rút tiền
// B9: Kiểm tra Số dư sau khi rút = 0 hay không?
// - Nếu Số dư hiện tại = 0 thì: Loại giao dịch = “Tất toán phiếu gửi tiền”.
// - Nếu Số dư hiện tại ≠ 0 thì: Loại giao dịch = “Rút tiền từ phiếu gửi tiền”
// B10: Lưu D4 xuống bộ nhớ phụ
// B11: Xuất D5 ra máy in
// B12: Đóng kết nối cơ sở dữ liệu
// B13: Kết thúc
