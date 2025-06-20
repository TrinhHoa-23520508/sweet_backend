package com.example.sweet.config;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.util.SecurityUtil;
import com.example.sweet.util.constant.StatusEnum;
import com.example.sweet.util.error.IdInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws Exception {

        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        String usernameLogin = SecurityUtil.getCurrentUserLogin().orElse("");
        if (usernameLogin.equals("")) {
            throw new IdInvalidException("Bạn chưa đăng nhập");
        }
        if (usernameLogin.startsWith("kh")) {
            String email = usernameLogin.substring(3);
            KhachHang khachHang = this.khachHangRepository.findByEmail(email).orElseThrow(
                    () -> new IdInvalidException("Khách hàng không tồn tại")
            );
            TrangThai trangThaiTaiKhoan = khachHang.getTrangThaiTaiKhoan();
            if (trangThaiTaiKhoan.getMaTrangThai().equals(StatusEnum.VO_HIEU_HOA.toString())) {
                throw new IdInvalidException("Tài khoản của bạn đã bị vô hiệu hóa");
            }
            VaiTro vaiTro = khachHang.getVaiTro();
            if (vaiTro == null) {
                throw new IdInvalidException("Vai trò của bạn không được xác định");
            }
            List<QuyenHan> quyenHans = vaiTro.getQuyenHans();
            boolean isAllowed = quyenHans.stream()
                    .anyMatch(quyenHan -> quyenHan.getApiPath().equals(path) &&
                            quyenHan.getMethod().equalsIgnoreCase(httpMethod));
            if (!isAllowed) {
                throw new IdInvalidException("Bạn không có quyền truy cập vào nguồn tài nguyên này");
            }


        } else if (usernameLogin.startsWith("nv")) {
            String email = usernameLogin.substring(3);
            NhanVien nhanVien = this.nhanVienRepository.findByEmail(email).orElseThrow(
                    () -> new IdInvalidException("Nhân viên không tồn tại")
            );
            TrangThai trangThaiTaiKhoan = nhanVien.getTrangThaiTaiKhoan();
            if (trangThaiTaiKhoan.getMaTrangThai().equals(StatusEnum.VO_HIEU_HOA.toString())) {
                throw new IdInvalidException("Tài khoản của bạn đã bị vô hiệu hóa");
            }

            VaiTro vaiTro = nhanVien.getVaiTro();
            if (vaiTro == null) {
                throw new IdInvalidException("Vai trò của bạn không được xác định");
            }
            List<QuyenHan> quyenHans = vaiTro.getQuyenHans();
            boolean isAllowed = quyenHans.stream()
                    .anyMatch(quyenHan -> quyenHan.getApiPath().equals(path) &&
                            quyenHan.getMethod().equalsIgnoreCase(httpMethod));
            if (!isAllowed) {
                throw new IdInvalidException("Bạn không có quyền truy cập vào nguồn tài nguyên này");
            }

        }
        return true;
    }
}