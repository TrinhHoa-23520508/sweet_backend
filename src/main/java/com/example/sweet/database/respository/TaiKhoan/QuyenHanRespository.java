package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenHanRespository extends CrudRepository<QuyenHan, Integer> {}
