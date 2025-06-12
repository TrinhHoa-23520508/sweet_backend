package com.example.sweet.services.GiaoDich;

import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.HinhThucDaoHanRepository;
import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.dto.PhieuGuiTienDTO;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.util.mapper.PhieuGuiTienMapper;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhieuGuiTienService {

    private final HinhThucDaoHanRepository hinhThucDaoHanRepository;

    private final LoaiKyHanRepository loaiKyHanRepository;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final PhieuGuiTienMapper phieuGuiTienMapper;

    public PhieuGuiTienService(
            PhieuGuiTienRepository phieuGuiTienRepository,
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            LoaiTietKiemRepository loaiTietKiemRepository,
            TanSuatNhanLaiRepository tanSuatNhanLaiRepository,
            LoaiKyHanRepository loaiKyHanRepository,
            HinhThucDaoHanRepository hinhThucDaoHanRepository,
            TrangThaiRepository trangThaiRepository,
            PhieuGuiTienMapper phieuGuiTienMapper) {
        this.phieuGuiTienRepository = phieuGuiTienRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.phieuGuiTienMapper = phieuGuiTienMapper;
        this.tanSuatNhanLaiRepository = tanSuatNhanLaiRepository;
        this.loaiKyHanRepository = loaiKyHanRepository;
        this.hinhThucDaoHanRepository = hinhThucDaoHanRepository;
        this.trangThaiRepository = trangThaiRepository;
    }

    @Transactional
    public PhieuGuiTienDTO createPhieuGuiTien(PhieuGuiTienDTO phieuGuiTienDTO) {
        try {
            // Chuyển đổi DTO thành Entity sử dụng mapper
            PhieuGuiTien phieuGuiTien = phieuGuiTienMapper.toEntity(phieuGuiTienDTO);

            // Lưu entity và chuyển đổi kết quả thành DTO
            return phieuGuiTienMapper.toDTO(phieuGuiTienRepository.save(phieuGuiTien));
        } catch (RuntimeException e) {
            throw new RuntimeException("Lỗi khi tạo phiếu gửi tiền: " + e.getMessage());
        }
    }

    public List<PhieuGuiTienDTO> getAllPhieuGuiTien() {
        Iterable<PhieuGuiTien> phieuGuiTiens = phieuGuiTienRepository.findAll();
        List<PhieuGuiTien> phieuGuiTienList = new ArrayList<>();
        phieuGuiTiens.forEach(phieuGuiTienList::add);

        return phieuGuiTienList.stream()
                .map(phieuGuiTienMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePhieuGuiTien(Integer id) {
        phieuGuiTienRepository.deleteById(id);
    }

    @Transactional
    public PhieuGuiTienDTO updatePhieuGuiTien(Integer id, PhieuGuiTienDTO phieuGuiTienDTO) {
        PhieuGuiTien existingPhieuGuiTien = phieuGuiTienRepository.findById(id).get();

        if (phieuGuiTienDTO.getKhachHangId() != null) {
            existingPhieuGuiTien.setKhachHang(khachHangRepository.findById(phieuGuiTienDTO.getKhachHangId()).get());
        }
        if (phieuGuiTienDTO.getGiaoDichId() != null) {
            existingPhieuGuiTien.setGiaoDichVien(nhanVienRepository.findById(phieuGuiTienDTO.getGiaoDichId()).get());
        }
        if (phieuGuiTienDTO.getLoaiTietKiemId() != null) {
            existingPhieuGuiTien
                    .setLoaiTietKiem(loaiTietKiemRepository.findById(phieuGuiTienDTO.getLoaiTietKiemId()).get());
        }

        existingPhieuGuiTien.setNgayGuiTien(phieuGuiTienDTO.getNgayGuiTien());
        existingPhieuGuiTien.setSoTienGuiBanDau(phieuGuiTienDTO.getSoTienGuiBanDau());
        existingPhieuGuiTien.setLaiSuatCamKet(phieuGuiTienDTO.getLaiSuatCamKet());
        existingPhieuGuiTien.setTenGoiNho(phieuGuiTienDTO.getTenGoiNho());

        return phieuGuiTienMapper.toDTO(phieuGuiTienRepository.save(existingPhieuGuiTien));
    }
}
