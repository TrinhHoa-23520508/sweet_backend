package com.example.sweet.database.repository;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.database.schema.TrangThai;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Long>, JpaSpecificationExecutor<TrangThai> {
    Optional<TrangThai> findByMaTrangThaiAndLoaiTrangThai(String maTrangThai, LoaiTrangThai loaiTrangThai);

    List<TrangThai> findAllByLoaiTrangThai(LoaiTrangThai loaiTrangThai);
}
