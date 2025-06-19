package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuGuiTienRepository extends JpaRepository<PhieuGuiTien, Long> {
    List<PhieuGuiTien> findByTrangThaiMaTrangThai(String maTrangThai);
}
