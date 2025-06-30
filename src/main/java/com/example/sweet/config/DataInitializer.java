package com.example.sweet.config;

import com.example.sweet.database.repository.Loai.*;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.QuyenHanRepository;
import com.example.sweet.database.repository.ThamSoRepository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.*;
import com.example.sweet.database.schema.TaiKhoan.*;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.TaiKhoan.DiaChiRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.KhachHangRequestDTO;
import com.example.sweet.domain.request.NhanVienRequestDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;
import com.example.sweet.services.KhachHangService;
import com.example.sweet.services.NhanVienService;
import com.example.sweet.util.constant.KenhGiaoDichEnum;
import com.example.sweet.util.constant.LoaiGiaoDichEnum;
import com.example.sweet.util.constant.LoaiTKDichEnum;
import com.example.sweet.util.constant.SystemParameterEnum;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    private final KenhGiaoDichRepository kenhGiaoDichRepository;
    private final LoaiGiaoDichRepository loaiGiaoDichRepository;
    private final TrangThaiRepository trangThaiRepo;
    private final LoaiTrangThaiRepository loaiTrangThaiRepo;
    private final DiaChiRepository diaChiRepo;
    private final VaiTroRepository vaiTroRepo;
    private final HinhThucDaoHanRepository hinhThucDaoHanRepo;
    private final LoaiTietKiemRepository loaiTietKiemRepo;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepo;

    private final LoaiKyHanRepository loaiKyHanRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final QuyDinhLaiSuatRepository quyDinhLaiSuatRepository;
    private final NhanVienRepository nhanVienRepository;
    private final ChiTietQuyDinhLaiSuatRepository chiTietQuyDinhLaiSuatRepository;
    private final ThamSoRepository thamSoRepository;
    private final QuyenHanRepository quyenHanRepository;
    private final NhanVienService nhanVienService;
    private final KhachHangService khachHangService;


    private final PasswordEncoder passwordEncoder;
    private final RequestMapConfig requestMapper;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (loaiTaiKhoanRepository.count() > 0)
            return;
        thamSoRepository.saveAll(
            Arrays
                .stream(SystemParameterEnum.values())
                .map(value -> new ThamSo(null, value.name(), value.name(), value.name(),
                    "1"))
                .toList());
        loaiTaiKhoanRepository.saveAll(
            Arrays
                .stream(LoaiTKDichEnum.values())
                .map(value -> new LoaiTaiKhoan(null, value.ordinal(), value.getLabel(),
                    value.getLabel()))
                .toList());

        kenhGiaoDichRepository.saveAll(
            Arrays
                .stream(KenhGiaoDichEnum.values())
                .map(value -> new KenhGiaoDich(null, (int) value.getCode(),
                    value.getLabel(), value.getLabel()))
                .toList());

        loaiGiaoDichRepository.saveAll(
            Arrays.stream(LoaiGiaoDichEnum.values())
                .map(value -> new LoaiGiaoDich(null, (int)value.getCode(), value.getLabel(), value.getLabel()))
                .toList()
        );

        var insertedLTT = loaiTrangThaiRepo.saveAll(List.of(
                new LoaiTrangThai(null, "customer", "Khách hàng", "Khách hàng", false, List.of()),
                new LoaiTrangThai(null, "employee", "Nhân viên", "Nhân viên", false, List.of()),
                new LoaiTrangThai(null, "payment_account", "Tài khoản thanh toán",
                    "Tài khoản thanh toán", false, List.of()),
                new LoaiTrangThai(null, "transaction", "Giao dịch", "Giao dịch", false, List.of()),
                new LoaiTrangThai(null, "deposit_receipt", "Phiếu gửi tiền", "Phiếu gửi tiền", false,
                    List.of()),
                new LoaiTrangThai(null, "login_account", "Tài khoản đăng nhập", "Tài khoản đăng nhập",
                    false, List.of())))
            .iterator();
        ArrayList<LoaiTrangThai> loaiTrangThais = new ArrayList<>();
        while (insertedLTT.hasNext()) {
            loaiTrangThais.add(insertedLTT.next());
        }

        var trangThais = trangThaiRepo.saveAll(List.of(
            // Trạng thái khách hàng
            new TrangThai(null, "active", "Còn hoạt động", false, loaiTrangThais.get(0)),
            new TrangThai(null, "locked", "Đã hủy", false, loaiTrangThais.get(0)),

            // Trạng thái nhân viên
            new TrangThai(null, "active", "Còn hoạt động", false, loaiTrangThais.get(1)),
            new TrangThai(null, "locked", "Đã hủy", false, loaiTrangThais.get(1)),

            // Trạng thái tài khoản thanh toán
            new TrangThai(null, "active", "Còn hoạt động", false, loaiTrangThais.get(2)),
            new TrangThai(null, "locked", "Đã khóa", false, loaiTrangThais.get(2)),

            // Trạng thái giao dịch
            new TrangThai(null, "success", "Giao dịch thành công", false, loaiTrangThais.get(3)),
            new TrangThai(null, "failed", "Giao dịch thất bại", false, loaiTrangThais.get(3)),

            // Trạng thái phiếu gửi tiền
            new TrangThai(null, "settled", "Đã tất toán", false, loaiTrangThais.get(4)),
            new TrangThai(null, "unsettled", "Chưa tất toán", false, loaiTrangThais.get(4)),

            // Trạng thái tài khoản đăng nhập
            new TrangThai(null, "active", "Đang hoạt động", false, loaiTrangThais.get(5)),
            new TrangThai(null, "locked", "Đã khóa", false, loaiTrangThais.get(5))));

        var quyenHans = quyenHanRepository.saveAll(requestMapper.getRequestMapToQuyenHan());

        final List<String> thanhToanModules = List.of( "GiaoDich" , "KenhGiaoDich", "LichSuGiaoDich_TKTT", "LoaiGiaoDich",
            "TaiKhoanThanhToan", "QuyDinhLaiSuat", "LoaiTaikKhoan" );

        ArrayList<QuyenHan> quyenHanThanhToan = new ArrayList<QuyenHan>();
        for (String module : thanhToanModules) {
            quyenHanThanhToan.addAll(quyenHanRepository.findAllByModule(module));
        }

        final ArrayList<String> tietKiemModules = new ArrayList<>(List.of("HinhThucDaoHan", "LoaiTietKiem", "TanSuatNhanLai", "LoaiKyHan"));
        tietKiemModules.addAll(thanhToanModules);

        ArrayList<QuyenHan> quyenHanTietKiem = new ArrayList<QuyenHan>();
        for (String module : tietKiemModules) {
            quyenHanTietKiem.addAll(quyenHanRepository.findAllByModule(module));
        }

        var vaiTros = vaiTroRepo.saveAll(List.of(
            new VaiTro(null, "KHONG_QUYEN_KHACH_HANG", true,
                "tài khoản không có quyền thực hiện điều gì", true,
                // Cai tiep theo la list quyen han
                List.of(), List.of(), List.of()),
            new VaiTro(null, "QUYEN_THANH_TOAN", true,
                "Chỉ có quyền thực hiện các giao dịch thanh toán", true,
                quyenHanThanhToan, List.of(), List.of()),
            new VaiTro(null, "QUYEN_TIET_KIEM", true,
                "Có quyền thanh toán và thực hiện các chức năng tiết kiệm", true,
                quyenHanTietKiem, List.of(), List.of()),
            new VaiTro(null, "QUAN_TRI_VIEN", false, "Có toàn quyền trong hệ thống", true,
                quyenHans, List.of(), List.of()),
            new VaiTro(null, "KHONG_QUYEN_NHAN_VIEN", false, "Không có quyền truy cập vào hệ thống",
                true,
                List.of(), List.of(), List.of()),
            new VaiTro(null, "NHAN_VIEN_GIAO_DICH", false, "Xử lý các giao dịch của khách hàng",
                true,
                quyenHanTietKiem, List.of(), List.of()),
            new VaiTro(null, "NHAN_VIEN_TIEP_THI", false, "Quản lý các sản phẩm và báo cáo", true,
                quyenHanTietKiem, List.of(), List.of())));

        var diaChis = diaChiRepo.saveAll(List.of(
            new DiaChi(null, "123", "Nguyễn Văn Cừ", "Phường 4", "Quận 5", "TP. Hồ Chí Minh"),
            new DiaChi(null, "45", "Trần Hưng Đạo", "Phường Phạm Ngũ Lão", "Quận 1", "TP. Hồ Chí Minh"),
            new DiaChi(null, "67", "Lê Duẩn", "Phường Bến Nghé", "Quận 1", "TP. Hồ Chí Minh"),
            new DiaChi(null, "89", "Đinh Tiên Hoàng", "Phường Đa Kao", "Quận 1", "TP. Hồ Chí Minh"),
            new DiaChi(null, "101", "Võ Thị Sáu", "Phường Tân Định", "Quận 1", "TP. Hồ Chí Minh"),
            new DiaChi(null, "55", "Nguyễn Thị Minh Khai", "Phường 5", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "77", "Cao Thắng", "Phường 5", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "99", "Nguyễn Thông", "Phường 9", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "111", "Trương Định", "Phường 6", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "222", "Lý Chính Thắng", "Phường 8", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "333", "Nguyễn Thiện Thuật", "Phường 2", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "444", "Trần Quốc Thảo", "Phường 10", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "555", "Võ Văn Tần", "Phường 5", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "666", "Bà Huyện Thanh Quan", "Phường 7", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "777", "Nam Kỳ Khởi Nghĩa", "Phường 7", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "888", "Nguyễn Đình Chiểu", "Phường 4", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "28", "Bùi Thị Xuân", "Phường 2", "Quận Tân Bình", "TP. Hồ Chí Minh"),
            new DiaChi(null, "17", "Phạm Ngọc Thạch", "Phường 6", "Quận 3", "TP. Hồ Chí Minh"),
            new DiaChi(null, "92", "Huỳnh Khương Ninh", "Phường Đakao", "Quận 1", "TP. Hồ Chí Minh"),
            new DiaChi(null, "115", "Hồ Xuân Hương", "Phường 6", "Quận 3", "TP. Hồ Chí Minh")

        ));


        hinhThucDaoHanRepo.saveAll(List.of(
            new HinhThucDaoHan(null, "Tất toán phiếu gửi tiền", 01,
                "Nhận toàn bộ tiền gốc và lãi khi đáo hạn"),
            new HinhThucDaoHan(null, "Tái tục gốc", 02,
                "Nhận lãi và tự động gửi lại tiền gốc"),
            new HinhThucDaoHan(null, "Tự động tái tục gốc và lãi", 03,
                "Tự động gửi lại cả gốc và lãi khi đáo hạn")));

        var loaiTietKiems = loaiTietKiemRepo.saveAll(List.of(
            new LoaiTietKiem(null, "Tiết kiệm có kỳ hạn", 01,
                "Phải gửi đủ thời gian mới được hưởng lãi suất tối đa",
                false, false, true),
            new LoaiTietKiem(null, "Tiết kiệm linh hoạt", 02,
                "Cho phép rút một phần tiền trước hạn",
                true, true, true)));

        var tanSuatNhanLais = tanSuatNhanLaiRepo.saveAll(List.of(
            new TanSuatNhanLai(null, "Hàng tháng", 01,
                "Nhận lãi định kỳ hàng tháng", true),
            new TanSuatNhanLai(null, "Hàng quý", 02,
                "Nhận lãi định kỳ mỗi quý", true),
            new TanSuatNhanLai(null, "Cuối kỳ hạn", 03,
                "Nhận lãi một lần khi đáo hạn", true),
            new TanSuatNhanLai(null, "Đầu kỳ hạn", 04,
                "Nhận lãi một lần khi đáo hạn", true)));

        var admin = nhanVienRepository.save(
            new NhanVien(
                null,
                "Nguyễn Văn A",
                LocalDate.of(2005, 6, 21),
                20,
                "12345678245",
                "0912345678",
                "admin@gmail.com",
                diaChis.get(0),
                diaChis.get(0),
                LocalDate.of(2025, 6, 21),
                passwordEncoder.encode("123456"),
                vaiTros.get(3),
                trangThais.get(10)));

        var loaiKyHans = loaiKyHanRepository.saveAll(
            IntStream.rangeClosed(1, 24)
                .mapToObj(value -> new LoaiKyHan(null, value + " Tháng", value))
                .collect(Collectors.toList()));

        var quyDinh1 = quyDinhLaiSuatRepository.save(
            new QuyDinhLaiSuat(null, LocalDate.now(), LocalDate.now(), "Blabla", admin, 0.1f,
                1_000_000, true, List.of()));

        chiTietQuyDinhLaiSuatRepository.saveAll(List.of(
            // Cuối kỳ hạn
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(2), loaiKyHans.get(0), 0.47f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(2), loaiKyHans.get(2), 0.44f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(2), loaiKyHans.get(5), 0.57f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(2), loaiKyHans.get(11), 0.6f),

            // Đầu kỳ hạn
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(3), loaiKyHans.get(0), 0.428f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(3), loaiKyHans.get(2), 0.443f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(3), loaiKyHans.get(5), 0.568f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(3), loaiKyHans.get(11), 0.557f),

            // Hàng tháng
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(0), loaiKyHans.get(0), 0.448f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(0), loaiKyHans.get(2), 0.581f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(0), loaiKyHans.get(5), 0.581f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(0), loaiKyHans.get(11), 0.57f),

            // Hàng quý
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(1), loaiKyHans.get(0), 0.585f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(1), loaiKyHans.get(2), 0.584f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(1), loaiKyHans.get(5), 0.573f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
                tanSuatNhanLais.get(1), loaiKyHans.get(11), 0.592f),

            // Hàng quý
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
                tanSuatNhanLais.get(2), loaiKyHans.get(0), 0.47f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
                tanSuatNhanLais.get(2), loaiKyHans.get(2), 0.44f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
                tanSuatNhanLais.get(2), loaiKyHans.get(5), 0.57f),
            new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
                tanSuatNhanLais.get(2), loaiKyHans.get(11), 0.6f)));

        SeedNhanVien(vaiTros.get(5), trangThais.get(2), diaChis);
        SeedKhachHang(vaiTros.get(2), trangThais.get(0), diaChis);
    }

    private List<NhanVienResponseDTO> SeedNhanVien(VaiTro vaiTro, TrangThai activeStatus, List<DiaChi> diaChis) {

        ArrayList<NhanVienResponseDTO> nhanVienResponseDTOs = new ArrayList<>();
        ArrayList<NhanVienRequestDTO> nhanViens = new ArrayList<>();

        // Employee 1
        nhanViens.add(new NhanVienRequestDTO(
            "Nguyễn Văn An",
            LocalDate.of(1990, 5, 15),
            "079090123456",
            "nguyenan@example.com",
            "0912345678",
            diaChis.get(0),
            diaChis.get(0),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 2
        nhanViens.add(new NhanVienRequestDTO(
            "Trần Thị Bình",
            LocalDate.of(1992, 8, 20),
            "079092123457",
            "tranbinh@example.com",
            "0923456789",
            diaChis.get(1),
            diaChis.get(1),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 3
        nhanViens.add(new NhanVienRequestDTO(
            "Lê Văn Công",
            LocalDate.of(1988, 4, 10),
            "079088123458",
            "lecong@example.com",
            "0934567890",
            diaChis.get(2),
            diaChis.get(2),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 4
        nhanViens.add(new NhanVienRequestDTO(
            "Phạm Thị Dung",
            LocalDate.of(1995, 11, 25),
            "079095123459",
            "phamdung@example.com",
            "0945678901",
            diaChis.get(3),
            diaChis.get(3),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 5
        nhanViens.add(new NhanVienRequestDTO(
            "Hoàng Văn Em",
            LocalDate.of(1991, 7, 15),
            "079091123460",
            "hoangem@example.com",
            "0956789012",
            diaChis.get(4),
            diaChis.get(4),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 6
        nhanViens.add(new NhanVienRequestDTO(
            "Võ Thị Phương",
            LocalDate.of(1993, 3, 8),
            "079093123461",
            "vophuong@example.com",
            "0967890123",
            diaChis.get(5),
            diaChis.get(5),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 7
        nhanViens.add(new NhanVienRequestDTO(
            "Đỗ Văn Giang",
            LocalDate.of(1987, 2, 18),
            "079087123462",
            "dogiang@example.com",
            "0978901234",
            diaChis.get(6),
            diaChis.get(6),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 8
        nhanViens.add(new NhanVienRequestDTO(
            "Nguyễn Thị Hương",
            LocalDate.of(1994, 9, 30),
            "079094123463",
            "nguyenhuong@example.com",
            "0989012345",
            diaChis.get(7),
            diaChis.get(7),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 9
        nhanViens.add(new NhanVienRequestDTO(
            "Trần Văn Khang",
            LocalDate.of(1989, 12, 5),
            "079089123464",
            "trankhang@example.com",
            "0990123456",
            diaChis.get(8),
            diaChis.get(8),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Employee 10
        nhanViens.add(new NhanVienRequestDTO(
            "Lê Thị Lan",
            LocalDate.of(1996, 6, 12),
            "079096123465",
            "lelan@example.com",
            "0901234567",
            diaChis.get(9),
            diaChis.get(9),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        for (NhanVienRequestDTO nhanVien : nhanViens) {
            NhanVienResponseDTO response = nhanVienService.createNhanVien(nhanVien);
            nhanVienResponseDTOs.add(response);
            nhanVienService.activateNhanVien(response.getNhanVienID());
        }
        return nhanVienResponseDTOs;
    }

    private ArrayList<KhachHangResponseDTO> SeedKhachHang(VaiTro vaiTro, TrangThai activeStatus, List<DiaChi> customerAddresses) {
        ArrayList<KhachHangResponseDTO> customersResponse = new ArrayList<>();
        ArrayList<KhachHangRequestDTO> customers = new ArrayList<>();
        customers.add(new KhachHangRequestDTO(
            "Trần Văn Minh",
            LocalDate.of(1985, 3, 12),
            "079085123456",
            "tranminh@example.com",
            "0901234567",
            customerAddresses.get(0),
            customerAddresses.get(0),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 2
        customers.add(new KhachHangRequestDTO(
            "Nguyễn Thị Hoa",
            LocalDate.of(1990, 7, 25),
            "079090234567",
            "nguyenhoa@example.com",
            "0912345678",
            customerAddresses.get(1),
            customerAddresses.get(1),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 3
        customers.add(new KhachHangRequestDTO(
            "Lê Hoàng Nam",
            LocalDate.of(1988, 11, 5),
            "079088345678",
            "lenam@example.com",
            "0923456789",
            customerAddresses.get(2),
            customerAddresses.get(2),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 4
        customers.add(new KhachHangRequestDTO(
            "Phạm Thị Mai",
            LocalDate.of(1992, 5, 18),
            "079092456789",
            "phammai@example.com",
            "0934567890",
            customerAddresses.get(3),
            customerAddresses.get(3),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 5
        customers.add(new KhachHangRequestDTO(
            "Hoàng Văn Tùng",
            LocalDate.of(1980, 9, 30),
            "079080567890",
            "hoangtung@example.com",
            "0945678901",
            customerAddresses.get(4),
            customerAddresses.get(4),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 6
        customers.add(new KhachHangRequestDTO(
            "Vũ Thị Lan",
            LocalDate.of(1995, 2, 14),
            "079095678901",
            "vulan@example.com",
            "0956789012",
            customerAddresses.get(5),
            customerAddresses.get(5),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 7
        customers.add(new KhachHangRequestDTO(
            "Đặng Văn Hùng",
            LocalDate.of(1987, 6, 8),
            "079087789012",
            "danghung@example.com",
            "0967890123",
            customerAddresses.get(6),
            customerAddresses.get(6),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 8
        customers.add(new KhachHangRequestDTO(
            "Trịnh Thị Thủy",
            LocalDate.of(1993, 10, 20),
            "079093890123",
            "trinhthuy@example.com",
            "0978901234",
            customerAddresses.get(7),
            customerAddresses.get(7),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 9
        customers.add(new KhachHangRequestDTO(
            "Bùi Văn Long",
            LocalDate.of(1984, 4, 15),
            "079084901234",
            "builong@example.com",
            "0989012345",
            customerAddresses.get(8),
            customerAddresses.get(8),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 10
        customers.add(new KhachHangRequestDTO(
            "Mai Thị Linh",
            LocalDate.of(1991, 8, 22),
            "079091012345",
            "mailinh@example.com",
            "0990123456",
            customerAddresses.get(9),
            customerAddresses.get(9),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 11
        customers.add(new KhachHangRequestDTO(
            "Dương Văn Thắng",
            LocalDate.of(1986, 12, 10),
            "079086123456",
            "duongthang@example.com",
            "0901234568",
            customerAddresses.get(10),
            customerAddresses.get(10),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 12
        customers.add(new KhachHangRequestDTO(
            "Ngô Thị Hương",
            LocalDate.of(1994, 3, 28),
            "079094234567",
            "ngohuong@example.com",
            "0912345679",
            customerAddresses.get(11),
            customerAddresses.get(11),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 13
        customers.add(new KhachHangRequestDTO(
            "Lý Văn Quang",
            LocalDate.of(1983, 7, 5),
            "079083345678",
            "lyquang@example.com",
            "0923456780",
            customerAddresses.get(12),
            customerAddresses.get(12),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 14
        customers.add(new KhachHangRequestDTO(
            "Hồ Thị Ngọc",
            LocalDate.of(1989, 11, 18),
            "079089456789",
            "hongoc@example.com",
            "0934567891",
            customerAddresses.get(13),
            customerAddresses.get(13),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 15
        customers.add(new KhachHangRequestDTO(
            "Tôn Văn Đức",
            LocalDate.of(1982, 5, 30),
            "079082567890",
            "tonduc@example.com",
            "0945678902",
            customerAddresses.get(14),
            customerAddresses.get(14),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 16
        customers.add(new KhachHangRequestDTO(
            "Lương Thị Hà",
            LocalDate.of(1996, 9, 14),
            "079096678901",
            "luongha@example.com",
            "0956789013",
            customerAddresses.get(15),
            customerAddresses.get(15),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 17
        customers.add(new KhachHangRequestDTO(
            "Đinh Văn Tú",
            LocalDate.of(1981, 2, 8),
            "079081789012",
            "dinhtu@example.com",
            "0967890124",
            customerAddresses.get(16),
            customerAddresses.get(16),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 18
        customers.add(new KhachHangRequestDTO(
            "Trương Thị Mỹ",
            LocalDate.of(1997, 6, 20),
            "079097890123",
            "truongmy@example.com",
            "0978901235",
            customerAddresses.get(17),
            customerAddresses.get(17),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 19
        customers.add(new KhachHangRequestDTO(
            "Hà Văn Dũng",
            LocalDate.of(1979, 10, 15),
            "079079901234",
            "hadung@example.com",
            "0989012346",
            customerAddresses.get(18),
            customerAddresses.get(18),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        // Customer 20
        customers.add(new KhachHangRequestDTO(
            "Cao Thị Thu",
            LocalDate.of(1998, 4, 22),
            "079098012345",
            "caothu@example.com",
            "0990123457",
            customerAddresses.get(19),
            customerAddresses.get(19),
            activeStatus.getTrangThaiID(),
            "123456",
            vaiTro.getId(),
            activeStatus.getTrangThaiID()
        ));

        for (KhachHangRequestDTO customer : customers) {
            KhachHangResponseDTO response = khachHangService.createKhachHang(customer);
            khachHangService.activateKhachHang(response.getKhachHangID());

            customersResponse.add(response);
        }
        return customersResponse;
    }
}
