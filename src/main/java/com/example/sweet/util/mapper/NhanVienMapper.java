package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.TaiKhoan.DiaChiRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.NhanVienRequestDTO;
import com.example.sweet.domain.response.NhanVienNoVaiTroResponseDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class NhanVienMapper {

    private final DiaChiRepository diaChiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final VaiTroRepository vaiTroRepository;
    private final TrangThaiMapper trangThaiMapper;
    private final VaiTroMapper vaiTroMapper;

    public NhanVienMapper(DiaChiRepository diaChiRepository,
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

    public NhanVien toNhanVienEntity(NhanVienRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoTen(requestDTO.getHoTen());
        nhanVien.setNgaySinh(requestDTO.getNgaySinh());
        nhanVien.setCccd(requestDTO.getCccd());
        nhanVien.setSoDienThoai(requestDTO.getSoDienThoai());
        nhanVien.setEmail(requestDTO.getEmail());
        nhanVien.setMatKhau(requestDTO.getMatKhau());
        nhanVien.setDiaChiLienLac(requestDTO.getDiaChiLienLac());
        nhanVien.setDiaChiThuongTru(requestDTO.getDiaChiThuongTru());
        if (requestDTO.getVaiTroId() != null) {
            VaiTro vaiTro = vaiTroRepository.findById(requestDTO.getVaiTroId())
                    .orElseThrow(() -> new IllegalArgumentException("Vai tro not found with id: " + requestDTO.getVaiTroId()));
            if (vaiTro.isCustomerRole()) {
                throw new IllegalArgumentException("Cannot assign customer role to NhanVien");
            }
            nhanVien.setVaiTro(vaiTro);
        } else {
            nhanVien.setVaiTro(null);
        }
        if (requestDTO.getTrangThaiTaiKhoanId() != null) {
            TrangThai trangThaiTaiKhoan = trangThaiRepository.findById(requestDTO.getTrangThaiTaiKhoanId())
                    .orElseThrow(() -> new IllegalArgumentException("Trang thai tai khoan not found with id: " + requestDTO.getTrangThaiTaiKhoanId()));
            nhanVien.setTrangThaiTaiKhoan(trangThaiTaiKhoan);
        } else {
            nhanVien.setTrangThaiTaiKhoan(null);
        }

        return nhanVien;

    }

    public NhanVienResponseDTO toNhanVienResponseDTO(NhanVien nhanVien) {
        if (nhanVien == null) return null;

        NhanVienResponseDTO responseDTO = new NhanVienResponseDTO();
        responseDTO.setNhanVienID(nhanVien.getNhanVienID());
        responseDTO.setHoTen(nhanVien.getHoTen());
        responseDTO.setNgaySinh(nhanVien.getNgaySinh());
        responseDTO.setCccd(nhanVien.getCccd());
        responseDTO.setEmail(nhanVien.getEmail());
        responseDTO.setSoDienThoai(nhanVien.getSoDienThoai());
        responseDTO.setTuoi(nhanVien.getTuoi());
        responseDTO.setDiaChiLienLac(nhanVien.getDiaChiLienLac());
        responseDTO.setDiaChiThuongTru(nhanVien.getDiaChiThuongTru());
        responseDTO.setNgayTuyenDung(nhanVien.getNgayTuyenDung());
        responseDTO.setVaiTro(nhanVien.getVaiTro() != null ?
                this.vaiTroMapper.toVaiTroDTO(nhanVien.getVaiTro()) : null);
        responseDTO.setTrangThaiTaiKhoan(nhanVien.getTrangThaiTaiKhoan() != null ?
                this.trangThaiMapper.toTrangThaiDTO(nhanVien.getTrangThaiTaiKhoan()) : null);
        return responseDTO;
    }

    public NhanVienNoVaiTroResponseDTO toNhanVienNoVaiTroResponseDTO(NhanVien nhanVien) {
        if (nhanVien == null) return null;

        NhanVienResponseDTO responseDTO = toNhanVienResponseDTO(nhanVien);

        NhanVienNoVaiTroResponseDTO responseNoVaiTroDTO = new NhanVienNoVaiTroResponseDTO();
        responseNoVaiTroDTO.setNhanVienID(responseDTO.getNhanVienID());
        responseNoVaiTroDTO.setHoTen(responseDTO.getHoTen());
        responseNoVaiTroDTO.setNgaySinh(responseDTO.getNgaySinh());
        responseNoVaiTroDTO.setCccd(responseDTO.getCccd());
        responseNoVaiTroDTO.setEmail(responseDTO.getEmail());
        responseNoVaiTroDTO.setSoDienThoai(responseDTO.getSoDienThoai());
        responseNoVaiTroDTO.setTuoi(responseDTO.getTuoi());
        responseNoVaiTroDTO.setDiaChiLienLac(responseDTO.getDiaChiLienLac());
        responseNoVaiTroDTO.setDiaChiThuongTru(responseDTO.getDiaChiThuongTru());
        responseNoVaiTroDTO.setNgayTuyenDung(responseDTO.getNgayTuyenDung());
        responseNoVaiTroDTO.setTrangThaiTaiKhoan(responseDTO.getTrangThaiTaiKhoan());
        return responseNoVaiTroDTO;
    }


}
