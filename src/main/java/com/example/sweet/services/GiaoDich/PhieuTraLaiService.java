package com.example.sweet.services.GiaoDich;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.sweet.database.repository.GiaoDich.PhieuTraLaiRepository;
import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;
import com.example.sweet.util.mapper.PhieuTraLaiMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PhieuTraLaiService {
    private final PhieuTraLaiRepository phieuTraLaiRepository;
    private final PhieuTraLaiMapper phieuTraLaiMapper;

    public List<PhieuTraLaiDTO> getAllPhieuTraLai() {
        Iterable<PhieuTraLai> phieuTraLais = phieuTraLaiRepository.findAll();
        List<PhieuTraLai> phieuTraLaiList = new ArrayList<>();
        phieuTraLais.forEach(phieuTraLaiList::add);

        return phieuTraLaiList.stream()
                .map(phieuTraLaiMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PhieuTraLaiDTO createPhieuTraLai(PhieuTraLaiDTO phieuTraLaiDTO) {
        try {
            // Convert DTO to entity
            PhieuTraLai phieuTraLai = phieuTraLaiMapper.toEntity(phieuTraLaiDTO);

            // Save to database
            PhieuTraLai savedPhieuTraLai = phieuTraLaiRepository.save(phieuTraLai);

            // Convert back to DTO and return
            return phieuTraLaiMapper.toDTO(savedPhieuTraLai);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo phiếu trả lãi: " + e.getMessage());
        }
    }

    @Transactional
    public void deletePhieuTraLai(Long id) {
        try {
            phieuTraLaiRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa phiếu trả lãi: " + e.getMessage());
        }
    }
}
