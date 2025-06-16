package com.example.sweet.util.mapper;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.domain.request.KhachHangRequestDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;

public class KhachHangMapper {

    public static KhachHangResponseDTO toKhachHangResponseDTO(KhachHang khachHang) {
        if (khachHang == null) {
            return null;
        }

        KhachHangResponseDTO response = new KhachHangResponseDTO();
        response.setKhachHangId(khachHang.getKhachHangID());
        response.setHoTen(khachHang.getHoTen());
        response.setNgaySinh(khachHang.getNgaySinh());
        response.setCccd(khachHang.getCccd());
        response.setEmail(khachHang.getEmail());
        response.setSoDienThoai(khachHang.getSoDienThoai());
        response.setDiaChiLienLacId(khachHang.getDiaChiLienLac()!= null ? khachHang.getDiaChiLienLac().getDiaChiID() : null);
        response.setDiaChiThuongTruId(khachHang.getDiaChiThuongTru() != null ? khachHang.getDiaChiThuongTru().getDiaChiID() : null);
        response.setTrangThaiKhachHangId(khachHang.getTrangThaiKhachHang()!= null ? khachHang.getTrangThaiKhachHang().getTrangThaiID() : null);
        response.setTenDangNhap(khachHang.getTenDangNhap());
        response.setVaiTroId(khachHang.getVaiTro()!= null ? khachHang.getVaiTro().getId() : null);
        response.setNgayDangKy(khachHang.getNgayDangKy());
        response.setTrangThaiTaiKhoanId(khachHang.getTrangThaiTaiKhoan() != null ? khachHang.getTrangThaiTaiKhoan().getTrangThaiID() : null);

        return response;
    }

//    public static KhachHang toKhachHangFromRequest(KhachHangRequestDTO khachHangRequestDTO){
//        if
//(khachHangRequestDTO == null){
//            return null;
//        }
//        KhachHang khachHang = new KhachHang();
//
//
//    }
}
