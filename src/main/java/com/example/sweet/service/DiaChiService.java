package com.example.sweet.service;

import com.example.sweet.database.respository.TaiKhoan.DiaChiRespository;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaChiService {

    private final DiaChiRespository diaChiRespository;

    public DiaChiService(DiaChiRespository diaChiRespository) {
        this.diaChiRespository = diaChiRespository;
    }

    public DiaChi createDiaChi(DiaChi diaChi) {
        return diaChiRespository.save(diaChi);
    }
    public DiaChi getDiaChiById(Long id) {
        return diaChiRespository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DiaChi with id " + id + " not found."));
    }

    public DiaChi updateDiaChi(DiaChi diaChi) {
        if (diaChi.getDiaChiID() == null) {
            throw new IllegalArgumentException("DiaChi ID cannot be null for update.");
        }
        DiaChi existingDiaChi = diaChiRespository.findById(diaChi.getDiaChiID())
                .orElseThrow(() -> new IllegalArgumentException("DiaChi with id " + diaChi.getDiaChiID() + " not found."));

        // Update the existing entity with new values
        existingDiaChi.setSoNha(diaChi.getSoNha());
        existingDiaChi.setTenDuong(diaChi.getTenDuong());
        existingDiaChi.setPhuongXa(diaChi.getPhuongXa());
        existingDiaChi.setQuanHuyen(diaChi.getQuanHuyen());
        existingDiaChi.setTinhTP(diaChi.getTinhTP());

        return diaChiRespository.save(existingDiaChi);
    }

    public List<DiaChi> getAllDiaChi(Specification<DiaChi> specification) {
        return diaChiRespository.findAll(specification);
    }
}
