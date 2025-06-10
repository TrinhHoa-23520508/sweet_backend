package com.example.sweet.config;


import com.example.sweet.database.respository.GiaoDich.KenhGiaoDichRespository;
import com.example.sweet.database.respository.Loai.LoaiGiaoDichRespository;
import com.example.sweet.database.respository.Loai.LoaiTaiKhoanRespository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private LoaiTaiKhoanRespository ltkRespo;
    private KenhGiaoDichRespository kgdRespo;
    private LoaiGiaoDichRespository lgdRespos;

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

        lgdRespos.saveAll(List.of(
            new LoaiGiaoDich(0, 1, "Gửi tiền vào tài khoản thanh toán", "Gửi tiền vào tài khoản thanh toán"),
            new LoaiGiaoDich(0, 2, "Rút tiền từ tài khoản thanh toán", "Rút tiền từ tài khoản thanh toán"),
            new LoaiGiaoDich(0, 3, "Gửi tiền vào phiếu gửi tiền", "Gửi tiền vào phiếu gửi tiền"),
            new LoaiGiaoDich(0, 4, "Rút tiền từ phiếu gửi tiền", "Rút tiền từ phiếu gửi tiền"),
            new LoaiGiaoDich(0, 5, "Tất toán phiếu gửi tiền", "Tất toán phiếu gửi tiền"),
            new LoaiGiaoDich(0, 6, "Trả tiền lãi", "Trả tiền lãi"),
            new LoaiGiaoDich(0, 7, "Đáo hạn phiếu gửi tiền", "Đáo hạn phiếu gửi tiền")
        ));
    }
}
