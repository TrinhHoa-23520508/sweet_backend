package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.domain.request.GiaoDich.GiaoDichRequestDTO;
import com.example.sweet.domain.response.GiaoDich.GiaoDichResponseDTO;
import com.example.sweet.services.GiaoDich.GiaoDichService;
import com.example.sweet.util.annotation.ApiMessage;
import com.example.sweet.util.mapper.GiaoDich.GiaoDichMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/giao-dich")
@AllArgsConstructor
public class GiaoDichController {
    private final GiaoDichService service;
    private final GiaoDichMapper mapper;

    @GetMapping("")
    @ApiMessage("Lấy danh sách giao dịch")
    public ResponseEntity<Iterable<GiaoDichResponseDTO>> getAllGiaoDich() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("")
    @ApiMessage("Thêm mới giao dịch")
    public ResponseEntity<GiaoDichResponseDTO> insertGiaoDich(@Valid @RequestBody GiaoDichRequestDTO giaoDich) {
        if (giaoDich.getGiaoDichID() != null)
            throw new IllegalStateException("Không thể update được giao dịch");
        return ResponseEntity.ok(mapper.toGiaoDichResponseDTO(service.createGiaoDich(giaoDich)));
    }

    @GetMapping("/{id}")
    @ApiMessage("Lấy giao dịch theo ID")
    public ResponseEntity<Optional<GiaoDichResponseDTO>> getGiaoDich(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("by/tktt/{id}")
    @ApiMessage("Lấy giao dịch theo tài khoản thanh toán")
    public ResponseEntity<Iterable<GiaoDichResponseDTO>> getGiaoDichByTaiKhoan(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByTaiKhoan(id));
    }

    @GetMapping("by/nhan-vien/{id}")
    @ApiMessage("Lấy giao dịch theo nhân viên giao dịch")
    public ResponseEntity<Iterable<GiaoDichResponseDTO>> getGiaoDichByNhanVien(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByNhanVienGiaoDich(id));
    }
}
