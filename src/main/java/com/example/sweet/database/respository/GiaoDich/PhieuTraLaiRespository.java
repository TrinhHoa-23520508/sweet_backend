package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuTraLaiRespository extends CrudRepository<PhieuRutTien, Integer> {}
