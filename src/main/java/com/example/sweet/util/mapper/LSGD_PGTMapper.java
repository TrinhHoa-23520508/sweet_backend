package com.example.sweet.util.mapper;

import org.springframework.stereotype.Component;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.domain.response.GiaoDich.LSGD_PGTDTO;
import com.example.sweet.util.mapper.GiaoDich.GiaoDichMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LSGD_PGTMapper {
    private final GiaoDichMapper giaoDichMapper;

    public LSGD_PGTDTO toDTO(LichSuGiaoDich_PhieuGuiTien LSGD_PGT) {
        LSGD_PGTDTO dto = new LSGD_PGTDTO();
        dto.setGiaoDich(giaoDichMapper.toGiaoDichResponseDTO(LSGD_PGT.getGiaoDich()));
        dto.setLichSuPhieuGuiTienId(LSGD_PGT.getLichSuPhieuGuiTienID());
        dto.setPhieuGuiTienId(LSGD_PGT.getPhieuGuiTien().getPhieuGuiTienID());
        dto.setSoTienGocGiaoDich(LSGD_PGT.getSoTienGocGiaoDich());
        dto.setSoDuHienTai_SauGD(LSGD_PGT.getSoDuHienTai_SauGD());
        return dto;
    }
}
