package com.example.sweet.util.mapper;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.response.NhanVienResponseDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;

public class NhanVienMapper {
    
    public static NhanVienResponseDTO toNhanVienResponseDTO(NhanVien nhanVien) {
        if (nhanVien == null) {
            return null;
        }

        NhanVienResponseDTO response = new NhanVienResponseDTO();
        response.setNhanVienId(nhanVien.getNhanVienID());
        response.setHoTen(nhanVien.getHoTen());
        response.setNgaySinh(nhanVien.getNgaySinh());
        response.setCccd(nhanVien.getCccd());
        response.setEmail(nhanVien.getEmail());
        response.setSoDienThoai(nhanVien.getSoDienThoai());
        response.setDiaChiLienLacId(nhanVien.getDiaChiLienLac().getDiaChiID());
        response.setDiaChiThuongTruId(nhanVien.getDiaChiThuongTru().getDiaChiID());
        response.setTrangThaiNhanVienId(nhanVien.getTrangThaiNhanVien().getTrangThaiID());
        response.setTenDangNhap(nhanVien.getTenDangNhap());
        response.setVaiTroId(nhanVien.getVaiTro().getId());
        response.setNgayTuyenDung(nhanVien.getNgayTuyenDung());
        response.setTrangThaiTaiKhoanId(nhanVien.getTrangThaiTaiKhoan().getTrangThaiID());

        return response;
    }
}
