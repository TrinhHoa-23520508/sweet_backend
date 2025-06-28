package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.QuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.request.QuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResDTO;
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
    private final QuyDinhLaiSuatMapper mapper;

    public Iterable<QuyDinhLaiSuatResDTO> findAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(QuyDinhLaiSuat::getNgayBatDau))
                .map(mapper::toQuyDinhLaiSuatResponseDTO)
                .toList();
    }

    public QuyDinhLaiSuatResDTO save(QuyDinhLaiSuat quyDinhLaiSuat) {
        return mapper.toQuyDinhLaiSuatResponseDTO(repository.save(quyDinhLaiSuat));
    }

    public QuyDinhLaiSuatResDTO save(QuyDinhLaiSuatReqDTO quyDinhLaiSuat) {
        return save(mapper.toQuyDinhLaiSuat(quyDinhLaiSuat));
    }

    public QuyDinhLaiSuatResDTO saveByID(Long id, QuyDinhLaiSuatReqDTO quyDinhLaiSuat) {
        return save(mapper.toQuyDinhLaiSuat(quyDinhLaiSuat));
    }

    public Optional<QuyDinhLaiSuatResDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toQuyDinhLaiSuatResponseDTO);
    }

    public void deleteById(Long id) {
        var currentQuyDinh = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quy định lãi suất không tồn tại"));
        currentQuyDinh.setActive(false);
        repository.save(currentQuyDinh);
        // repository.deleteById(id);
    }

    public Optional<QuyDinhLaiSuatResDTO> findCurrentQuyDinhLaiSuat() {
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
                .map(mapper::toQuyDinhLaiSuatResponseDTO);
    }
}
