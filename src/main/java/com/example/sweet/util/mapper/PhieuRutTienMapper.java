package com.example.sweet.util.mapper;

import org.springframework.stereotype.Component;
import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.domain.request.PhieuRutTienreqDTO;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PhieuRutTienMapper {

    private final PhieuGuiTienRepository phieuGuiTienRepository;

    public PhieuRutTien toEntity(PhieuRutTienreqDTO dto) {
        if (dto == null) {
            return null;
        }

        PhieuRutTien phieuRutTien = new PhieuRutTien();

        // Set phieuGuiTien from ID
        phieuRutTien.setPhieuGuiTien(
                phieuGuiTienRepository.findById(dto.getPhieuGuiTienID())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Không tìm thấy phiếu gửi tiền với ID: " + dto.getPhieuGuiTienID())));

        phieuRutTien.setSoTienRut(dto.getSoTienRut().intValue());

        return phieuRutTien;
    }

    public PhieuRutTienreqDTO toDTO(PhieuRutTien entity) {
        if (entity == null) {
            return null;
        }

        PhieuRutTienreqDTO dto = new PhieuRutTienreqDTO();
        dto.setPhieuGuiTienID(entity.getPhieuGuiTien().getPhieuGuiTienID());
        dto.setSoTienRut((long) entity.getSoTienRut());
        if (entity.getGiaoDich() != null && entity.getGiaoDich().getKenhGiaoDich() != null) {
            dto.setKenhGiaoDichID(entity.getGiaoDich().getKenhGiaoDich().getKenhGiaoDichID());
        }

        return dto;
    }
}
