package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietQuyDinhLaiSuatRepository extends JpaRepository<ChiTietQuyDinhLaiSuat, Long> {
    Optional<ChiTietQuyDinhLaiSuat> findByLoaiKyHan_LoaiKyHanIDAndLoaiTietKiem_LoaiTietKiemIDAndTanSuatNhanLai_TanSoNhanLaiID(
            Long loaiKyHanId,
            Long loaiTietKiemId,
            Long tanSuatNhanLaiId);
}
