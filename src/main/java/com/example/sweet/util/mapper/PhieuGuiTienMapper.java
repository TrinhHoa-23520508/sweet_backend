package com.example.sweet.util.mapper;

import org.springframework.stereotype.Component;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.dto.PhieuGuiTienDTO;

public class PhieuGuiTienMapper {

    public static PhieuGuiTienDTO toDTO(PhieuGuiTien phieuGuiTien) {
        if (phieuGuiTien == null) {
            return null;
        }

        PhieuGuiTienDTO dto = new PhieuGuiTienDTO();
        dto.setPhieuGuiTienID(phieuGuiTien.getPhieuGuiTienID());
        dto.setKhachHangId(phieuGuiTien.getKhachHang().getKhachHangID());
        dto.setGiaoDichId(phieuGuiTien.getGiaoDich().getGiaoDichID());
        dto.setGiaoDichVienId(phieuGuiTien.getGiaoDichVien().getNhanVienID());
        dto.setLoaiTietKiemId(phieuGuiTien.getLoaiTietKiem().getLoaiTietKiemID());
        dto.setTanSuatNhanLaiId(phieuGuiTien.getTanSuatNhanLai().getTanSoNhanLaiID());
        dto.setLoaiKyHanId(phieuGuiTien.getLoaiKyHan().getLoaiKyHanID());
        dto.setHinhThucDaoHanId(phieuGuiTien.getHinhThucDaoHan().getHinhThucDaoHangID());
        dto.setNgayGuiTien(phieuGuiTien.getNgayGuiTien());
        dto.setSoTienGuiBanDau(phieuGuiTien.getSoTienGuiBanDau());
        dto.setLaiSuatCamKet(phieuGuiTien.getLaiSuatCamKet());
        dto.setTenGoiNho(phieuGuiTien.getTenGoiNho());
        dto.setTrangThaiId(phieuGuiTien.getTrangThai().getTrangThaiID());

        return dto;
    }

}
