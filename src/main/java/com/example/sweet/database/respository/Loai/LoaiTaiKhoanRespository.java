package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiTaiKhoanRespository extends CrudRepository<LoaiTaiKhoan, Integer> {
}
