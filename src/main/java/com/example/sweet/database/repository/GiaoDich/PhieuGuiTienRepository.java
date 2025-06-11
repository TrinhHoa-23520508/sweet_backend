package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuGuiTienRepository extends CrudRepository<PhieuGuiTien, Integer> {
}
