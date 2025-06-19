package com.example.sweet.services.GiaoDich;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuDaoHanRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.dto.PhieuDaoHanDTO;
import com.example.sweet.database.repository.dto.PhieuDaoHanDTO_inp;
import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.util.mapper.PhieuDaoHanMapper;
import com.example.sweet.util.mapper.PhieuGuiTienMapper;

@Service
public class PhieuDaoHanService {
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

    public PhieuDaoHanService(
            PhieuDaoHanRepository phieuDaoHanRepository,
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            LoaiTietKiemRepository loaiTietKiemRepository,
            TanSuatNhanLaiRepository tanSuatNhanLaiRepository,
            LoaiKyHanRepository loaiKyHanRepository,
            HinhThucDaoHanRepository hinhThucDaoHanRepository,
            TrangThaiRepository trangThaiRepository,
            PhieuGuiTienMapper phieuGuiTienMapper,
            PhieuGuiTienRepository phieuGuiTienRepository,
            PhieuDaoHanMapper phieuDaoHanMapper) {

        this.trangThaiRepository = null;
        this.phieuGuiTienRepository = phieuGuiTienRepository;
        this.phieuDaoHanRepository = phieuDaoHanRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.phieuGuiTienMapper = phieuGuiTienMapper;
        this.tanSuatNhanLaiRepository = tanSuatNhanLaiRepository;
        this.loaiKyHanRepository = loaiKyHanRepository;
        this.hinhThucDaoHanRepository = hinhThucDaoHanRepository;
        this.phieuDaoHanMapper = phieuDaoHanMapper;
    }

    @Transactional
    public List<PhieuDaoHan> handleGetAllPhieuDaoHan() {
        List<PhieuDaoHan> phieuDaoHans = (List<PhieuDaoHan>) this.phieuDaoHanRepository.findAll();
        if (phieuDaoHans.isEmpty()) {
            throw new IllegalArgumentException("No PhieuDaoHan found");
        }
        return phieuDaoHans;
    }

    @Transactional
    /**
     * Handles the creation of a PhieuDaoHan (Maturity Receipt).
     * This method maps the input DTO to an entity and saves it to the database.
     */
    public PhieuDaoHanDTO handleCreatePhieuDaoHan(PhieuDaoHanDTO_inp phieuDaoHanDTO_inp) {
        if (phieuDaoHanRepository.existsByPhieuGuiTienKyTruoc_PhieuGuiTienID(phieuDaoHanDTO_inp.getMaPhieuGuiTien())) {
            throw new IllegalArgumentException("PhieuDaoHan with this ID already exists");
        }
        PhieuDaoHan phieuDaoHan = phieuDaoHanMapper.toEntity(phieuDaoHanDTO_inp);
        if (phieuDaoHan != null) {
            phieuDaoHanRepository.save(phieuDaoHan);
        }

        PhieuGuiTien phieuGuiTienKyTruoc = phieuDaoHan.getPhieuGuiTienKyTruoc();

        PhieuDaoHanDTO phieuDaoHanDTO = new PhieuDaoHanDTO(phieuDaoHan);

        PhieuGuiTien phieuGuiTienKySau = phieuDaoHanDTO.taoPhieuGuiTienKiSau(phieuGuiTienKyTruoc);
        phieuGuiTienKySau = this.phieuGuiTienRepository.saveAndFlush(phieuGuiTienKySau);

        {
            KhachHang khachHang = this.khachHangRepository.findById(phieuDaoHanDTO_inp.getMaKH())
                    .orElseThrow(() -> new IllegalArgumentException("KhachHang not found"));
            phieuDaoHanDTO.setMaPhieuDaoHan(phieuDaoHan.getPhieuDaoHanID());
            phieuDaoHanDTO.setMaKhachHang(phieuDaoHanDTO_inp.getMaKH());
            phieuDaoHanDTO.setCccdKhachHang(khachHang.getCccd());
            phieuDaoHanDTO.setHoTenKhachHang(khachHang.getHoTen());
            phieuDaoHanDTO.setMaPhieuGuiTien(phieuDaoHanDTO_inp.getMaPhieuGuiTien());
        }

        phieuDaoHanDTO.tinhToan(phieuGuiTienKyTruoc, phieuGuiTienKySau);
        phieuGuiTienRepository.save(phieuGuiTienKyTruoc);
        phieuDaoHanRepository.save(phieuDaoHan);
        phieuGuiTienRepository.save(phieuGuiTienKySau);
        return phieuDaoHanDTO;
    }

}
