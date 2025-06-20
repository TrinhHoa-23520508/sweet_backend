package com.example.sweet.services;

import com.example.sweet.database.repository.ThamSoRepository;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.NotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThamSoService {

    private final ThamSoRepository thamSoRepository;

    public ThamSoService(ThamSoRepository thamSoRepository) {
        this.thamSoRepository = thamSoRepository;
    }

    public boolean checkDuplicate(ThamSo thamSo) {
        if (thamSo.getThamSoID() == null) {
            return thamSoRepository.existsByMaThamSo(thamSo.getMaThamSo());
        } else {
            return thamSoRepository.existsByMaThamSoAndThamSoIDNot(thamSo.getMaThamSo(), thamSo.getThamSoID());
        }
    }

    public ThamSo createThamSo(ThamSo thamSo) {
        thamSo.setThamSoID(null);
        if (checkDuplicate(thamSo)) {
            throw new DuplicateResourceException("ThamSo with maThamSo " + thamSo.getMaThamSo() + " already exists.");
        }
        return thamSoRepository.save(thamSo);
    }

    public ThamSo getThamSoById(Long id) {
        return thamSoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ThamSo with id " + id + " not found."));
    }

    public List<ThamSo> getAllThamSo(Specification<ThamSo> specification) {
        return thamSoRepository.findAll(specification);
    }

    public ThamSo updateThamSo(ThamSo thamSo) {
        if (checkDuplicate(thamSo)) {
            throw new DuplicateResourceException("ThamSo with maThamSo " + thamSo.getMaThamSo() + " already exists.");
        }
        ThamSo existingThamSo = thamSoRepository.findById(thamSo.getThamSoID())
                .orElseThrow(() -> new NotFoundException("ThamSo with id " + thamSo.getThamSoID() + " not found."));

        // Update the existing entity with new values
        existingThamSo.setMaThamSo(thamSo.getMaThamSo());
        existingThamSo.setGiaTri(thamSo.getGiaTri());
        existingThamSo.setMoTa(thamSo.getMoTa());
        existingThamSo.setTenThamSo(thamSo.getTenThamSo());

        return thamSoRepository.save(existingThamSo);
    }

    public void deleteThamSo(Long id) {
        ThamSo thamSo = thamSoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ThamSo with id " + id + " not found."));
        thamSoRepository.delete(thamSo);
    }


}
