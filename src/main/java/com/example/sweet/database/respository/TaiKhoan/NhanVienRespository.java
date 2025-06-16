package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRespository extends JpaRepository<NhanVien, Long>, JpaSpecificationExecutor<NhanVien> {}
