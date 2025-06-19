package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.domain.response.ChiTietQuyDinhLaiSuatResponseDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResponseDTO;
import com.example.sweet.util.mapper.ChiTietQuyDinhLaiSuatMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChiTietQuyDinhLaiSuatService {
    private final ChiTietQuyDinhLaiSuatRepository repository;
    private final ChiTietQuyDinhLaiSuatMapper chiTietQuyDinhLaiSuatMapper;

    public Iterable<ChiTietQuyDinhLaiSuatResponseDTO> findAll() {
        return repository.findAll().stream().map(chiTietQuyDinhLaiSuatMapper::toChiTietQuyDinhLaiSuatResponseDTO).toList();
    }

    public ChiTietQuyDinhLaiSuatResponseDTO save(ChiTietQuyDinhLaiSuat quyDinhLaiSuat) {
        return chiTietQuyDinhLaiSuatMapper.toChiTietQuyDinhLaiSuatResponseDTO(repository.save(quyDinhLaiSuat));
    }

    public Optional<ChiTietQuyDinhLaiSuatResponseDTO> findById(Long id) {
        return repository.findById(id).map(chiTietQuyDinhLaiSuatMapper::toChiTietQuyDinhLaiSuatResponseDTO);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
