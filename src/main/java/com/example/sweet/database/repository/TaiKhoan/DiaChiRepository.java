package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.ThamSo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaChiRepository extends CrudRepository<DiaChi, Integer> {
}
