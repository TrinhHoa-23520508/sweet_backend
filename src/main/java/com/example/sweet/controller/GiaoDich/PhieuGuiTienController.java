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

import com.example.sweet.database.repository.dto.PhieuGuiTienDTO;
import com.example.sweet.services.GiaoDich.PhieuGuiTienService;
import com.example.sweet.util.annotation.ApiMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PhieuGuiTienController {
    private final PhieuGuiTienService phieuGuiTienService;

    public PhieuGuiTienController(PhieuGuiTienService phieuGuiTienService) {
        this.phieuGuiTienService = phieuGuiTienService;
    }

    @GetMapping("/phieu-gui-tien")
    @ApiMessage("Lấy tất cả phiếu gửi tiền")
    public ResponseEntity<List<PhieuGuiTienDTO>> getAllPhieuGuiTien() {
        return ResponseEntity.ok(this.phieuGuiTienService.getAllPhieuGuiTien());
    }

    @PostMapping("/phieu-gui-tien")
    @ApiMessage("Tạo mới phiếu gửi tiền")
    public ResponseEntity<PhieuGuiTienDTO> createPhieuGuiTien(
            @Valid @RequestBody PhieuGuiTienDTO phieuGuiTienDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.phieuGuiTienService.createPhieuGuiTien(phieuGuiTienDTO));
    }

    // @GetMapping("/phieu-gui-tien/{id}")
    // @ApiMessage("Lấy phiếu gửi tiền theo ID")
    // public ResponseEntity<PhieuGuiTienDTO> getPhieuGuiTienById(@PathVariable
    // Integer id) {
    // return ResponseEntity.ok(this.phieuGuiTienService.getPhieuGuiTienById(id));
    // }

    @DeleteMapping("/phieu-gui-tien/{id}")
    @ApiMessage("Xóa phiếu gửi tiền")
    public ResponseEntity<Void> deletePhieuGuiTien(@PathVariable Integer id) {
        phieuGuiTienService.deletePhieuGuiTien(id);
        return ResponseEntity.ok().build();
    }
}
