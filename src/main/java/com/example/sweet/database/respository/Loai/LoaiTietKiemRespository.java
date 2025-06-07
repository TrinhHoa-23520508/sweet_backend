package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import org.springframework.data.repository.CrudRepository;

public interface LoaiTietKiemRespository extends CrudRepository<LoaiTietKiem, Integer> {
}
