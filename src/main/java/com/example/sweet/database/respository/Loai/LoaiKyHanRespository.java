package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiKyHanRespository extends JpaRepository<LoaiKyHan, Long>, JpaSpecificationExecutor<LoaiKyHan> {
}
