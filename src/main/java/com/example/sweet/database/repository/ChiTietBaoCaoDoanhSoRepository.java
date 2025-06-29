package com.example.sweet.database.repository;

import com.example.sweet.database.schema.BaoCaoDoanhSo;
import com.example.sweet.database.schema.ChiTietBaoCaoDoanhSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietBaoCaoDoanhSoRepository extends JpaRepository<ChiTietBaoCaoDoanhSo, Long> {

    List<ChiTietBaoCaoDoanhSo> findAllByBaoCaoDoanhSo(BaoCaoDoanhSo baoCaoDoanhSo);
}
