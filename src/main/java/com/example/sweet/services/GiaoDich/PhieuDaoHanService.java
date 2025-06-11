package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import com.example.sweet.database.repository.GiaoDich.PhieuDaoHanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuDaoHanService {
    @Autowired
    private PhieuDaoHanRepository repository;

    public PhieuDaoHan create(PhieuDaoHan phieu) {
        return repository.save(phieu);
    }

    public List<PhieuDaoHan> getAll() {
        return repository.findAll();
    }

    // Các hàm cập nhật, xóa, tìm kiếm,...
}
