package com.example.sweet.util.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.dto.PhieuDaoHanDTO_inp;
import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;

@Component
public class PhieuDaoHanMapper {

    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepository;
    private final LoaiKyHanRepository loaiKyHanRepository;
    private final HinhThucDaoHanRepository hinhThucDaoHanRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final GiaoDichRepository giaoDichRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;

    @Autowired
    public PhieuDaoHanMapper(
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            LoaiTietKiemRepository loaiTietKiemRepository,
            TanSuatNhanLaiRepository tanSuatNhanLaiRepository,
            LoaiKyHanRepository loaiKyHanRepository,
            HinhThucDaoHanRepository hinhThucDaoHanRepository,
            TrangThaiRepository trangThaiRepository,
            GiaoDichRepository giaoDichRepository,
            PhieuGuiTienRepository phieuGuiTienRepository) {
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.tanSuatNhanLaiRepository = tanSuatNhanLaiRepository;
        this.loaiKyHanRepository = loaiKyHanRepository;
        this.hinhThucDaoHanRepository = hinhThucDaoHanRepository;
        this.trangThaiRepository = trangThaiRepository;
        this.giaoDichRepository = giaoDichRepository;
        this.phieuGuiTienRepository = phieuGuiTienRepository;
    }

    public PhieuDaoHan toEntity(PhieuDaoHanDTO_inp dto) {
        PhieuDaoHan entity = new PhieuDaoHan();
        entity.setNgayDaoHan(LocalDateTime.now());
        PhieuGuiTien phieuGuiTienKyTruoc = phieuGuiTienRepository.findById(dto.getMaPhieuGuiTien())
                .orElseThrow(() -> new IllegalArgumentException("PhieuGuiTienKyTruoc not found"));
        entity.setPhieuGuiTienKyTruoc(phieuGuiTienKyTruoc);
        entity.setPhieuGuiTienTiepTheo(null); // Assuming this will be set later
        entity.setNgayDaoHan(LocalDateTime.now()); // Set the current date and time
        // Set other fields if necessary, e.g., entity.setSomeField(dto.getSomeField());

        return entity;
    }
}