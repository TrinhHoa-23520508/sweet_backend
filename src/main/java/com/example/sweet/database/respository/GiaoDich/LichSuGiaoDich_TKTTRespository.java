package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSuGiaoDich_TKTTRespository extends CrudRepository<LichSuGiaoDich_TKTT, Integer> {
    void deleteByTaiKhoanAndGiaoDich(TaiKhoanThanhToan taiKhoan, GiaoDich giaoDich);

}
