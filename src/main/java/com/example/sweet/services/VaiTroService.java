package com.example.sweet.services;

import com.example.sweet.database.repository.TaiKhoan.QuyenHanRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.VaiTroDTO;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.mapper.VaiTroMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService {

    private final VaiTroRepository vaiTroRepository;
    private final QuyenHanRepository quyenHanRepository;
    private final VaiTroMapper vaiTroMapper;


    public VaiTroService(VaiTroRepository vaiTroRepository,
                         QuyenHanRepository quyenHanRepository,
                         VaiTroMapper vaiTroMapper) {
        this.vaiTroRepository = vaiTroRepository;
        this.quyenHanRepository = quyenHanRepository;
        this.vaiTroMapper = vaiTroMapper;
    }

    public boolean checkDuplicate(VaiTro vaiTro) {
        if(vaiTro.getId()==null){
            return this.vaiTroRepository.existsByName(vaiTro.getName());
        }else{
            return this.vaiTroRepository.existsByNameAndIdNot(vaiTro.getName(), vaiTro.getId());
        }
    }

    public VaiTro createVaiTro(VaiTroDTO vaiTroDTO) {
        VaiTro vaiTro = this.vaiTroMapper.toVaiTroEntity(vaiTroDTO);
        if(checkDuplicate(vaiTro)){
            throw new DuplicateResourceException("VaiTro with name " + vaiTro.getName() + " already exists.");
        }

        return vaiTroRepository.save(vaiTro);
    }

    public List<VaiTro> getAllVaiTro(Specification<VaiTro> specification) {
        return vaiTroRepository.findAll(specification);
    }

    public VaiTro getVaiTroById(Long id) {
        return vaiTroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VaiTro with id " + id + " not found."));
    }

    public VaiTro updateVaiTro(VaiTroDTO vaiTroDTO) {
        VaiTro vaiTro = this.vaiTroMapper.toVaiTroEntity(vaiTroDTO);
        if(vaiTro.getId() == null) {
            throw new IllegalArgumentException("VaiTro ID cannot be null for update.");
        }
        VaiTro existingVaiTro = vaiTroRepository.findById(vaiTro.getId())
                .orElseThrow(() -> new IllegalArgumentException("VaiTro with id " + vaiTro.getId() + " not found."));
        if(checkDuplicate(vaiTro)){
            throw new DuplicateResourceException("VaiTro with name " + vaiTro.getName() + " already exists.");
        }

        // Update the existing entity with new values
        existingVaiTro.setName(vaiTro.getName());
        existingVaiTro.setDescription(vaiTro.getDescription());
        existingVaiTro.setActive(vaiTro.isActive());
        existingVaiTro.setQuyenHans(vaiTro.getQuyenHans());
        return vaiTroRepository.save(existingVaiTro);
    }

    public void deleteVaiTro(Long id) {
        VaiTro vaiTro = vaiTroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VaiTro with id " + id + " not found."));
        vaiTro.setActive(false); // Soft delete
        vaiTroRepository.save(vaiTro);
    }


}
