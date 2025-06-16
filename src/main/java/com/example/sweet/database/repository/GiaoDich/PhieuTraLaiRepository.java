package com.example.sweet.database.repository.GiaoDich;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;

@Repository
public interface PhieuTraLaiRepository extends JpaRepository<PhieuTraLai, Long> {

    // Check if exists a PhieuTraLai with same PhieuGuiTien and NgayTraLai
    boolean existsByPhieuGuiTienAndNgayTraLai(PhieuGuiTien phieuGuiTien, Instant ngayTraLai);

    // Additional useful queries
    List<PhieuTraLai> findByPhieuGuiTien(PhieuGuiTien phieuGuiTien);

    List<PhieuTraLai> findByPhieuGuiTienAndNgayTraLaiBetween(
            PhieuGuiTien phieuGuiTien,
            LocalDateTime startDate,
            LocalDateTime endDate);
}
