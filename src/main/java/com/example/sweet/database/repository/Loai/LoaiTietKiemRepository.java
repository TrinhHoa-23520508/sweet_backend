package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiTietKiemRepository extends CrudRepository<LoaiTietKiem, Integer> {
}
