package com.example.sweet.service;

import com.example.sweet.database.respository.TaiKhoan.QuyenHanRespository;
import com.example.sweet.database.respository.TaiKhoan.VaiTroRespository;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.VaiTroDTO;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.mapper.VaiTroMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService {

    private final VaiTroRespository vaiTroRepository;
    private final QuyenHanRespository quyenHanRespository;
    private final VaiTroMapper vaiTroMapper;


    public VaiTroService(VaiTroRespository vaiTroRepository,
                         QuyenHanRespository quyenHanRespository,
                         VaiTroMapper vaiTroMapper) {
        this.vaiTroRepository = vaiTroRepository;
        this.quyenHanRespository = quyenHanRespository;
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
        existingVaiTro.setActive(vaiTro.getActive());
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
