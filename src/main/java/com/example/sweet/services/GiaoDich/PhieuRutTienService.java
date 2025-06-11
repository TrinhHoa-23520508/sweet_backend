package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.database.repository.GiaoDich.PhieuRutTienRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuRutTienService {
    @Autowired
    private PhieuRutTienRepository repository;

    public PhieuRutTien create(PhieuRutTien phieu) {
        return repository.save(phieu);
    }

    public List<PhieuRutTien> getAll() {
        return repository.findAll();
    }

    // Các hàm cập nhật, xóa, tìm kiếm,...
}
