package com.example.sweet.controller.GiaoDich;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.services.GiaoDich.PhieuTraLaiService;
import com.example.sweet.util.annotation.ApiMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PhieuTraLaiController {
    private final PhieuTraLaiService phieuTraLaiService;

    public PhieuTraLaiController(PhieuTraLaiService phieuTraLaiService) {
        this.phieuTraLaiService = phieuTraLaiService;
    }

    @GetMapping("/phieu-tra-lai")
    @ApiMessage("Lấy tất cả phiếu trả lãi")
    public ResponseEntity<List<PhieuTraLaiDTO>> getAllPhieuTraLai() {
        return ResponseEntity.ok(this.phieuTraLaiService.getAllPhieuTraLai());
    }

    @PostMapping("/phieu-tra-lai")
    @ApiMessage("Tạo mới phiếu trả lãi")
    public ResponseEntity<PhieuTraLaiDTO> createPhieuTraLai(
            @Valid @RequestBody PhieuTraLaiDTO phieuTraLaiDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.phieuTraLaiService.createPhieuTraLai(phieuTraLaiDTO));
    }

    @DeleteMapping("/phieu-tra-lai/{id}")
    @ApiMessage("Xóa phiếu trả lãi")
    public ResponseEntity<Void> deletePhieuTraLai(@PathVariable Long id) {
        phieuTraLaiService.deletePhieuTraLai(id);
        return ResponseEntity.ok().build();
    }
}
