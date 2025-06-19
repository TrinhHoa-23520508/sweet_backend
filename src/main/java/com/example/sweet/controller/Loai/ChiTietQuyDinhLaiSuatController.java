package com.example.sweet.controller.Loai;

import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.domain.response.ChiTietQuyDinhLaiSuatResponseDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResponseDTO;
import com.example.sweet.services.ChiTietQuyDinhLaiSuatService;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quy-dinh-lai-suat/chi-tiet")
@AllArgsConstructor
public class ChiTietQuyDinhLaiSuatController {
    private ChiTietQuyDinhLaiSuatService service;

    @GetMapping("")
    public ResponseEntity<Iterable<ChiTietQuyDinhLaiSuatResponseDTO>> getAllChiTietQuyDinhLaiSuat() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("")
    public ResponseEntity<ChiTietQuyDinhLaiSuatResponseDTO> insertChiTietQuyDinhLaiSuat(@RequestBody ChiTietQuyDinhLaiSuat loaiGiaoDich) {
        return ResponseEntity.ok(service.save(loaiGiaoDich));
    }

    @GetMapping("/{id}")
    public Optional<ChiTietQuyDinhLaiSuatResponseDTO> getChiTietQuyDinhLaiSuatById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChiTietQuyDinhLaiSuat(@PathVariable Long id) {
        service.deleteById(id);
    }
}
