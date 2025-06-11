package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TanSuatNhanLaiRepository extends CrudRepository<TanSuatNhanLai, Integer> {
}
