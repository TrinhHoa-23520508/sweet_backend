package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSuGiaoDich_TKTTRespository extends JpaRepository<LichSuGiaoDich_TKTT, Long>, JpaSpecificationExecutor<LichSuGiaoDich_TKTT> {}
