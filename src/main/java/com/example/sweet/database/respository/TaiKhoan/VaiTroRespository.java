package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRespository extends CrudRepository<VaiTro, Integer> {}
