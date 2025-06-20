package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.TaiKhoan.DiaChiRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.KhachHangRequestDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class KhachHangMapper {

    private final DiaChiRepository diaChiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final VaiTroRepository vaiTroRepository;
    private final TrangThaiMapper trangThaiMapper;
    private final VaiTroMapper vaiTroMapper;

    public KhachHangMapper(DiaChiRepository diaChiRepository,
                           TrangThaiRepository trangThaiRepository,
                           VaiTroRepository vaiTroRepository,
                           TrangThaiMapper trangThaiMapper,
                           VaiTroMapper vaiTroMapper) {
        this.diaChiRepository = diaChiRepository;
        this.trangThaiRepository = trangThaiRepository;
        this.vaiTroRepository = vaiTroRepository;
        this.trangThaiMapper = trangThaiMapper;
        this.vaiTroMapper = vaiTroMapper;
    }

    public KhachHang toKhachHangEntity(KhachHangRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(requestDTO.getHoTen());
        khachHang.setNgaySinh(requestDTO.getNgaySinh());
        khachHang.setCccd(requestDTO.getCccd());
        khachHang.setEmail(requestDTO.getEmail());
        khachHang.setSoDienThoai(requestDTO.getSoDienThoai());
        khachHang.setDiaChiThuongTru(requestDTO.getDiaChiThuongTru());
        khachHang.setDiaChiLienLac(requestDTO.getDiaChiLienLac());
        if (requestDTO.getTrangThaiKhachHangId() != null) {
            TrangThai trangThai = trangThaiRepository.findById(requestDTO.getTrangThaiKhachHangId())
                    .orElseThrow(() -> new IllegalArgumentException("Trang thai khach hang not found with id: " + requestDTO.getTrangThaiKhachHangId()));
            khachHang.setTrangThaiKhachHang(trangThai);
        } else {
            khachHang.setTrangThaiKhachHang(null);
        }
        if (requestDTO.getVaiTroId() != null) {
            khachHang.setVaiTro(vaiTroRepository.findById(requestDTO.getVaiTroId())
                    .orElseThrow(() -> new IllegalArgumentException("Vai tro not found with id: " + requestDTO.getVaiTroId())));
        } else {
            khachHang.setVaiTro(null);
        }
        if (requestDTO.getTrangThaiTaiKhoanId() != null) {
            TrangThai trangThaiTaiKhoan = trangThaiRepository.findById(requestDTO.getTrangThaiTaiKhoanId())
                    .orElseThrow(() -> new IllegalArgumentException("Trang thai tai khoan not found with id: " + requestDTO.getTrangThaiTaiKhoanId()));
            khachHang.setTrangThaiTaiKhoan(trangThaiTaiKhoan);
        } else {
            khachHang.setTrangThaiTaiKhoan(null);
        }
        khachHang.setMatKhau(requestDTO.getMatKhau());


        return khachHang;
    }

    public KhachHangResponseDTO toKhachHangResponseDTO(KhachHang khachHang) {
        if (khachHang == null) {
            return null;
        }

        KhachHangResponseDTO responseDTO = new KhachHangResponseDTO();
        responseDTO.setKhachHangID(khachHang.getKhachHangID());
        responseDTO.setHoTen(khachHang.getHoTen());
        responseDTO.setNgaySinh(khachHang.getNgaySinh());
        responseDTO.setCccd(khachHang.getCccd());
        responseDTO.setEmail(khachHang.getEmail());
        responseDTO.setSoDienThoai(khachHang.getSoDienThoai());
        responseDTO.setTuoi(khachHang.getTuoi());
        responseDTO.setDiaChiThuongTru(khachHang.getDiaChiThuongTru());
        responseDTO.setDiaChiLienLac(khachHang.getDiaChiLienLac());
        responseDTO.setNgayDangKy(khachHang.getNgayDangKy());
        responseDTO.setTrangThaiKhachHang(khachHang.getTrangThaiKhachHang() != null ?
                this.trangThaiMapper.toTrangThaiDTO(khachHang.getTrangThaiKhachHang()) : null);
        responseDTO.setVaiTro(khachHang.getVaiTro() != null ?
                this.vaiTroMapper.toVaiTroDTO(khachHang.getVaiTro()) : null);
        responseDTO.setTrangThaiTaiKhoan(khachHang.getTrangThaiTaiKhoan() != null ?
                this.trangThaiMapper.toTrangThaiDTO(khachHang.getTrangThaiTaiKhoan()) : null);
        return responseDTO;
    }


}
