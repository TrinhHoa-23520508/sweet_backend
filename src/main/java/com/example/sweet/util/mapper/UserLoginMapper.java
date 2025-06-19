package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.request.ReqLoginDTO;
import com.example.sweet.domain.response.ResLoginDTO;
import com.example.sweet.util.constant.TypeUserEnum;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapper {

    private final VaiTroMapper vaiTroMapper;
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;

    public UserLoginMapper(VaiTroMapper vaiTroMapper, NhanVienRepository nhanVienRepository, KhachHangRepository khachHangRepository) {
        this.vaiTroMapper = vaiTroMapper;
        this.nhanVienRepository = nhanVienRepository;
        this.khachHangRepository = khachHangRepository;
    }

    public ResLoginDTO reqLoginToResLogin(ReqLoginDTO reqLogin) {
        ResLoginDTO resLoginDTO = new ResLoginDTO();
        KhachHang khachHang = null;
        NhanVien nhanVien = null;

        switch (reqLogin.getType()) {
            case KHACHHANG -> khachHang = this.khachHangRepository.findByEmail(reqLogin.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại với email: " + reqLogin.getUsername()));
            case NHANVIEN -> nhanVien = this.nhanVienRepository.findByEmail(reqLogin.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại với email: " + reqLogin.getUsername()));
            default -> throw new IllegalArgumentException("Invalid user type: " + reqLogin.getType());
        }

        ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin();
        userLogin.setId(khachHang != null ? khachHang.getKhachHangID() : nhanVien.getNhanVienID());
        userLogin.setEmail(reqLogin.getUsername());
        userLogin.setHoTen(khachHang != null ? khachHang.getHoTen() : nhanVien.getHoTen());
        userLogin.setVaiTro(vaiTroMapper.toVaiTroDTO(
                khachHang != null ? khachHang.getVaiTro() : nhanVien.getVaiTro()
        ));

        resLoginDTO.setUser(userLogin);
        resLoginDTO.setType(reqLogin.getType());

        return resLoginDTO;
    }

}
