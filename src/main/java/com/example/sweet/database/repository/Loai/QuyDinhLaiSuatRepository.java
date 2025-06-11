package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhLaiSuatRepository extends CrudRepository<QuyDinhLaiSuat, Integer> {
}
