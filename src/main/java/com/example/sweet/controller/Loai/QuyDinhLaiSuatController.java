package com.example.sweet.controller.Loai;

import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.response.QuyDinhLaiSuatResponseDTO;
import com.example.sweet.services.QuyDinhLaiSuatService;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quy-dinh-lai-suat")
@AllArgsConstructor
public class QuyDinhLaiSuatController {
    private QuyDinhLaiSuatService service;

    @GetMapping("")
    @ApiMessage("Lấy danh sách quy định lãi suất")
    public ResponseEntity<Iterable<QuyDinhLaiSuatResponseDTO>> getAllQuyDinhLaiSuat() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("")
    @ApiMessage("Thêm mới quy định lãi suất")
    public ResponseEntity<QuyDinhLaiSuatResponseDTO> insertQuyDinhLaiSuat(@RequestBody QuyDinhLaiSuat loaiGiaoDich) {
        return ResponseEntity.ok(service.save(loaiGiaoDich));
    }

    @GetMapping("/{id}")
    @ApiMessage("Lấy chi tiết quy định lãi suất theo ID")
    public Optional<QuyDinhLaiSuatResponseDTO> getChiTietQuyDinhLaiSuat(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Xoá quy định lãi suất theo ID")
    public void deleteQuyDinhLaiSuat(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/current")
    public ResponseEntity<QuyDinhLaiSuatResponseDTO> getCurrentQuyDinhLaiSuat() {
        return service.findCurrentQuyDinhLaiSuat()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
