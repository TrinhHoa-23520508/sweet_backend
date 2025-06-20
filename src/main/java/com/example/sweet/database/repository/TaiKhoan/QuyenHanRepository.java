package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuyenHanRepository extends JpaRepository<QuyenHan, Long>, JpaSpecificationExecutor<QuyenHan> {

    boolean existsByApiPathAndMethodAndModule(String apiPath, String method, String module);

    boolean existsByApiPathAndMethodAndModuleAndIdNot(String apiPath, String method, String module, Long id);

    List<QuyenHan> findAllByIdIn(List<Long> ids);

    List<QuyenHan> findAllByModule(String module);
}
