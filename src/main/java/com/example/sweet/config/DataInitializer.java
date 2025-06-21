 package com.example.sweet.config;

 import com.example.sweet.database.repository.Loai.*;
 import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
 import com.example.sweet.database.repository.ThamSoRepository;
 import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
 import com.example.sweet.database.schema.Loai.*;
 import com.example.sweet.database.schema.TaiKhoan.DiaChi;
 import com.example.sweet.database.schema.TaiKhoan.NhanVien;
 import com.example.sweet.database.schema.TaiKhoan.VaiTro;
 import com.example.sweet.database.repository.TrangThaiRepository;
 import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
 import com.example.sweet.database.repository.TaiKhoan.DiaChiRepository;
 import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
 import com.example.sweet.database.schema.ThamSo;
 import com.example.sweet.database.schema.TrangThai;
 import com.example.sweet.util.constant.KenhGiaoDichEnum;
 import com.example.sweet.util.constant.LoaiTKDichEnum;
 import com.example.sweet.util.constant.SystemParameterEnum;
 import lombok.AllArgsConstructor;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.stereotype.Component;

 import java.time.LocalDate;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
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
     private final ChiTietQuyDinhLaiSuatRepository
     chiTietQuyDinhLaiSuatRepository;
     private final ThamSoRepository thamSoRepository;

     @Override
     public void run(String... args) throws Exception {
         /*
         if (loaiTaiKhoanRepository.count() > 0)
            return;
         thamSoRepository.saveAll(
                 Arrays
                 .stream(SystemParameterEnum.values())
                 .map(value ->
                         new ThamSo(null, value.name(), value.name(), value.name(), "1"))
                 .toList());
         loaiTaiKhoanRepository.saveAll(
             Arrays
                 .stream(LoaiTKDichEnum.values())
                 .map(value ->
                         new LoaiTaiKhoan(null, value.ordinal(), value.getLabel(), value.getLabel()))
                 .toList());
//         loaiTaiKhoanRepository.saveAll(List.of(
//             new LoaiTaiKhoan(null, 1, "Tài khoản thanh toán", "Tài khoản thanh toán"),
//             new LoaiTaiKhoan(null, 2, "Phiếu gửi tiền", "Phiếu gửi tiền"),
//             new LoaiTaiKhoan(null, 3, "Tiền mặt tại quầy", "Tiền mặt tại quầy"),
//             new LoaiTaiKhoan(null, 4, "Ngân hàng", "Ngân hàng")));

         kenhGiaoDichRepository.saveAll(
             Arrays
                 .stream(KenhGiaoDichEnum.values())
                 .map(value ->
                         new KenhGiaoDich(null, (int) value.getCode(), value.getLabel(), value.getLabel()))
                 .toList()
         );
//         kenhGiaoDichRepository.saveAll(List.of(
//             new KenhGiaoDich(null, 1, "Giao dịch tại quầy", "Giao dịch tại quầy"),
//             new KenhGiaoDich(null, 2, "Giao dịch trực tuyến", "Giao dịch trực tuyến")));

         loaiGiaoDichRepository.saveAll(
            Arrays
                .stream(LoaiTKDichEnum.values())
                .map(value ->
                        new LoaiGiaoDich(null, (int) value.getCode(), value.getLabel(), value.getLabel()))
                .toList()
         );
//         loaiGiaoDichRepository.saveAll(List.of(
//             new LoaiGiaoDich(null, 1, "Gửi tiền vào tài khoản thanh toán",
//             "Gửi tiền vào tài khoản thanh toán"),
//             new LoaiGiaoDich(null, 2, "Rút tiền từ tài khoản thanh toán",
//             "Rút tiền từ tài khoản thanh toán"),
//             new LoaiGiaoDich(null, 3, "Gửi tiền vào phiếu gửi tiền", "Gửi tiền vào phiếu gửi tiền"),
//             new LoaiGiaoDich(null, 4, "Rút tiền từ phiếu gửi tiền", "Rút tiền từ phiếu gửi tiền"),
//             new LoaiGiaoDich(null, 5, "Tất toán phiếu gửi tiền", "Tất toán phiếu gửi tiền"),
//             new LoaiGiaoDich(null, 6, "Trả tiền lãi", "Trả tiền lãi"),
//             new LoaiGiaoDich(null, 7, "Đáo hạn phiếu gửi tiền", "Đáo hạn phiếu gửi tiền")));

         var insertedLTT = loaiTrangThaiRepo.saveAll(List.of(
             new LoaiTrangThai(null, "customer", "Khách hàng", "Khách hàng", false, List.of()),
             new LoaiTrangThai(null, "employee", "Nhân viên", "Nhân viên", false, List.of()),
             new LoaiTrangThai(null, "payment_account", "Tài khoản thanh toán", "Tài khoản thanh toán", false, List.of()),
             new LoaiTrangThai(null, "transaction", "Giao dịch", "Giao dịch", false, List.of()),
             new LoaiTrangThai(null, "deposit_receipt", "Phiếu gửi tiền", "Phiếu gửi tiền", false, List.of()),
             new LoaiTrangThai(null, "login_account", "Tài khoản đăng nhập", "Tài khoản đăng nhập", false, List.of())))
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

         var vaiTro = vaiTroRepo.save(new VaiTro(null, "Foo", "fOO", true, List.of(), List.of(), List.of()));
         var diaChi = diaChiRepo.save(new DiaChi(null, "1", "foo", "foo", "foo","foo"));

         var admin = nhanVienRepository.save(
             new NhanVien(null, "123", LocalDate.now(), 16, "12345678", "1234567", "foo@bar.com", diaChi, diaChi, LocalDate.now(), "123456789", vaiTro, trangThais.get(2))
         );

         Long temp = null;
         hinhThucDaoHanRepo.saveAll(List.of(
             new HinhThucDaoHan(temp, "Tất toán phiếu gửi tiền", 01,
             "Nhận toàn bộ tiền gốc và lãi khi đáo hạn"),
             new HinhThucDaoHan(null, "Tái tục gốc", 02,
             "Nhận lãi và tự động gửi lại tiền gốc"),
             new HinhThucDaoHan(null, "Tự động tái tục gốc và lãi", 03,
             "Tự động gửi lại cả gốc và lãi khi đáo hạn")));

         loaiTietKiemRepo.saveAll(List.of(
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

         var loaiKyHans = loaiKyHanRepository.saveAll(
             IntStream.rangeClosed(1, 24)
             .mapToObj(value -> new LoaiKyHan(null, value + " Tháng", value))
             .collect(Collectors.toList())
         );

         var loaiTietKiems = loaiTietKiemRepository.saveAll(List.of(
             new LoaiTietKiem(null, "Tiết kiệm có kỳ hạn", 1,
             "Tiết kiệm có kỳ hạn", true, true, true),
             new LoaiTietKiem(null, "Tiết kiệm có kỳ hạn rút gốc linh hoạt", 1,
             "Tiết kiệm có kỳ hạn rút gốc linh hoạt", true, true, true)
         ));

         var quyDinh1 = quyDinhLaiSuatRepository.save(
             new QuyDinhLaiSuat(null, LocalDate.now(), LocalDate.now(), "Blabla", admin, 0.1f, Integer.MAX_VALUE, List.of())
         );

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
             tanSuatNhanLais.get(0), loaiKyHans.get(2), 0.448f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(0), loaiKyHans.get(5), 0.581f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(0), loaiKyHans.get(11), 0.581f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(0), loaiKyHans.get(17), 0.57f),

             // Hàng quý
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(1), loaiKyHans.get(5), 0.585f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(1), loaiKyHans.get(11), 0.584f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(1), loaiKyHans.get(17), 0.573f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(0),
             tanSuatNhanLais.get(1), loaiKyHans.get(23), 0.592f),

             // Hàng quý
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
             null, loaiKyHans.get(0), 0.47f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
             null, loaiKyHans.get(2), 0.44f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
             null, loaiKyHans.get(5), 0.57f),
             new ChiTietQuyDinhLaiSuat(null, quyDinh1, loaiTietKiems.get(1),
             null, loaiKyHans.get(11), 0.6f)
         ));
         */
     }
 }

 /*
 * Hào: Data mẫu để tui test đừng xóa làm ơn
 * INSERT INTO `sweet`.`khach_hang` (`khach_hangid`, `cccd`, `email`,
 `ho_ten`,
 * `mat_khau`, `ngay_dang_ky`,
 * `ngay_sinh`, `ten_dang_nhap`, `dia_chi_lien_lac`, `dia_chi_thuong_tru`,
 * `trang_thai_tai_khoan`, `vai_tro`)
 * VALUES ('2', '123', 'jojijik', 'fawfawfaw', '123456', '2022-01-01',
 * '2022-01-01', 'blabla', '1', '1', '1', '1');
 *
 * INSERT INTO `sweet`.`tai_khoan_thanh_toan` (`so_tai_khoan`, `ngay_tao`,
 * `so_du`, `khach_hang`, `trang_thai`)
 * VALUES ('1', '2022-01-01', '900000', '1', '1');
 *
 * INSERT INTO `sweet`.`tai_khoan_thanh_toan` (`so_tai_khoan`, `ngay_tao`,
 * `so_du`, `khach_hang`, `trang_thai`)
 * VALUES ('2', '2022-06-01', '100000', '1', '1');
 *
 * INSERT INTO `sweet`.`nhan_vien` (`nhan_vienid`, `cccd`, `email`, `ho_ten`,
 * `mat_khau`, `ngay_tuyen_dung`,
 * `ngay_sinh`, `ten_dang_nhap`, `dia_chi_lien_lac`, `dia_chi_thuong_tru`,
 * `trang_thai_tai_khoan`, `vai_tro`)
 * VALUES ('1', '123', 'jojijik', 'fawfawfaw', '123456', '2022-01-01',
 * '2022-01-01', 'blabla', '1', '1', '1', '1');
 */
