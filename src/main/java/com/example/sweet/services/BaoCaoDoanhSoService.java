package com.example.sweet.services;

import com.example.sweet.database.repository.BaoCaoDoanhSoRepository;
import com.example.sweet.database.repository.ChiTietBaoCaoDoanhSoRepository;
import com.example.sweet.database.schema.BaoCaoDoanhSo;
import com.example.sweet.database.schema.ChiTietBaoCaoDoanhSo;
import com.example.sweet.util.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaoCaoDoanhSoService {

    private final BaoCaoDoanhSoRepository baoCaoDoanhSoRepository;
    private final ChiTietBaoCaoDoanhSoRepository chiTietBaoCaoDoanhSoRepository;

    public BaoCaoDoanhSoService(BaoCaoDoanhSoRepository baoCaoDoanhSoRepository,
                                ChiTietBaoCaoDoanhSoRepository chiTietBaoCaoDoanhSoRepository) {
        this.baoCaoDoanhSoRepository = baoCaoDoanhSoRepository;
        this.chiTietBaoCaoDoanhSoRepository = chiTietBaoCaoDoanhSoRepository;
    }

    public List<BaoCaoDoanhSo> getAllBaoCaoDoanhSo() {
        return this.baoCaoDoanhSoRepository.findAll();
    }

    public List<ChiTietBaoCaoDoanhSo> getAllChiTietBaoCaoByBaoCao(Long baoCaoID) {
        BaoCaoDoanhSo baoCaoDoanhSo = this.baoCaoDoanhSoRepository.findById(baoCaoID).orElseThrow(
                () -> new NotFoundException("Báo cáo doanh số không tồn tại!")
        );

        return this.chiTietBaoCaoDoanhSoRepository.findAllByBaoCaoDoanhSo(baoCaoDoanhSo);


    }
}
