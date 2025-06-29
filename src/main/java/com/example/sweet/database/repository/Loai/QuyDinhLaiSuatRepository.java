package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhLaiSuatRepository extends JpaRepository<QuyDinhLaiSuat, Long> {
    boolean existsByNgayBatDau(LocalDate ngayBatDau);

    Optional<QuyDinhLaiSuat> findByNgayBatDau(LocalDate ngayBatDau);
}
