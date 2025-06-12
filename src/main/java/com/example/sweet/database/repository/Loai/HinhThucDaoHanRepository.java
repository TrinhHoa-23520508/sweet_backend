package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhThucDaoHanRepository extends JpaRepository<HinhThucDaoHan, Long> {
}
