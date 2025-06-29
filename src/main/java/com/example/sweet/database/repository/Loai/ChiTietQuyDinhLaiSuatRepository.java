package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;

import java.util.List;
import java.util.Optional;

import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietQuyDinhLaiSuatRepository extends JpaRepository<ChiTietQuyDinhLaiSuat, Long> {
    Optional<ChiTietQuyDinhLaiSuat> findByLoaiKyHan_LoaiKyHanIDAndLoaiTietKiem_LoaiTietKiemIDAndTanSuatNhanLai_TanSoNhanLaiID(
            Integer loaiKyHanId,
            Long loaiTietKiemId,
            Long tanSuatNhanLaiId);

    Optional<ChiTietQuyDinhLaiSuat> findByQuyDinhLaiSuat_QuyDinhLaiSuatIDAndLoaiKyHan_LoaiKyHanIDAndLoaiTietKiem_LoaiTietKiemIDAndTanSuatNhanLai_TanSoNhanLaiID(
            Long quyDinhLaiSuatId,
            Integer loaiKyHanId,
            Long loaiTietKiemId,
            Long tanSuatNhanLaiId);

    List<ChiTietQuyDinhLaiSuat> findAllByQuyDinhLaiSuat(QuyDinhLaiSuat quyDinhLaiSuat);
}
