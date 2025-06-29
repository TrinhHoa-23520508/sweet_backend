package com.example.sweet.services;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.request.ChangePasswordDTO;
import com.example.sweet.util.constant.TypeUserEnum;
import com.example.sweet.util.error.IdInvalidException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;

    public CommonService(KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository,
                         PasswordEncoder passwordEncoder) {
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(Long id, ChangePasswordDTO changePasswordDTO) throws IdInvalidException {
        TypeUserEnum userType = changePasswordDTO.getUserType();

        switch (userType) {
            case KHACHHANG -> changePasswordForKhachHang(id, changePasswordDTO);
            case NHANVIEN -> changePasswordForNhanVien(id, changePasswordDTO);
            default -> throw new IdInvalidException("Loại người dùng không hợp lệ");
        }
    }

    private void changePasswordForKhachHang(Long id, ChangePasswordDTO dto) throws IdInvalidException {
        KhachHang khachHang = khachHangRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("ID khách hàng không hợp lệ"));

        if (!passwordEncoder.matches(dto.getOldPassword(), khachHang.getMatKhau())) {
            throw new IdInvalidException("Mật khẩu cũ không trùng khớp");
        }

        khachHang.setMatKhau(passwordEncoder.encode(dto.getNewPassword()));
        khachHangRepository.save(khachHang);
    }

    private void changePasswordForNhanVien(Long id, ChangePasswordDTO dto) throws IdInvalidException {
        NhanVien nhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("ID nhân viên không hợp lệ"));

        if (!passwordEncoder.matches(dto.getOldPassword(), nhanVien.getMatKhau())) {
            throw new IdInvalidException("Mật khẩu cũ không trùng khớp");
        }

        nhanVien.setMatKhau(passwordEncoder.encode(dto.getNewPassword()));
        nhanVienRepository.save(nhanVien);
    }

}
