package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import org.springframework.data.repository.CrudRepository;

public interface KhachHangRespository extends CrudRepository<KhachHang, Integer> {}
