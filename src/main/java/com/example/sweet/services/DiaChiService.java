package com.example.sweet.services;


import com.example.sweet.database.repository.TaiKhoan.DiaChiRepository;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaChiService {

    private final DiaChiRepository diaChiRepository;

    public DiaChiService(DiaChiRepository diaChiRepository) {
        this.diaChiRepository = diaChiRepository;
    }

    public DiaChi createDiaChi(DiaChi diaChi) {
        return diaChiRepository.save(diaChi);
    }
    public DiaChi getDiaChiById(Long id) {
        return diaChiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DiaChi with id " + id + " not found."));
    }

    public DiaChi updateDiaChi(DiaChi diaChi) {
        if (diaChi.getDiaChiID() == null) {
            throw new IllegalArgumentException("DiaChi ID cannot be null for update.");
        }
        DiaChi existingDiaChi = diaChiRepository.findById(diaChi.getDiaChiID())
                .orElseThrow(() -> new IllegalArgumentException("DiaChi with id " + diaChi.getDiaChiID() + " not found."));

        // Update the existing entity with new values
        existingDiaChi.setSoNha(diaChi.getSoNha());
        existingDiaChi.setTenDuong(diaChi.getTenDuong());
        existingDiaChi.setPhuongXa(diaChi.getPhuongXa());
        existingDiaChi.setQuanHuyen(diaChi.getQuanHuyen());
        existingDiaChi.setTinhTP(diaChi.getTinhTP());

        return diaChiRepository.save(existingDiaChi);
    }

    public List<DiaChi> getAllDiaChi(Specification<DiaChi> specification) {
        return diaChiRepository.findAll(specification);
    }
}
