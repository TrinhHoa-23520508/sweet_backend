package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.QuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.response.QuyDinhLaiSuatResponseDTO;
import com.example.sweet.util.mapper.QuyDinhLaiSuatMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuyDinhLaiSuatService {
    private final QuyDinhLaiSuatRepository repository;
    private final QuyDinhLaiSuatMapper quyDinhLaiSuatMapper;

    public Iterable<QuyDinhLaiSuatResponseDTO> findAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(QuyDinhLaiSuat::getNgayBatDau))
                .map(quyDinhLaiSuatMapper::toQuyDinhLaiSuatResponseDTO)
                .toList();
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

    public Optional<QuyDinhLaiSuatResponseDTO> findCurrentQuyDinhLaiSuat() {
        List<QuyDinhLaiSuat> list = repository.findAll().stream()
                .sorted(Comparator.comparing(QuyDinhLaiSuat::getNgayBatDau))
                .toList();

        LocalDate today = LocalDate.now();
        QuyDinhLaiSuat result = null;

        for (QuyDinhLaiSuat qdls : list) {
            if (!qdls.getNgayBatDau().isAfter(today)) {
                result = qdls;
            } else {
                break; // Đã gặp bản ghi có ngayBatDau > hiện tại
            }
        }

        return Optional.ofNullable(result)
                .map(quyDinhLaiSuatMapper::toQuyDinhLaiSuatResponseDTO);
    }
}
