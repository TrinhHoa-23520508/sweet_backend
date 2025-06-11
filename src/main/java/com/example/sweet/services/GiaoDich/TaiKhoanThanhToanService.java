package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaiKhoanThanhToanService {
    private TaiKhoanThanhToanRepository taiKhoanRepo;

    public Iterable<TaiKhoanThanhToan> findAll() {
        return taiKhoanRepo.findAll();
    }

    public TaiKhoanThanhToan save(TaiKhoanThanhToan taiKhoan) {
        return taiKhoanRepo.save(taiKhoan);
    }

    public void deleteById(int id) {
        taiKhoanRepo.deleteById(id);
    }

    public Optional<TaiKhoanThanhToan> findById(int id) {
        return taiKhoanRepo.findById(id);
    }
}
