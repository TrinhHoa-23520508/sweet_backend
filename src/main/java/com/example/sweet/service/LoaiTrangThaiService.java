package com.example.sweet.service;

import com.example.sweet.database.respository.Loai.LoaiTrangThaiRespository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.IdInvalidException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiTrangThaiService {

    private final LoaiTrangThaiRespository loaiTrangThaiRespository;
    public LoaiTrangThaiService(LoaiTrangThaiRespository loaiTrangThaiRespository) {
        this.loaiTrangThaiRespository = loaiTrangThaiRespository;
    }

    public LoaiTrangThai createLoaiTrangThai(LoaiTrangThai loaiTrangThai) {
        Optional<LoaiTrangThai> loaiTrangThaiDB = this.loaiTrangThaiRespository.findByMaLoaiTrangThai(loaiTrangThai.getMaLoaiTrangThai());
        if(loaiTrangThaiDB.isPresent()) {
            throw new DuplicateResourceException("LoaiTrangThai with maLoaiTrangThai " + loaiTrangThai.getMaLoaiTrangThai() + " already exists.");
        }
        return loaiTrangThaiRespository.save(loaiTrangThai);
    }

    public List<LoaiTrangThai> getAllLoaiTrangThai(Specification<LoaiTrangThai> specification) {
        return loaiTrangThaiRespository.findAll(specification);
    }

    public LoaiTrangThai getLoaiTrangThaiById(Long id) {
    LoaiTrangThai loaiTrangThai = loaiTrangThaiRespository.findById(id)
            .orElseThrow(() -> new IdInvalidException("LoaiTrangThai with id " + id + " not found."));
        return loaiTrangThai;
    }

    public LoaiTrangThai updateLoaiTrangThai(LoaiTrangThai loaiTrangThai) {
        if(loaiTrangThai.getLoaiTrangThaiID() == null) {
            throw new IdInvalidException("LoaiTrangThai ID cannot be null for update.");
        }
        LoaiTrangThai existingLoaiTrangThai = loaiTrangThaiRespository.findById(loaiTrangThai.getLoaiTrangThaiID())
                .orElseThrow(() -> new IdInvalidException("LoaiTrangThai with id " + loaiTrangThai.getLoaiTrangThaiID() + " not found."));
        Optional<LoaiTrangThai> loaiTrangThaiDB = this.loaiTrangThaiRespository.findByMaLoaiTrangThai(loaiTrangThai.getMaLoaiTrangThai());
        if(loaiTrangThaiDB.isPresent() && !loaiTrangThaiDB.get().getLoaiTrangThaiID().equals(loaiTrangThai.getLoaiTrangThaiID())) {
            throw new DuplicateResourceException("LoaiTrangThai with maLoaiTrangThai " + loaiTrangThai.getMaLoaiTrangThai() + " already exists.");
        }
        // Update the existing entity with new values
        existingLoaiTrangThai.setMaLoaiTrangThai(loaiTrangThai.getMaLoaiTrangThai());
        existingLoaiTrangThai.setTenLoaiTrangThai(loaiTrangThai.getTenLoaiTrangThai());
        existingLoaiTrangThai.setMoTa(loaiTrangThai.getMoTa());
        existingLoaiTrangThai.setDeleted(loaiTrangThai.getDeleted());
        // Save the updated entity
        return loaiTrangThaiRespository.save(loaiTrangThai);
    }

    public void deleteLoaiTrangThai(Long id) {
        LoaiTrangThai loaiTrangThai = loaiTrangThaiRespository.findById(id)
                .orElseThrow(() -> new IdInvalidException("LoaiTrangThai with id " + id + " not found."));
        loaiTrangThai.setDeleted(true);
        loaiTrangThaiRespository.save(loaiTrangThai);
    }
}
