package com.example.sweet.database.respository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.ThamSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaChiRespository extends JpaRepository<DiaChi, Long>, JpaSpecificationExecutor<DiaChi> {}
