package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuTraLaiRespository extends JpaRepository<PhieuTraLai, Long>, JpaSpecificationExecutor<PhieuTraLai> {}
