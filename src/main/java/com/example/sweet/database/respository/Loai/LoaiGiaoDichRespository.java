package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import org.springframework.data.repository.CrudRepository;

public interface LoaiGiaoDichRespository extends CrudRepository<LoaiGiaoDich, Integer> {
}
