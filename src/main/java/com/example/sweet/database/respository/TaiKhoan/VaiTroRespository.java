package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRespository extends JpaRepository<VaiTro, Long>, JpaSpecificationExecutor<VaiTro> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
