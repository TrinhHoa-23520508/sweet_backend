package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietQuyDinhLaiSuatRepository extends CrudRepository<ChiTietQuyDinhLaiSuat, Integer> {
}
