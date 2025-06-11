package com.example.sweet.config;


import com.example.sweet.database.respository.GiaoDich.KenhGiaoDichRespository;
import com.example.sweet.database.respository.Loai.LoaiGiaoDichRespository;
import com.example.sweet.database.respository.Loai.LoaiTaiKhoanRespository;
import com.example.sweet.database.respository.Loai.LoaiTrangThaiRespository;
import com.example.sweet.database.respository.TaiKhoan.DiaChiRespository;
import com.example.sweet.database.respository.TaiKhoan.VaiTroRespository;
import com.example.sweet.database.respository.TrangThaiRespository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.database.schema.TrangThai;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private LoaiTaiKhoanRespository ltkRespo;
    private KenhGiaoDichRespository kgdRespo;
    private LoaiGiaoDichRespository lgdRespo;
    private TrangThaiRespository trangThaiRespo;
    private LoaiTrangThaiRespository loaiTrangThaiRespo;
    private DiaChiRespository diaChiRespo;
    private VaiTroRespository vaiTroRespo;

    @Override
    public void run(String... args) throws Exception {
        if (ltkRespo.count() > 0)
            return;
        ltkRespo.saveAll(List.of(
            new LoaiTaiKhoan(0, 1, "Tài khoản thanh toán", "Tài khoản thanh toán"),
            new LoaiTaiKhoan(0, 2, "Phiếu gửi tiền", "Phiếu gửi tiền"),
            new LoaiTaiKhoan(0, 3, "Tiền mặt tại quầy", "Tiền mặt tại quầy"),
            new LoaiTaiKhoan(0, 4, "Ngân hàng", "Ngân hàng")
        ));

        kgdRespo.saveAll(List.of(
            new KenhGiaoDich(0, 1, "Giao dịch tại quầy", "Giao dịch tại quầy"),
            new KenhGiaoDich(0, 2, "Giao dịch trực tuyến", "Giao dịch trực tuyến")
        ));

        lgdRespo.saveAll(List.of(
            new LoaiGiaoDich(0, 1, "Gửi tiền vào tài khoản thanh toán", "Gửi tiền vào tài khoản thanh toán"),
            new LoaiGiaoDich(0, 2, "Rút tiền từ tài khoản thanh toán", "Rút tiền từ tài khoản thanh toán"),
            new LoaiGiaoDich(0, 3, "Gửi tiền vào phiếu gửi tiền", "Gửi tiền vào phiếu gửi tiền"),
            new LoaiGiaoDich(0, 4, "Rút tiền từ phiếu gửi tiền", "Rút tiền từ phiếu gửi tiền"),
            new LoaiGiaoDich(0, 5, "Tất toán phiếu gửi tiền", "Tất toán phiếu gửi tiền"),
            new LoaiGiaoDich(0, 6, "Trả tiền lãi", "Trả tiền lãi"),
            new LoaiGiaoDich(0, 7, "Đáo hạn phiếu gửi tiền", "Đáo hạn phiếu gửi tiền")
        ));

        var loaiTrangThai = loaiTrangThaiRespo.save(new LoaiTrangThai(0, 1, "Foo", "foo"));

        trangThaiRespo.saveAll(List.of(
            new TrangThai(0, 1, "Đã tất toán", loaiTrangThai),
            new TrangThai(0, 2, "Chưa tất toán", loaiTrangThai),
            new TrangThai(0, 3, "Thành công", loaiTrangThai),
            new TrangThai(0, 4, "Thất bại", loaiTrangThai),
            new TrangThai(0, 5, "Còn hoạt động", loaiTrangThai),
            new TrangThai(0, 6, "Đã hủy", loaiTrangThai)
        ));

        vaiTroRespo.save(new VaiTro(0L, "Foo", "fOO", true, List.of()));
        diaChiRespo.save(new DiaChi(0, 1, "foo", "foo", "foo", "foo"));
        /*
        Hào: Data mẫu để tui test đừng xóa làm ơn
        INSERT INTO `sweet`.`khach_hang` (`khach_hangid`, `cccd`, `email`, `ho_ten`, `mat_khau`, `ngay_dang_ky`,
            `ngay_sinh`, `ten_dang_nhap`, `dia_chi_lien_lac`, `dia_chi_thuong_tru`, `trang_thai_tai_khoan`, `vai_tro`)
            VALUES ('2', '123', 'jojijik', 'fawfawfaw', '123456', '2022-01-01', '2022-01-01', 'blabla', '1', '1', '1', '1');

        INSERT INTO `sweet`.`tai_khoan_thanh_toan` (`so_tai_khoan`, `ngay_tao`, `so_du`, `khach_hang`, `trang_thai`)
            VALUES ('1', '2022-01-01', '900000', '1', '1');

        INSERT INTO `sweet`.`tai_khoan_thanh_toan` (`so_tai_khoan`, `ngay_tao`, `so_du`, `khach_hang`, `trang_thai`)
            VALUES ('2', '2022-06-01', '100000', '1', '1');

        INSERT INTO `sweet`.`nhan_vien` (`nhan_vienid`, `cccd`, `email`, `ho_ten`, `mat_khau`, `ngay_tuyen_dung`,
            `ngay_sinh`, `ten_dang_nhap`, `dia_chi_lien_lac`, `dia_chi_thuong_tru`, `trang_thai_tai_khoan`, `vai_tro`)
            VALUES ('1', '123', 'jojijik', 'fawfawfaw', '123456', '2022-01-01', '2022-01-01', 'blabla', '1', '1', '1', '1');
         */

    }
}
