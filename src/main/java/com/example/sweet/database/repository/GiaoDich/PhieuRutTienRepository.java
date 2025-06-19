package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuRutTienRepository extends CrudRepository<PhieuRutTien, Long> {
    PhieuRutTien saveAndFlush(PhieuRutTien phieuRutTien);
}
