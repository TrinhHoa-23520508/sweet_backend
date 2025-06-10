package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiKyHanRespository extends CrudRepository<LoaiKyHan, Integer> {
}
