package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanThanhToanRepository extends JpaRepository<TaiKhoanThanhToan, Long> {
    // Find TaiKhoanThanhToan by KhachHangID
    Optional<TaiKhoanThanhToan> findByKhachHangKhachHangID(Long khachHangId);

    Optional<TaiKhoanThanhToan> findByKhachHang_KhachHangId(Long khachHangId);
}
