package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanThanhToanRepository extends CrudRepository<TaiKhoanThanhToan, Integer> {
}
