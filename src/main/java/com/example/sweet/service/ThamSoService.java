package com.example.sweet.service;

import com.example.sweet.database.respository.ThamSoRespository;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.IdInvalidException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThamSoService {

    private final ThamSoRespository thamSoRespository;

    public ThamSoService(ThamSoRespository thamSoRespository) {
        this.thamSoRespository = thamSoRespository;
    }

    public boolean checkDuplicate(ThamSo thamSo) {
        if (thamSo.getThamSoID() == null) {
            return thamSoRespository.existsByMaThamSo(thamSo.getMaThamSo());
        } else {
            return thamSoRespository.existsByMaThamSoAndThamSoIDNot(thamSo.getMaThamSo(), thamSo.getThamSoID());
        }
    }

    public ThamSo createThamSo(ThamSo thamSo) {
        if (checkDuplicate(thamSo)) {
            throw new DuplicateResourceException("ThamSo with maThamSo " + thamSo.getMaThamSo() + " already exists.");
        }
        return thamSoRespository.save(thamSo);
    }

    public ThamSo getThamSoById(Long id) {
        return thamSoRespository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ThamSo with id " + id + " not found."));
    }

    public List<ThamSo> getAllThamSo(Specification<ThamSo> specification) {
        return thamSoRespository.findAll(specification);
    }

    public ThamSo updateThamSo(ThamSo thamSo) {
        if(checkDuplicate(thamSo)) {
            throw new DuplicateResourceException("ThamSo with maThamSo " + thamSo.getMaThamSo() + " already exists.");
        }
        ThamSo existingThamSo = thamSoRespository.findById(thamSo.getThamSoID())
                .orElseThrow(() -> new IdInvalidException("ThamSo with id " + thamSo.getThamSoID() + " not found."));

        // Update the existing entity with new values
        existingThamSo.setMaThamSo(thamSo.getMaThamSo());
        existingThamSo.setGiaTri(thamSo.getGiaTri());
        existingThamSo.setMoTa(thamSo.getMoTa());
        existingThamSo.setTenThamSo(thamSo.getTenThamSo());

        return thamSoRespository.save(existingThamSo);
    }


}
