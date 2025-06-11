package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiGiaoDichRepository extends CrudRepository<LoaiGiaoDich, Integer> {
}
