package com.example.sweet.util.mapper;

import org.springframework.stereotype.Component;

import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;
import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;

@Component
public class PhieuTraLaiMapper {
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final GiaoDichRepository giaoDichRepository;

    public PhieuTraLaiMapper(
            PhieuGuiTienRepository phieuGuiTienRepository,
            GiaoDichRepository giaoDichRepository) {
        this.phieuGuiTienRepository = phieuGuiTienRepository;
        this.giaoDichRepository = giaoDichRepository;
    }

    public PhieuTraLaiDTO toDTO(PhieuTraLai phieuTraLai) {
        if (phieuTraLai == null) {
            return null;
        }

        PhieuTraLaiDTO dto = new PhieuTraLaiDTO();

        dto.setPhieuTraLaiID(phieuTraLai.getPhieuTraLaiID());
        dto.setPhieuGuiTienID(
                phieuTraLai.getPhieuGuiTien() != null ? phieuTraLai.getPhieuGuiTien().getPhieuGuiTienID() : null);
        dto.setGiaoDichID(phieuTraLai.getGiaoDich() != null ? phieuTraLai.getGiaoDich().getGiaoDichID() : null);
        dto.setNgayTraLai(phieuTraLai.getNgayTraLai());

        // Map thêm thông tin từ PhieuGuiTien
        if (phieuTraLai.getPhieuGuiTien() != null) {
            PhieuGuiTien pgt = phieuTraLai.getPhieuGuiTien();
            dto.setMaPhieuGuiTien(pgt.getPhieuGuiTienID());
            dto.setNgayGuiTien(pgt.getNgayGuiTien());
            dto.setTienLaiNhanDinhKy(pgt.getTienLaiNhanDinhKy());
            dto.setTienLaiDaNhanNhungChuaQuyetToan(pgt.getTienLaiDaNhanNhungChuaQuyetToan());
            dto.setNgayDaoHan(pgt.getNgayDaoHan());

            // Map thông tin từ KhachHang
            if (pgt.getKhachHang() != null) {
                dto.setMaKhachHang(pgt.getKhachHang().getKhachHangID().toString());
                dto.setHoTen(pgt.getKhachHang().getHoTen());
                dto.setCccd(pgt.getKhachHang().getCccd());
            }

            // Map thông tin từ LoaiTietKiem
            if (pgt.getLoaiTietKiem() != null) {
                dto.setLoaiTietKiem(pgt.getLoaiTietKiem().getTenLoaiTietKiem());
            }

            // Map thông tin từ TanSuatNhanLai
            if (pgt.getTanSuatNhanLai() != null) {
                dto.setTanSuatTraLai(pgt.getTanSuatNhanLai().getTenTanSoNhanLai());
            }
        }

        // Map thông tin từ GiaoDich
        if (phieuTraLai.getGiaoDich() != null) {
            dto.setSoTienNhanDuocKhiTraLai(phieuTraLai.getPhieuGuiTien().getTienLaiNhanDinhKy());
        }

        return dto;
    }

    public PhieuTraLai toEntity(PhieuTraLaiDTO dto) {
        if (dto == null) {
            return null;
        }

        PhieuTraLai entity = new PhieuTraLai();

        if (dto.getPhieuTraLaiID() != null) {
            entity.setPhieuTraLaiID(dto.getPhieuTraLaiID());
        }

        if (dto.getPhieuGuiTienID() != null) {
            PhieuGuiTien phieuGuiTien = phieuGuiTienRepository.findById(dto.getPhieuGuiTienID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền"));
            entity.setPhieuGuiTien(phieuGuiTien);
        }

        if (dto.getGiaoDichID() != null) {
            GiaoDich giaoDich = giaoDichRepository.findById(dto.getGiaoDichID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch"));
            entity.setGiaoDich(giaoDich);
        }

        entity.setNgayTraLai(dto.getNgayTraLai());

        return entity;
    }
}