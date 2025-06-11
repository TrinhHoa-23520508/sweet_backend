package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.dto.PhieuGuiTienDTO;
import com.example.sweet.util.mapper.PhieuGuiTienMapper;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhieuGuiTienService {
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
            PhieuGuiTienMapper phieuGuiTienMapper) {
        this.phieuGuiTienRepository = phieuGuiTienRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTietKiemRepository = loaiTietKiemRepository;
        this.phieuGuiTienMapper = phieuGuiTienMapper;
    }

    @Transactional
    public PhieuGuiTienDTO createPhieuGuiTien(PhieuGuiTienDTO phieuGuiTienDTO) {
        KhachHang khachHang = khachHangRepository.findById(phieuGuiTienDTO.getKhachHangId()).get();
        NhanVien nhanVien = nhanVienRepository.findById(phieuGuiTienDTO.getGiaoDichVienId()).get();
        LoaiTietKiem loaiTietKiem = loaiTietKiemRepository.findById(phieuGuiTienDTO.getLoaiTietKiemId()).get();

        PhieuGuiTien phieuGuiTien = new PhieuGuiTien();
        phieuGuiTien.setKhachHang(khachHang);
        phieuGuiTien.setGiaoDichVien(nhanVien);
        phieuGuiTien.setLoaiTietKiem(loaiTietKiem);
        phieuGuiTien.setNgayGuiTien(phieuGuiTienDTO.getNgayGuiTien());
        phieuGuiTien.setSoTienGuiBanDau(phieuGuiTienDTO.getSoTienGuiBanDau());
        phieuGuiTien.setLaiSuatCamKet(phieuGuiTienDTO.getLaiSuatCamKet());
        phieuGuiTien.setTenGoiNho(phieuGuiTienDTO.getTenGoiNho());

        return phieuGuiTienMapper.toDTO(phieuGuiTienRepository.save(phieuGuiTien));
    }

    public List<PhieuGuiTienDTO> getAllPhieuGuiTien() {
        return phieuGuiTienRepository.findAll().stream()
                .map(PhieuGuiTienMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PhieuGuiTienDTO getPhieuGuiTienById(Integer id) {
        return phieuGuiTienMapper.toDTO(phieuGuiTienRepository.findById(id).get());
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
