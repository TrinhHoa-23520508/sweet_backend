package com.example.sweet.database.respository.Loai;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyDinhLaiSuatRespository extends JpaRepository<QuyDinhLaiSuat, Long>, JpaSpecificationExecutor<QuyDinhLaiSuat> {
}
