package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.domain.request.ChiTietQuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.ChiTietQuyDinhLaiSuatResDTO;
import com.example.sweet.util.mapper.ChiTietQuyDinhLaiSuatMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChiTietQuyDinhLaiSuatService {
    private final ChiTietQuyDinhLaiSuatRepository repository;
    private final ChiTietQuyDinhLaiSuatMapper mapper;

    public Iterable<ChiTietQuyDinhLaiSuatResDTO> findAll() {
        return repository.findAll().stream().map(mapper::toChiTietQuyDinhLaiSuatResponseDTO).toList();
    }

    public ChiTietQuyDinhLaiSuatResDTO save(ChiTietQuyDinhLaiSuat quyDinhLaiSuat) {
        return mapper.toChiTietQuyDinhLaiSuatResponseDTO(repository.save(quyDinhLaiSuat));
    }


    public ChiTietQuyDinhLaiSuatResDTO save(ChiTietQuyDinhLaiSuatReqDTO quyDinhLaiSuat) {
        return mapper.toChiTietQuyDinhLaiSuatResponseDTO(repository.save(mapper.toChiTietQuyDinhLaiSuat(quyDinhLaiSuat)));
    }


    public Optional<ChiTietQuyDinhLaiSuatResDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toChiTietQuyDinhLaiSuatResponseDTO);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
