package com.example.sweet.database.repository.GiaoDich;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;

public interface LichSuGiaoDich_PhieuGuiTienRepository extends JpaRepository<LichSuGiaoDich_PhieuGuiTien, Long> {
    List<LichSuGiaoDich_PhieuGuiTien> findByPhieuGuiTien(PhieuGuiTien phieuGuiTien);

    Optional<LichSuGiaoDich_PhieuGuiTien> findFirstByPhieuGuiTien(PhieuGuiTien phieuGuiTien);
}
