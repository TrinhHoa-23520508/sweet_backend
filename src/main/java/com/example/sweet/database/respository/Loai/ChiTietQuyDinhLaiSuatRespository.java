package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietQuyDinhLaiSuatRespository extends CrudRepository<ChiTietQuyDinhLaiSuat, Integer> {
}
