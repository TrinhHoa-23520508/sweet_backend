package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.LoaiTrangThaiRepository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.NotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiTrangThaiService {

    private final LoaiTrangThaiRepository loaiTrangThaiRepository;
    public LoaiTrangThaiService(LoaiTrangThaiRepository loaiTrangThaiRepository) {
        this.loaiTrangThaiRepository = loaiTrangThaiRepository;
    }

    public LoaiTrangThai createLoaiTrangThai(LoaiTrangThai loaiTrangThai) {
        Optional<LoaiTrangThai> loaiTrangThaiDB = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(loaiTrangThai.getMaLoaiTrangThai());
        if(loaiTrangThaiDB.isPresent()) {
            throw new DuplicateResourceException("LoaiTrangThai with maLoaiTrangThai " + loaiTrangThai.getMaLoaiTrangThai() + " already exists.");
        }
        return loaiTrangThaiRepository.save(loaiTrangThai);
    }

    public List<LoaiTrangThai> getAllLoaiTrangThai(Specification<LoaiTrangThai> specification) {
        return loaiTrangThaiRepository.findAll(specification);
    }

    public LoaiTrangThai getLoaiTrangThaiById(Long id) {
    LoaiTrangThai loaiTrangThai = loaiTrangThaiRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("LoaiTrangThai with id " + id + " not found."));
        return loaiTrangThai;
    }

    public LoaiTrangThai updateLoaiTrangThai(LoaiTrangThai loaiTrangThai) {
        if(loaiTrangThai.getLoaiTrangThaiID() == null) {
            throw new NotFoundException("LoaiTrangThai ID cannot be null for update.");
        }
        LoaiTrangThai existingLoaiTrangThai = loaiTrangThaiRepository.findById(loaiTrangThai.getLoaiTrangThaiID())
                .orElseThrow(() -> new NotFoundException("LoaiTrangThai with id " + loaiTrangThai.getLoaiTrangThaiID() + " not found."));
        Optional<LoaiTrangThai> loaiTrangThaiDB = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(loaiTrangThai.getMaLoaiTrangThai());
        if(loaiTrangThaiDB.isPresent() && !loaiTrangThaiDB.get().getLoaiTrangThaiID().equals(loaiTrangThai.getLoaiTrangThaiID())) {
            throw new DuplicateResourceException("LoaiTrangThai with maLoaiTrangThai " + loaiTrangThai.getMaLoaiTrangThai() + " already exists.");
        }
        // Update the existing entity with new values
        existingLoaiTrangThai.setMaLoaiTrangThai(loaiTrangThai.getMaLoaiTrangThai());
        existingLoaiTrangThai.setTenLoaiTrangThai(loaiTrangThai.getTenLoaiTrangThai());
        existingLoaiTrangThai.setMoTa(loaiTrangThai.getMoTa());
        existingLoaiTrangThai.setDeleted(loaiTrangThai.getDeleted());
        // Save the updated entity
        return loaiTrangThaiRepository.save(loaiTrangThai);
    }

    public void deleteLoaiTrangThai(Long id) {
        LoaiTrangThai loaiTrangThai = loaiTrangThaiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LoaiTrangThai with id " + id + " not found."));
        loaiTrangThai.setDeleted(true);
        loaiTrangThaiRepository.save(loaiTrangThai);
    }
}
