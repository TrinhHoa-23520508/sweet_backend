package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import org.springframework.data.repository.CrudRepository;

public interface NhanVienRespository extends CrudRepository<NhanVien, Integer> {}
