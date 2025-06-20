package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.request.ReqLoginDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;
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

    public ResLoginDTO.UserGetAccount NhanVienResToUserGetAccount(NhanVienResponseDTO nhanVienResponseDTO) {
        ResLoginDTO.UserGetAccount userGetAccount = new ResLoginDTO.UserGetAccount();
        userGetAccount.setId(nhanVienResponseDTO.getNhanVienID());
        userGetAccount.setHoTen(nhanVienResponseDTO.getHoTen());
        userGetAccount.setType(TypeUserEnum.NHANVIEN);
        userGetAccount.setNgaySinh(nhanVienResponseDTO.getNgaySinh());
        userGetAccount.setEmail(nhanVienResponseDTO.getEmail());
        userGetAccount.setCccd(nhanVienResponseDTO.getCccd());
        userGetAccount.setSoDienThoai(nhanVienResponseDTO.getSoDienThoai());
        userGetAccount.setDiaChiLienLac(nhanVienResponseDTO.getDiaChiLienLac());
        userGetAccount.setDiaChiThuongTru(nhanVienResponseDTO.getDiaChiThuongTru());
        userGetAccount.setVaiTro(nhanVienResponseDTO.getVaiTro());
        userGetAccount.setTrangThaiTaiKhoan(nhanVienResponseDTO.getTrangThaiTaiKhoan());

        return userGetAccount;
    }

    public ResLoginDTO.UserGetAccount KhachHangResToUserGetAccount(KhachHangResponseDTO khachHangResponseDTO) {
        ResLoginDTO.UserGetAccount userGetAccount = new ResLoginDTO.UserGetAccount();
        userGetAccount.setId(khachHangResponseDTO.getKhachHangID());
        userGetAccount.setHoTen(khachHangResponseDTO.getHoTen());
        userGetAccount.setType(TypeUserEnum.KHACHHANG);
        userGetAccount.setNgaySinh(khachHangResponseDTO.getNgaySinh());
        userGetAccount.setEmail(khachHangResponseDTO.getEmail());
        userGetAccount.setCccd(khachHangResponseDTO.getCccd());
        userGetAccount.setSoDienThoai(khachHangResponseDTO.getSoDienThoai());
        userGetAccount.setDiaChiLienLac(khachHangResponseDTO.getDiaChiLienLac());
        userGetAccount.setDiaChiThuongTru(khachHangResponseDTO.getDiaChiThuongTru());
        userGetAccount.setVaiTro(khachHangResponseDTO.getVaiTro());
        userGetAccount.setTrangThaiTaiKhoan(khachHangResponseDTO.getTrangThaiTaiKhoan());


        return userGetAccount;
    }

}
