package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.ThamSo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich, Long> {
    @Query("SELECT g FROM GiaoDich g WHERE g.taiKhoanNguon = :soTaiKhoan OR g.taiKhoanDich = :soTaiKhoan")
    List<GiaoDich> findByTaiKhoan(@Param("soTaiKhoan") Long soTaiKhoan);

    List<GiaoDich> findByNhanVienGiaoDich(NhanVien nhanVienGiaoDich);
}
