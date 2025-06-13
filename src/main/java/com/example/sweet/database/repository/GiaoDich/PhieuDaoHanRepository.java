package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuDaoHanRepository extends CrudRepository<PhieuDaoHan, Integer> {
    // boolean existsByPhieuGuiTienKyTruoc(Long phieuGuiTienKyTruocId);
    boolean existsByPhieuGuiTienKyTruoc_PhieuGuiTienID(Long phieuGuiTienKyTruocId);
}
