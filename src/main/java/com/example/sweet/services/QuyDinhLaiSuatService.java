package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.repository.Loai.QuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.request.QuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResDTO;
import com.example.sweet.util.mapper.ChiTietQuyDinhLaiSuatMapper;
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
    private final ChiTietQuyDinhLaiSuatRepository chiTietQuyDinhLaiSuatRepository;
    private final ChiTietQuyDinhLaiSuatMapper chiTietQuyDinhLaiSuatMapper;

    public Iterable<QuyDinhLaiSuatResDTO> findAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(QuyDinhLaiSuat::getNgayBatDau))
                .map(mapper::toQuyDinhLaiSuatResponseDTO)
                .toList();
    }

    public QuyDinhLaiSuatResDTO save(QuyDinhLaiSuat quyDinhLaiSuat) {
        return mapper.toQuyDinhLaiSuatResponseDTO(repository.save(quyDinhLaiSuat));
    }

    public QuyDinhLaiSuatResDTO save(QuyDinhLaiSuatReqDTO requestDTO) {
        if (requestDTO.getQuyDinhLaiSuatID() != null) { // Cập nhật
            if (!repository.existsById(requestDTO.getQuyDinhLaiSuatID())) {
                throw new NullPointerException("Không tồn tại quy định lãi suất với ID: " + requestDTO.getQuyDinhLaiSuatID());
            }
            return save(mapper.toQuyDinhLaiSuat(requestDTO));
        }
        // Thêm mới
        QuyDinhLaiSuat quyDinhLaiSuat = mapper.toQuyDinhLaiSuatGoc(requestDTO);
        quyDinhLaiSuat = repository.save(quyDinhLaiSuat);

        QuyDinhLaiSuat finalQuyDinhLaiSuat = quyDinhLaiSuat;
        var chiTiets = requestDTO.getChiTietQuyDinhLaiSuats().stream()
                .map((value) -> {
                    ChiTietQuyDinhLaiSuat result = chiTietQuyDinhLaiSuatMapper.toChiTietQuyDinhLaiSuat(value);
                    result.setQuyDinhLaiSuat(finalQuyDinhLaiSuat);
                    return result;
                }).toList();
        chiTietQuyDinhLaiSuatRepository.saveAll(chiTiets);

        return mapper.toQuyDinhLaiSuatResponseDTO(quyDinhLaiSuat);
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
