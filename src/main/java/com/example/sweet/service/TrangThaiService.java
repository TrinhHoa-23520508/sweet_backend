package com.example.sweet.service;

import com.example.sweet.database.respository.Loai.LoaiTrangThaiRespository;
import com.example.sweet.database.respository.TrangThaiRespository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.TrangThaiDTO;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.IdInvalidException;
import com.example.sweet.util.mapper.TrangThaiMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrangThaiService {

    private TrangThaiRespository trangThaiRespository;
    private TrangThaiMapper trangThaiMapper;
    private LoaiTrangThaiRespository loaiTrangThaiRespository;

    public TrangThaiService(TrangThaiRespository trangThaiRespository,
                            TrangThaiMapper trangThaiMapper,
                            LoaiTrangThaiRespository loaiTrangThaiRespository) {
        this.trangThaiRespository = trangThaiRespository;
        this.trangThaiMapper = trangThaiMapper;
        this.loaiTrangThaiRespository = loaiTrangThaiRespository;
    }

    public void checkDuplicateTrangThai(TrangThai trangThai) {
        if(trangThai.getMaTrangThai()==null) {
            throw new IdInvalidException("MaTrangThai cannot be null");
        }
        if(trangThai.getLoaiTrangThai()==null) {
            throw new IdInvalidException("LoaiTrangThai cannot be null");
        }
        Optional<TrangThai> existingTrangThai = trangThaiRespository.findByMaTrangThaiAndLoaiTrangThai(
                trangThai.getMaTrangThai(), trangThai.getLoaiTrangThai());
        if (existingTrangThai.isPresent() && !existingTrangThai.get().getTrangThaiID().equals(trangThai.getTrangThaiID())) {
            throw new DuplicateResourceException("TrangThai with maTrangThai " + trangThai.getMaTrangThai() + " already exists for the specified LoaiTrangThai.");
        }
    }

    public TrangThaiDTO createTrangThai(TrangThaiDTO trangThaiDTO) {
        TrangThai trangThai = this.trangThaiMapper.toTrangThaiEnTiTy(trangThaiDTO);

        String maTrangThai = trangThai.getMaTrangThai();
        Optional<TrangThai> existingTrangThai = trangThaiRespository.findByMaTrangThaiAndLoaiTrangThai(
                trangThai.getMaTrangThai(), trangThai.getLoaiTrangThai());
        if( existingTrangThai.isPresent() ) {
            throw new DuplicateResourceException("TrangThai with maTrangThai " + maTrangThai + " already exists for the specified LoaiTrangThai.");
        }

        return this.trangThaiMapper.toTrangThaiDTO(trangThaiRespository.save(trangThai));
    }

    public List<TrangThaiDTO> getAllTrangThaiByLoai(Long loaiTrangThaiID) {
        if(loaiTrangThaiID == null) {
            throw new IllegalArgumentException("LoaiTrangThaiID cannot be null");
        }
        LoaiTrangThai loaiTrangThai = this.loaiTrangThaiRespository.findById(loaiTrangThaiID)
                .orElseThrow(() -> new IdInvalidException("LoaiTrangThai with ID " + loaiTrangThaiID + " not found."));
        List<TrangThai> trangThais = this.trangThaiRespository.findAllByLoaiTrangThai(loaiTrangThai);
        return trangThais.stream()
                .map(trangThaiMapper::toTrangThaiDTO)
                .toList();
    }

    public TrangThaiDTO getTrangThaiById(Long id) {
        if (id == null) {
            throw new IdInvalidException("TrangThai ID cannot be null");
        }
        TrangThai trangThai = trangThaiRespository.findById(id)
                .orElseThrow(() -> new IdInvalidException("TrangThai with ID " + id + " not found."));
        return trangThaiMapper.toTrangThaiDTO(trangThai);
    }

    public List<TrangThaiDTO> getAllTrangThai(Specification<TrangThai> spec) {
        return trangThaiRespository.findAll(spec).stream().map(trangThaiMapper::toTrangThaiDTO).collect(Collectors.toList());
    }

    public TrangThaiDTO updateTrangThai(TrangThaiDTO trangThaiDTO) {
        if (trangThaiDTO.getTrangThaiID() == null) {
            throw new IdInvalidException("TrangThai ID cannot be null for update.");
        }
        TrangThai existingTrangThai = trangThaiRespository.findById(trangThaiDTO.getTrangThaiID())
                .orElseThrow(() -> new IdInvalidException("TrangThai with ID " + trangThaiDTO.getTrangThaiID() + " not found."));

        TrangThai updatedTrangThai = this.trangThaiMapper.toTrangThaiEnTiTy(trangThaiDTO);
        this.checkDuplicateTrangThai(updatedTrangThai);
        existingTrangThai.setMaTrangThai(updatedTrangThai.getMaTrangThai());
        existingTrangThai.setTenTrangThai(updatedTrangThai.getTenTrangThai());
        existingTrangThai.setLoaiTrangThai(updatedTrangThai.getLoaiTrangThai());
        existingTrangThai.setDeleted(updatedTrangThai.getDeleted());

        return this.trangThaiMapper.toTrangThaiDTO(trangThaiRespository.save(existingTrangThai));
    }

    public void deleteTrangThai(Long id) {
        if (id == null) {
            throw new IdInvalidException("TrangThai ID cannot be null for deletion.");
        }
        TrangThai trangThai = trangThaiRespository.findById(id)
                .orElseThrow(() -> new IdInvalidException("TrangThai with ID " + id + " not found."));
        trangThai.setDeleted(true);
        trangThaiRespository.save(trangThai);
    }





}
