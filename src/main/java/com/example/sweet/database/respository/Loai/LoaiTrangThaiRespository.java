package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiTrangThaiRespository extends CrudRepository<LoaiTrangThai, Integer> {
}
