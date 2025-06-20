package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long>, JpaSpecificationExecutor<NhanVien> {

    boolean existsByEmailOrCccd(String email, String cccd);

    boolean existsByEmailOrCccdAndNhanVienIDNot(String email, String cccd, Long nhanVienID);

    Optional<NhanVien> findByEmail(String email);

    @Query("SELECT nv FROM NhanVien nv " +
            "JOIN FETCH nv.vaiTro vt " +
            "JOIN FETCH vt.quyenHans " +
            "WHERE nv.email = :email")
    Optional<NhanVien> findByEmailWithPermissions(@Param("email") String email);
}
