package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhLaiSuatRespository extends CrudRepository<QuyDinhLaiSuat, Integer> {
}
