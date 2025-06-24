package com.example.sweet.config;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.services.KhachHangService;
import com.example.sweet.services.NhanVienService;
import com.example.sweet.util.constant.StatusEnum;
import com.example.sweet.util.error.IdInvalidException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("userDetailsService")
public class UserDetailCustom implements UserDetailsService {

    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;

    public UserDetailCustom(KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository) {
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("nv.")) {
            String email = username.substring(3);
            NhanVien nhanVien = this.nhanVienRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("NhanVien not found"));
            return new User(
                    username,
                    nhanVien.getMatKhau(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_NHAN_VIEN"))
            );
        } else if (username.startsWith("kh.")) {
            String email = username.substring(3);
            KhachHang khachHang = this.khachHangRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("KhachHang not found"));
            return new User(
                    username,
                    khachHang.getMatKhau(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_KHACH_HANG"))
            );
        } else {
            throw new UsernameNotFoundException("Tên đăng nhập không hợp lệ");
        }
    }
}
