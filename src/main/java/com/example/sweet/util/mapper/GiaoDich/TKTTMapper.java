package com.example.sweet.util.mapper.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.domain.request.GiaoDich.GiaoDichRequestDTO;
import com.example.sweet.domain.request.GiaoDich.TKTTRequestDTO;
import com.example.sweet.domain.response.GiaoDich.TKTTResponseDTO;
import com.example.sweet.util.mapper.KhachHangMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class TKTTMapper {
    private KhachHangRepository khachHangRepository;
    private TrangThaiRepository trangThaiRepository;
    private KhachHangMapper khachHangMapper;

    public TaiKhoanThanhToan toTaiKhoanThanhToan(TKTTRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        TaiKhoanThanhToan taiKhoanThanhToan = new TaiKhoanThanhToan();
        taiKhoanThanhToan.setSoTaiKhoan(requestDTO.getSoTaiKhoan());
        taiKhoanThanhToan.setNgayTao(requestDTO.getNgayTao());
        taiKhoanThanhToan.setSoDu(requestDTO.getSoDu());
        taiKhoanThanhToan.setTrangThai(
            trangThaiRepository.findById(requestDTO.getTrangThaiID())
                    .orElseThrow(() -> new NullPointerException("Không tìm thấy trạng thái tài khoản")));
        taiKhoanThanhToan.setKhachHang(
                khachHangRepository.findById(requestDTO.getKhachHangID())
                .orElseThrow(() -> new NullPointerException("Không tìm thấy khách hàng cho tài khoản thanh toán")));
        return taiKhoanThanhToan;
    }

    public TKTTResponseDTO toTKTTResponseDTO(TaiKhoanThanhToan taiKhoanThanhToan) {
        if (taiKhoanThanhToan == null) {
            return null;
        }
        TKTTResponseDTO responseDTO = new TKTTResponseDTO();
        responseDTO.setSoTaiKhoan(taiKhoanThanhToan.getSoTaiKhoan());
        responseDTO.setNgayTao(taiKhoanThanhToan.getNgayTao());
        responseDTO.setSoDu(taiKhoanThanhToan.getSoDu());
        responseDTO.setTrangThai(taiKhoanThanhToan.getTrangThai());
        responseDTO.setKhachHang(khachHangMapper.toKhachHangResponseDTO(taiKhoanThanhToan.getKhachHang()));
        return responseDTO;
    }
}
