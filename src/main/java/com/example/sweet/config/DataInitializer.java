package com.example.sweet.config;

import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.LoaiTrangThaiRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TaiKhoan.DiaChiRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.schema.TrangThai;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

        private LoaiTaiKhoanRepository ltkRespo;
        private KenhGiaoDichRepository kgdRespo;
        private LoaiGiaoDichRepository lgdRespo;
        private TrangThaiRepository trangThaiRespo;
        private LoaiTrangThaiRepository loaiTrangThaiRespo;
        private DiaChiRepository diaChiRespo;
        private VaiTroRepository vaiTroRespo;
        private final HinhThucDaoHanRepository hinhThucDaoHanRepo;
        private final LoaiTietKiemRepository loaiTietKiemRepo;
        private final TanSuatNhanLaiRepository tanSuatNhanLaiRepo;

        @Override
        public void run(String... args) throws Exception {
                if (ltkRespo.count() > 0)
                        return;
                ltkRespo.saveAll(List.of(
                                new LoaiTaiKhoan(null, 1, "Tài khoản thanh toán", "Tài khoản thanh toán"),
                                new LoaiTaiKhoan(null, 2, "Phiếu gửi tiền", "Phiếu gửi tiền"),
                                new LoaiTaiKhoan(null, 3, "Tiền mặt tại quầy", "Tiền mặt tại quầy"),
                                new LoaiTaiKhoan(null, 4, "Ngân hàng", "Ngân hàng")));

                kgdRespo.saveAll(List.of(
                                new KenhGiaoDich(null, 1, "Giao dịch tại quầy", "Giao dịch tại quầy"),
                                new KenhGiaoDich(null, 2, "Giao dịch trực tuyến", "Giao dịch trực tuyến")));

                lgdRespo.saveAll(List.of(
                                new LoaiGiaoDich(null, 1, "Gửi tiền vào tài khoản thanh toán",
                                                "Gửi tiền vào tài khoản thanh toán"),
                                new LoaiGiaoDich(null, 2, "Rút tiền từ tài khoản thanh toán",
                                                "Rút tiền từ tài khoản thanh toán"),
                                new LoaiGiaoDich(null, 3, "Gửi tiền vào phiếu gửi tiền", "Gửi tiền vào phiếu gửi tiền"),
                                new LoaiGiaoDich(null, 4, "Rút tiền từ phiếu gửi tiền", "Rút tiền từ phiếu gửi tiền"),
                                new LoaiGiaoDich(null, 5, "Tất toán phiếu gửi tiền", "Tất toán phiếu gửi tiền"),
                                new LoaiGiaoDich(null, 6, "Trả tiền lãi", "Trả tiền lãi"),
                                new LoaiGiaoDich(null, 7, "Đáo hạn phiếu gửi tiền", "Đáo hạn phiếu gửi tiền")));

                var insertedLTT = loaiTrangThaiRespo.saveAll(List.of(
                                new LoaiTrangThai(null, "customer", "Khách hàng", "Khách hàng"),
                                new LoaiTrangThai(null, "employee", "Nhân viên", "Nhân viên"),
                                new LoaiTrangThai(null, "payment_account", "Tài khoản thanh toán",
                                                "Tài khoản thanh toán"),
                                new LoaiTrangThai(null, "transaction", "Giao dịch", "Giao dịch"),
                                new LoaiTrangThai(null, "deposit_receipt", "Phiếu gửi tiền", "Phiếu gửi tiền"),
                                new LoaiTrangThai(null, "login_account", "Tài khoản đăng nhập", "Tài khoản đăng nhập")))
                                .iterator();
                ArrayList<LoaiTrangThai> loaiTrangThais = new ArrayList<>();
                while (insertedLTT.hasNext()) {
                        loaiTrangThais.add(insertedLTT.next());
                }

                trangThaiRespo.saveAll(List.of(
                                // Trạng thái khách hàng
                                new TrangThai(null, "active", "Còn hoạt động", loaiTrangThais.get(0)),
                                new TrangThai(null, "locked", "Đã hủy", loaiTrangThais.get(0)),

                                // Trạng thái nhân viên
                                new TrangThai(null, "active", "Còn hoạt động", loaiTrangThais.get(1)),
                                new TrangThai(null, "locked", "Đã hủy", loaiTrangThais.get(1)),

                                // Trạng thái tài khoản thanh toán
                                new TrangThai(null, "active", "Còn hoạt động", loaiTrangThais.get(2)),
                                new TrangThai(null, "locked", "Đã khóa", loaiTrangThais.get(2)),

                                // Trạng thái giao dịch
                                new TrangThai(null, "success", "Giao dịch thành công", loaiTrangThais.get(3)),
                                new TrangThai(null, "failed", "Giao dịch thất bại", loaiTrangThais.get(3)),

                                // Trạng thái phiếu gửi tiền
                                new TrangThai(null, "settled", "Đã tất toán", loaiTrangThais.get(4)),
                                new TrangThai(null, "unsettled", "Chưa tất toán", loaiTrangThais.get(4)),

                                // Trạng thái tài khoản đăng nhập
                                new TrangThai(null, "active", "Đang hoạt động", loaiTrangThais.get(5)),
                                new TrangThai(null, "locked", "Đã khóa", loaiTrangThais.get(5))));

                vaiTroRespo.save(new VaiTro(null, "Foo", "fOO", true, List.of()));
                diaChiRespo.save(new DiaChi(null, 1, "foo", "foo", "foo", "foo"));

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

                tanSuatNhanLaiRepo.saveAll(List.of(
                                new TanSuatNhanLai(null, "Hàng tháng", 01,
                                                "Nhận lãi định kỳ hàng tháng", true),
                                new TanSuatNhanLai(null, "Hàng quý", 02,
                                                "Nhận lãi định kỳ mỗi quý", true),
                                new TanSuatNhanLai(null, "Cuối kỳ hạn", 03,
                                                "Nhận lãi một lần khi đáo hạn", true),
                                new TanSuatNhanLai(null, "Đầu kỳ hạn", 04,
                                                "Nhận lãi một lần khi đáo hạn", true)));
        }
}

/*
 * Hào: Data mẫu để tui test đừng xóa làm ơn
 * INSERT INTO `sweet`.`khach_hang` (`khach_hangid`, `cccd`, `email`, `ho_ten`,
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
