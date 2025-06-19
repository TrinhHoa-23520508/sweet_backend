package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.domain.request.GiaoDich.TKTTRequestDTO;
import com.example.sweet.domain.response.GiaoDich.TKTTResponseDTO;
import com.example.sweet.util.mapper.GiaoDich.TKTTMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaiKhoanThanhToanService {
    private TaiKhoanThanhToanRepository taiKhoanRepo;
    private final TKTTMapper tkttMapper;

    public List<TKTTResponseDTO> findAll() {
        return taiKhoanRepo.findAll().stream().map(tkttMapper::toTKTTResponseDTO).toList();
    }

    @Transactional
    public TaiKhoanThanhToan save(TKTTRequestDTO requestDTO) {
        return taiKhoanRepo.save(tkttMapper.toTaiKhoanThanhToan(requestDTO));
    }

    @Transactional
    public TaiKhoanThanhToan save(TaiKhoanThanhToan taiKhoan) {
        return taiKhoanRepo.save(taiKhoan);
    }

    public void deleteById(Long id) {
        taiKhoanRepo.deleteById(id);
    }

    public Optional<TKTTResponseDTO> findById(Long id) {
        return taiKhoanRepo.findById(id).stream().map(tkttMapper::toTKTTResponseDTO).findFirst();
    }
}
