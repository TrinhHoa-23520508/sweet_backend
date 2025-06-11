package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;
import com.example.sweet.database.repository.GiaoDich.PhieuTraLaiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuTraLaiService {
    @Autowired
    private PhieuTraLaiRepository repository;

    public PhieuTraLai create(PhieuTraLai phieu) {
        return repository.save(phieu);
    }

    public List<PhieuTraLai> getAll() {
        return repository.findAll();
    }

    // Các hàm cập nhật, xóa, tìm kiếm,...
}
