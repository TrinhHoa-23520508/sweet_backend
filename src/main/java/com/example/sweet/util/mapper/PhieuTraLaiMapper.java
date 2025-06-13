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
        dto.setPhieuGuiTTienID(
                phieuTraLai.getPhieuGuiTien() != null ? phieuTraLai.getPhieuGuiTien().getPhieuGuiTienID() : null);
        dto.setGiaoDichID(phieuTraLai.getGiaoDich() != null ? phieuTraLai.getGiaoDich().getGiaoDichID() : null);
        dto.setNgayTraLai(phieuTraLai.getNgayTraLai());

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

        if (dto.getPhieuGuiTTienID() != null) {
            PhieuGuiTien phieuGuiTien = phieuGuiTienRepository.findById(dto.getPhieuGuiTTienID())
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