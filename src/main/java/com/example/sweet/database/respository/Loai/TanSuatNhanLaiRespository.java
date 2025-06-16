package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TanSuatNhanLaiRespository extends JpaRepository<TanSuatNhanLai, Long>, JpaSpecificationExecutor<TanSuatNhanLai> {
}
