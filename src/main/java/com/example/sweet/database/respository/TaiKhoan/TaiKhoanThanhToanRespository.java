package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanThanhToanRespository extends JpaRepository<TaiKhoanThanhToan, Long>, JpaSpecificationExecutor<TaiKhoanThanhToan> {}
