package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuDaoHanRespository extends JpaRepository<PhieuDaoHan, Long>, JpaSpecificationExecutor<PhieuDaoHan> {}
