package com.example.sweet.database.repository;

import com.example.sweet.database.schema.BaoCaoDoanhSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface BaoCaoDoanhSoRepository extends JpaRepository<BaoCaoDoanhSo, Long> {
}
