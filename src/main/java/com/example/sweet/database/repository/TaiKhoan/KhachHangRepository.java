package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long>, JpaSpecificationExecutor<KhachHang> {
}
