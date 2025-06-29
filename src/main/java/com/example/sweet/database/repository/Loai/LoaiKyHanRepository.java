package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiKyHan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiKyHanRepository extends JpaRepository<LoaiKyHan, Long> {
    Optional<LoaiKyHan> findBySoThang(Integer soThang);
}
