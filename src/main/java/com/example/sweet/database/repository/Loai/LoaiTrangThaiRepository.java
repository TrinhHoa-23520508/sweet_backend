package com.example.sweet.database.repository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoaiTrangThaiRepository extends JpaRepository<LoaiTrangThai, Long>, JpaSpecificationExecutor<LoaiTrangThai> {

    Optional<LoaiTrangThai> findByMaLoaiTrangThai(String maLoaiTrangThai);
}
