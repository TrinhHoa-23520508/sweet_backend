package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.QuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.response.QuyDinhLaiSuatResponseDTO;
import com.example.sweet.util.mapper.QuyDinhLaiSuatMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QuyDinhLaiSuatService {
    private final QuyDinhLaiSuatRepository repository;
    private final QuyDinhLaiSuatMapper quyDinhLaiSuatMapper;

    public Iterable<QuyDinhLaiSuatResponseDTO> findAll() {
        return repository.findAll().stream().map(quyDinhLaiSuatMapper::toQuyDinhLaiSuatResponseDTO).toList();
    }

    public QuyDinhLaiSuatResponseDTO save(QuyDinhLaiSuat quyDinhLaiSuat) {
        return quyDinhLaiSuatMapper.toQuyDinhLaiSuatResponseDTO(repository.save(quyDinhLaiSuat));
    }

    public Optional<QuyDinhLaiSuatResponseDTO> findById(Long id) {
        return repository.findById(id).map(quyDinhLaiSuatMapper::toQuyDinhLaiSuatResponseDTO);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
