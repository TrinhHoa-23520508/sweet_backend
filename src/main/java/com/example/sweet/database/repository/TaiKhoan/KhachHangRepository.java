package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long>, JpaSpecificationExecutor<KhachHang> {

    boolean existsByEmailOrCccd(String email, String cccd);

    boolean existsByEmailOrCccdAndKhachHangIDNot(String email, String cccd, Long id);

    Optional<KhachHang> findByEmail(String email);

    @Query("SELECT kh FROM KhachHang kh " +
            "JOIN FETCH kh.vaiTro vt " +
            "JOIN FETCH vt.quyenHans " +
            "WHERE kh.email = :email")
    Optional<KhachHang> findByEmailWithPermissions(@Param("email") String email);
}
